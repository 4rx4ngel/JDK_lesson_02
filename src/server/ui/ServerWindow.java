package server.ui;

import server.domain.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerWindow extends JFrame implements ViewServer {
    public Server server;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnStart, btnStop;
    private JTextArea log;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");


    public ServerWindow(){
        setting();
        createPanel();

        setVisible(true);
    }

    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        server = new Server(this);
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isServerWork()){
                    appendLog("Сервер уже был запущен");
                } else {
                    server.setIsServerWork(true);
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!server.isServerWork()){
                    appendLog("Сервер уже был остановлен");
                } else {
                    server.setIsServerWork(false);
                    server.disconnectAll();
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    @Override
    public void appendLog(String text) {
        Date date = new Date();
        log.append(sdf.format(date) + ": " + text + "\n");
    }

}