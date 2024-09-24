package com.server.httpserver;

import com.server.httpserver.config.Configuration;
import com.server.httpserver.config.ConfigurationManager;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server starting....");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("port: "+ conf.getPort());
        System.out.println("webroot: "+ conf.getWebroot());
    }
}