package com.entire.hotspotmap;

import com.entire.hotspotmap.system.config.ConfigProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
@MapperScan("com.entire.hotspotmap.mapper")
public class HotspotMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotspotMapApplication.class, args);
    }

}
