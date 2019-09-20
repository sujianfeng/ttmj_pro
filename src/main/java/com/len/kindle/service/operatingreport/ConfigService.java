package com.len.kindle.service.operatingreport;

import com.len.kindle.entity.GameConfig;
import com.len.kindle.repo.GameConfigRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/4 17:24
 */
@Slf4j
@Service
public class ConfigService {
    @Autowired
    private GameConfigRepo gameConfigRepo;

    public Page<GameConfig> findAll(String[] condition, Pageable pageable) {

        Page<GameConfig> gameConfigPage = gameConfigRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();


            if (StringUtils.isNotBlank(condition[0]) && !("-1".equals(condition[0]))) {
                list.add(cb.equal(root.get("type").as(Integer.class), Integer.parseInt(condition[0])));
            }

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);

        Iterator<GameConfig> gameConfigIterator = gameConfigPage.iterator();
        while (gameConfigIterator.hasNext()) {
            GameConfig gameConfig = gameConfigIterator.next();
            gameConfig.setTypeName();
        }

        return gameConfigPage;
    }

    public GameConfig getGameConfig(Integer id) {
        return gameConfigRepo.getOne(id);
    }

    public GameConfig saveGameConfig(GameConfig dbGameConfig) {
        return gameConfigRepo.saveAndFlush(dbGameConfig);
    }
}
