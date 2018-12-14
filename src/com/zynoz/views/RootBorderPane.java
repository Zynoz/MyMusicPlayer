package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
    private SongDetails songDetails;
    private BottomPane bottomPane;
    private RightGridPane rightGridPane;

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
        songDetails = new SongDetails(this, mediaAPI);
        bottomPane = new BottomPane(this, mediaAPI);
        rightGridPane = new RightGridPane(this, mediaAPI);
    }

    private void addComponents() {
        mFile.getItems().addAll(miReload, new SeparatorMenuItem(), miExit);
        menuBar.getMenus().addAll(mFile, mEdit, mHelp);

        setTop(menuBar);
        setLeft(songOverview);
        setCenter(songDetails);
        setBottom(bottomPane);
        setRight(rightGridPane);
        BorderPane.setAlignment(bottomPane, Pos.CENTER);
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

    public void setSongDetails(Song song) {
        songDetails.setSong(song);
    }


    public void setSongInfos(Song selectedItem) {
        rightGridPane.setSong(selectedItem);
    }
}