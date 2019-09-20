package com.len.kindle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KindleApplicationTests {

	@Test
	public void contextLoads() {
//		UserInfo userInfo = new UserInfo();
//		userInfo.setAccount("111");
//		System.out.print(userInfo);

        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.put("2", "2");


	}

}
