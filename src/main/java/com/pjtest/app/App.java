package com.pjtest.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App 
{
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/spot_check", new SpotHandler());
        server.setExecutor(null);
        server.start();
    }
    
    static class SpotHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            // Only accepts POST
            if(!t.getRequestMethod().equalsIgnoreCase("POST")){
                byte [] response = "Invalid Method".getBytes();
                t.sendResponseHeaders(405, response.length);
                OutputStream os = t.getResponseBody();
                os.write(response);
                os.close();
                return;
            }

            InputStream ios = t.getRequestBody();
            Analyzer analyzer = new Analyzer();
            try {
                analyzer.readInput(ios);
            } catch(Exception e){
                byte [] response = e.getMessage().getBytes();
                t.sendResponseHeaders(400, response.length);
                OutputStream os = t.getResponseBody();
                os.write(response);
                os.close();
                return;
            }
            SpotsData data = analyzer.analyzeData();

            byte [] response = SpotsData.genJson(data).getBytes();
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }
}
