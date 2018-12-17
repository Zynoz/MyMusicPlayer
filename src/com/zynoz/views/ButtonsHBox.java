package com.zynoz.views;

import com.jfoenix.controls.JFXButton;
import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Util;
import javafx.scene.layout.HBox;

public class ButtonsHBox extends HBox {
    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Song song;

    private JFXButton playPause, next, edit;

    public ButtonsHBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
        addListeners();
    }

    private void initComponents() {
        playPause = new JFXButton("||");
        next = new JFXButton("Next");
        edit = new JFXButton("Edit Song");
    }

    private void addComponents() {
        getChildren().addAll(playPause, next, edit);
    }

    private void addListeners() {
        next.setOnAction(event -> {
            mediaAPI.playRandomSong();
            rootBorderPane.setSongInfos(mediaAPI.getCurrentSong());
        });
        edit.setOnAction(event -> Util.openEdit(rootBorderPane, mediaAPI, song));
        playPause.setOnAction(event -> {
            playPause();
        });
    }

    public void playPause() {
        if (mediaAPI.isPlaying()) {
            playPause.setText(">");
            mediaAPI.playPause();
        } else {
            playPause.setText("||");
            mediaAPI.playPause();
        }
    }

    public void setSong(Song song) {
        this.song = song;
    }
}