package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonsHBox extends HBox {
    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    private Button playPause, next;

    public ButtonsHBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        playPause = new Button("playpause");
        next = new Button("Next");
    }

    private void addComponents() {
        getChildren().addAll(playPause, next);
    }
}