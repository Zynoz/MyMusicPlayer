package com.zynoz.ui;

import com.zynoz.controller.MediaAPI;
import com.zynoz.controller.MediaManager;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

public class RootBorderPane extends BorderPane {
    private MenuBar menuBar;
    private Menu mFile, mEdit, mHelp;
    private MenuItem miReload, miExit;
    private Overview overview;

    private MediaAPI mediaAPI;

    public RootBorderPane(MediaManager mediaManager) {
        initComponents();
        addComponents();
        addListeners();
        setVis(true);
        mediaAPI = new MediaAPI(mediaManager, this);
    }

    private void initComponents() {
        menuBar = new MenuBar();

        mFile = new Menu("File");
        mEdit = new Menu("Edit");
        mHelp = new Menu("Help");

        miReload = new MenuItem("Reload songs");
        miExit = new MenuItem("Exit");

        overview = new Overview();
    }

    private void addComponents() {
        mFile.getItems().addAll(miReload, new SeparatorMenuItem(), miExit);

        menuBar.getMenus().addAll(mFile, mEdit, mHelp);

        setTop(menuBar);
        setCenter(overview);
    }

    private void addListeners() {
        miExit.setOnAction(event -> Platform.exit());
        miReload.setOnAction(event -> {
            mediaAPI.reload();
            overview.setSongs(mediaAPI.getSongs());
        });
    }

    private void setVis(boolean visible) {
        overview.setVisible(visible);
    }

    public Overview getOverview() {
        return overview;
    }
}