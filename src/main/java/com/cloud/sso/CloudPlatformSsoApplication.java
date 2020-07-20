package com.cloud.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.cloud.sso.mapper")
@SpringBootApplication
public class CloudPlatformSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPlatformSsoApplication.class, args);
    }

}
