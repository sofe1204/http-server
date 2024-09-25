package com.server.httpserver;

import com.server.httpserver.config.Configuration;
import com.server.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server starting....");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("port: "+ conf.getPort());
        System.out.println("webroot: "+ conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort()); // server socket just to accept the connection
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream(); // to read
            OutputStream outputStream = socket.getOutputStream(); //to write

            // writing
            String html = "<html><head><title>Java http server</title></head><body><h1>This page was served by java http server</h1></body</html>";

            final String CRLF = "\n\r"; //13, 10 ascii

            String response =
                    "HTTP/1.1 200 OK" + CRLF +// status line  : http version response_code response_message;
                    "Content-Length: " + html.getBytes().length + CRLF + // header
                            CRLF +
                            html +
                            CRLF + CRLF ;

            outputStream.write(response.getBytes());


            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}