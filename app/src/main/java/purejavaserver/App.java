package purejavaserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }
    
    void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", 5678);
        HttpServer httpServer = HttpServer.create(address, 0);
        httpServer.createContext("/", new Handler());
        httpServer.start();
    }
}
