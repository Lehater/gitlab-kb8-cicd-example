package app;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) {
        try {
            // Создаем HTTP-сервер, который слушает на порту 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // Добавляем обработчик для корневого маршрута
            server.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String response = "Hello, World!";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes());
                    }
                }
            });

            // Запускаем сервер
            server.setExecutor(null); // Используем встроенный пул потоков
            server.start();
            System.out.println("Server is running on http://localhost:8080");
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}
