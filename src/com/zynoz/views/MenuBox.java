package com.zynoz.views;

import com.jfoenix.controls.JFXButton;
import com.zynoz.controller.MediaAPI;
import javafx.scene.control.Tab;

public class MenuBox extends Tab {

    private JFXButton serverSettings;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    public MenuBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        super("Remove Control");
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
        addListeners();
    }

    private void initComponents() {
        serverSettings = new JFXButton("Server Settings");
    }

    private void addComponents() {
        setContent(serverSettings);
    }

    private void addListeners() {
        serverSettings.setOnAction(event -> {
            System.out.println("menuBox");
        });
    }
}