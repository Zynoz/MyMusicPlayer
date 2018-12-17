package com.zynoz.server;

import com.zynoz.controller.MediaAPI;
import com.zynoz.views.RootBorderPane;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerProtocol extends Thread {
    private Socket socket;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    public PlayerProtocol(Socket socket, int clientNumber, RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.socket = socket;
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        System.out.println("New connection with client# " + clientNumber + " at " + socket);
    }

    public void run() {
        System.out.println("new player server");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(mediaAPI.getCurrentSong());

            while (true) {
                String input = bufferedReader.readLine();
                if (input == null || input.equals(".")) {
                    break;
                }
                if (input.equals("playpause")) {
                    System.out.println("playpause");
                    Platform.runLater(() -> rootBorderPane.getRightVBox().getButtonsHBox().playPause());
                } else if (input.equals("next")) {
                    System.out.println("next");
                    mediaAPI.playNextSong();
                    Platform.runLater(() -> rootBorderPane.getSongOverview().setSongs());
                    out.println(mediaAPI.getCurrentSong());
                } else if (input.startsWith("volume:")) {
                    double value = Double.parseDouble(input.substring(7));
                    System.out.println("input: " + input + "; value: " + value);
                    Platform.runLater(() -> rootBorderPane.getRightVBox().getVolumeHBox().getVolumeSlider().setValue(value));
                    mediaAPI.setVolume(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}