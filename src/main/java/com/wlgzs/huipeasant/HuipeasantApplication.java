package com.wlgzs.huipeasant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableCaching
@ServletComponentScan
@EnableScheduling
public class HuipeasantApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuipeasantApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("9000MB");
        config.setMaxRequestSize("9000MB");
        return config.createMultipartConfig();
    }

}
