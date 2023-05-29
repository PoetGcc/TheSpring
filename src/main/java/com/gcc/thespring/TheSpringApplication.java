package com.gcc.thespring;

import com.gcc.thespring.utils.ServerIpUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TheSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TheSpringApplication.class, args);
        ServerIpUtils.appInfo(context);
    }

}
