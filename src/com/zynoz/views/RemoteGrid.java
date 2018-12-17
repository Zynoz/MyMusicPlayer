package com.zynoz.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.zynoz.Main;
import com.zynoz.controller.MediaAPI;
import com.zynoz.util.Util;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RemoteGrid extends GridPane {

    private Label port;
    private JFXTextField portInput;
    private JFXButton startServer;
    private JFXCheckBox startUp;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    public RemoteGrid(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
        addListeners();
    }

    private void initComponents() {
        port = new Label("Port: ");
        startServer = new JFXButton("Start Server");
        portInput = new JFXTextField(Util.getPort());
        startUp = new JFXCheckBox("Start up server when application starts.");
    }

    private void addComponents() {
        add(port, 0, 0);
        add(portInput, 1, 0);
        add(startUp, 0, 1);
        add(startServer, 1, 1);
    }

    private void addListeners() {
        startServer.setOnAction(event -> {
            if (!portInput.getText().equals("") || !portInput.getText().isEmpty()) {
                System.out.println(portInput.getText());
                Util.setPort(portInput.getText());
                Util.startServer(rootBorderPane, mediaAPI);
                startServer.setText("Server already running");
                startServer.setDisable(true);
            } else {
                Main.alert("Error", "Port is empty");
            }
        });
    }
}