
import client.ui.ClientWindow;
import client.domain.User;
import server.ui.ServerWindow;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Jhon", "127.0.0.1", "1234", "123456");
        User user2 = new User("Doe", "127.0.0.1", "4321", "654321");

        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow, user1);
        new ClientWindow(serverWindow, user2).setLocation(1250, 365);
    }
}