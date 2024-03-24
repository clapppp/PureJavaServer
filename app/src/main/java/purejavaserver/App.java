package purejavaserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }
    
    void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", 5670);
        HttpServer httpServer = HttpServer.create(address, 0);
        httpServer.createContext("/", (exchange) -> {
            printRequest(exchange);
            sendResponse(exchange);
        });
        httpServer.start();
    }

    private void printRequest(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        String body = new String(requestBody.readAllBytes());
        InetSocketAddress remoteAddress = exchange.getRemoteAddress();
        System.out.println("클라이언트 IP" + remoteAddress.getHostName());
        System.out.println(body);
        System.out.println(requestBody.toString());
    }

    private void sendResponse(HttpExchange exchange) throws IOException {
        String hello = "hello";
        byte[] content = hello.getBytes();
        OutputStream responseBody = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, content.length);
        responseBody.write(content);
        responseBody.flush();
        System.out.println(responseBody.toString());
    }
}
