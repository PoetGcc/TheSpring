package com.gcc.thespring.utils;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author: gcc
 * @date: 2023/5/29 10:58
 */
public class ServerIpUtils {

    public static void appInfo(ConfigurableApplicationContext context) {
        if (context == null) {
            return;
        }

        Environment environment = context.getBean(Environment.class);
        environmentInfo(environment);
    }

    public static void environmentInfo(Environment environment) {
        if (environment == null) {
            return;
        }

        String hostAddress;

        try {
            hostAddress = Inet4Address.getLocalHost().getHostAddress();
            System.out.println("hostAddress = " + hostAddress);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        String port = environment.getProperty("server.port");
        System.out.println("port = " + port);

        System.out.println("访问地址 = { " + hostAddress + ":" + port + " }");

        String webAppName = environment.getProperty("server.servlet.context-path");
        System.out.println("webAppName = " + webAppName);
    }
}
