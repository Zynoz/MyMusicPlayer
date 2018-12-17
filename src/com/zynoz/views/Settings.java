package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.server.Server;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Settings extends GridPane {

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Stage stage;

    public Settings(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        startServer();
    }

    private void startServer() {
        Thread thread = new Thread(() -> {
            Server server = new Server(rootBorderPane, mediaAPI, 6666);
        });
        thread.start();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}