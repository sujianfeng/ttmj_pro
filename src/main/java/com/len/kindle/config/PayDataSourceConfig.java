package com.len.kindle.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author sujianfeng
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "datasource.pay")
public class PayDataSourceConfig extends BaseDataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        super.initDataSource(dataSource);
        return dataSource;
    }
}
