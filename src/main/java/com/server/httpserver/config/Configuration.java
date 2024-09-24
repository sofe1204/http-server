package com.server.httpserver.config;

public class Configuration {
    private int port;
    private String webroot;

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    public int getPort() {
        return port;
    }

    public String getWebroot() {
        return webroot;
    }
}
