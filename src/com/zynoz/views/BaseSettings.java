package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class BaseSettings extends TabPane {

    private MenuBox menus;
    private RemoteTab serverOptions;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Stage stage;

    public BaseSettings(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        menus = new MenuBox(rootBorderPane, mediaAPI);
        serverOptions = new RemoteTab(rootBorderPane, mediaAPI);
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    private void addComponents() {
        getTabs().addAll(menus, serverOptions);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}