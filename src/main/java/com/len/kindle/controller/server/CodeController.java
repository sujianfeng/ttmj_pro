package com.len.kindle.controller.server;

import com.len.kindle.config.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


/**
 *
 * @author hqz
 * @date 2016/9/8
 */
@Slf4j
@Controller
@RequestMapping("/server/user/code")
public class CodeController {

    char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private int width = 60;
    private int height = 32;
    private int codeCount = 4;
    private int x = 0;
    private int fontHeight;
    private int codeY;

    @RequestMapping("/generateCode")
    public void generateCode(HttpServletRequest request,
                             HttpServletResponse response) {

        x = width / (codeCount + 1);
        fontHeight = height - 2;
        codeY = height - 4;


        HttpSession session = request.getSession();

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 创建一个随机数生成器类
        Random random = new Random();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        // 设置字体。
        g.setFont(font);

        // 画边框。
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);

        // 随机产生20条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(Color.BLACK);
        /*
         * for (int i = 0; i < 20; i++) { int x = random.nextInt(width); int y =
		 * random.nextInt(height); int xl = random.nextInt(12); int yl =
		 * random.nextInt(12); g.drawLine(x, y, x + xl, y + yl); }
		 */

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(10)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x - 5, codeY + 2);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        session.setAttribute(Constant.CODE_KEY, randomCode.toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 清空缓存
        g.dispose();

        try {
            // 将图像输出到Servlet输出流中。
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write(buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
