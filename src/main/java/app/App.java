package app;

public class App {
    public static void main(String[] args) {
        System.out.println("Application is running...");
        while (true) {
            try {
                Thread.sleep(1000); // Приостанавливает выполнение на 1 секунду
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Application interrupted.");
                break;
            }
        }
    }
    public int add(int a, int b) {
        return a + b;
    }
}
