package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
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
    private SongOverview songOverview;
    private RightVBox rightVBox;

    private MediaAPI mediaAPI;

    public RootBorderPane() {
        mediaAPI = MediaAPI.getInstance();
        initComponents();
        addComponents();
        addListeners();
    }

    private void initComponents() {
        menuBar = new MenuBar();

        mFile = new Menu("File");
        mEdit = new Menu("Edit");
        mHelp = new Menu("Help");

        miReload = new MenuItem("Reload songs");
        miExit = new MenuItem("Exit");

        songOverview = new SongOverview(this, mediaAPI);
        rightVBox = new RightVBox(this, mediaAPI);

        songOverview.setPrefWidth(300);
    }

    private void addComponents() {
        mFile.getItems().addAll(miReload, new SeparatorMenuItem(), miExit);
        menuBar.getMenus().addAll(mFile, mEdit, mHelp);

        setTop(menuBar);
        setLeft(songOverview);
        setRight(rightVBox);
        songOverview.setSongs();
    }

    private void addListeners() {
        miExit.setOnAction(event -> Platform.exit());
        miReload.setOnAction(event -> {
            mediaAPI.reload();
            songOverview.setSongs();
        });
    }

    public SongOverview getSongOverview() {
        return songOverview;
    }


    public void setSongInfos(Song selectedItem) {
        rightVBox.setSong(selectedItem);
    }
}