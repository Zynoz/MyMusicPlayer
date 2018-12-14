package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.controller.MediaManager;
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
    private SongDetails songDetails;

    private MediaAPI mediaAPI;

    public RootBorderPane(MediaManager mediaManager) {
        initComponents();
        addComponents();
        addListeners();
        setVis(true);
        mediaAPI = new MediaAPI(mediaManager);
    }

    private void initComponents() {
        menuBar = new MenuBar();

        mFile = new Menu("File");
        mEdit = new Menu("Edit");
        mHelp = new Menu("Help");

        miReload = new MenuItem("Reload songs");
        miExit = new MenuItem("Exit");

        songOverview = new SongOverview(this);
        songDetails = new SongDetails(this);
    }

    private void addComponents() {
        mFile.getItems().addAll(miReload, new SeparatorMenuItem(), miExit);

        menuBar.getMenus().addAll(mFile, mEdit, mHelp);

        setTop(menuBar);
        setLeft(songOverview);
        setCenter(songDetails);
    }

    private void addListeners() {
        miExit.setOnAction(event -> Platform.exit());
        miReload.setOnAction(event -> {
            mediaAPI.reload();
            songOverview.setSongs(mediaAPI.getSongs());
        });
    }

    private void setVis(boolean visible) {
        songOverview.setVisible(visible);
    }

    public SongOverview getSongOverview() {
        return songOverview;
    }

    public MediaAPI getMediaAPI() {
        return mediaAPI;
    }

    public void setSongDetails(Song song) {
        songDetails.setSong(song);
    }
}