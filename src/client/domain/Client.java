package client.domain;


import client.ui.View;
import server.domain.Server;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private View view;
    private User user;
    private Server server;
    private boolean connected;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");


    public Client(View view, Server server, User user) {
        this.view = view;
        this.server = server;
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public boolean connectToServer(){
        if (server.connectUser(this)){
            Date date = new Date();
            showOnWindow(": Вы успешно подключились!\n");

            connected = true;
            server.viewServer.appendLog( user.getName() + " подключился к беседе");

            String log = server.getHistory();
            if (log != null){
                view.sendMessage("История переписки:\n-------start-------\n");
                showOnWindow(log);
                view.sendMessage("-------end--------\n");
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    public void disconnectFromServer(){
        if (connected) {
            connected = false;
            server.disconnectUser(this);
            view.disconnectedFromServer();
            Date date = new Date();
            showOnWindow(": Вы были отключены от сервера!");

        }
    }

    public void answerFromServer(String messageFromServer){
        showOnWindow(messageFromServer);
    }

    public void sendMessage(String message){
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage( user.getName() + ": " + message);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }

    private void showOnWindow(String text) {
        Date date = new Date();
        view.sendMessage(sdf.format(date) + ": " + text + "\n");
    }
}