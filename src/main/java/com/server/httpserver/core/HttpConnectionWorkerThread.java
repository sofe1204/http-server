package com.server.httpserver.core;

import com.server.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{

    private Socket socket;
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);


    public HttpConnectionWorkerThread(Socket socket)
    {
        this.socket=socket;
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        OutputStream outputStream= null;

        try {
            inputStream = socket.getInputStream(); // to read
            outputStream = socket.getOutputStream(); //to write

            // writing
            String html = "<html><head><title>Java http server</title></head><body><h1>This page was served by java http server</h1></body</html>";

            final String CRLF = "\n\r"; //13, 10 ascii

            String response =
                    "HTTP/1.1 200 OK" + CRLF +// status line  : http version response_code response_message;
                            "Content-Length: " + html.getBytes().length + CRLF + // header
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection processing finished");
        }
        catch (IOException e) {
            LOGGER.info("Problem with communication ", e);
        }finally {
            if(inputStream!=null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if(outputStream!=null)
            {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
            if(socket!=null)
            {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
