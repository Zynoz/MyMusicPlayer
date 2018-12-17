package com.zynoz.server;

import com.zynoz.Main;
import com.zynoz.controller.MediaAPI;
import com.zynoz.views.RootBorderPane;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private int port;
    private int clientNumber;
    private ServerSocket serverSocket;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    public Server(RootBorderPane rootBorderPane, MediaAPI mediaAPI, int port) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        this.port = port;
        try {
            startServer();
        } catch (Exception e) {
            Main.alert(e.getCause().toString(), e.getMessage());
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private void startServer() throws IOException{
        System.out.println("Startserver");
        serverSocket = new ServerSocket(port);
        try {
            while (true) {
                new PlayerProtocol(serverSocket.accept(), clientNumber++, rootBorderPane, mediaAPI).start();
            }
        } finally {
            serverSocket.close();
        }
    }
}