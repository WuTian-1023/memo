package com.wlq.memoapi;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wlq.memoapi")
@MapperScan("com.wlq.memoapi.api.mapper")
public class MemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoApiApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
