package com.server.httpserver.core.io;

public class WebRootNotFoundException extends Throwable {
    public WebRootNotFoundException(String message) {
        super(message);
    }
}
