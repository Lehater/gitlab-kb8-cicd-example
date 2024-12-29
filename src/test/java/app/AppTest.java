package app;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void testServerResponse() throws Exception {
        // Запускаем сервер в отдельном потоке
        Thread serverThread = new Thread(() -> {
            try {
                App.main(new String[]{});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        // Подождем, чтобы сервер успел запуститься
        Thread.sleep(1000);

        // Отправляем запрос на сервер
        URL url = new URL("http://localhost:8080");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "Response code should be 200");

        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            String response = scanner.useDelimiter("\\A").next();
            assertEquals("Hello, World!", response, "Response should be 'Hello, World!'");
        }
    }
}
