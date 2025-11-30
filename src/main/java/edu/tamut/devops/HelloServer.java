package edu.tamut.devops;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HelloServer {

    public static void main(String[] args) throws IOException {
        int port = 9090; // port for our HTTP server

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new HelloHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("HelloServer running at http://localhost:" + port + "/");
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = """
                    <html>
                      <head><title>Hello, DevOps!</title></head>
                      <body>
                        <h1>Hello, DevOps!</h1>
                        <p>My First CI/CD pipeline has been successfully built and deployed. It works!</p>
                      </body>
                    </html>
                    """;

            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }
}
