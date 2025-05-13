package com.ihr360.applet.customization.qys;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IhrApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(IhrApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 设置控制台输出编码为 UTF-8
        System.out.println("file.encoding="+System.getProperty("file.encoding"));
        System.setProperty("console.encoding", "UTF-8");
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("file.encoding="+System.getProperty("file.encoding"));
        System.out.println("启动成功!");
    }

}