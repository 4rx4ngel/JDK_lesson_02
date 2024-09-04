package client.ui;

public interface View {
    void sendMessage(String message);
    void connectedToServer();
    void disconnectedFromServer();
}