package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Util;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonsHBox extends HBox {
    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Song song;

    private Button playPause, next, edit;

    public ButtonsHBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
        addListeners();
    }

    private void addListeners() {
        next.setOnAction(event -> {
            mediaAPI.playRandomSong();
            rootBorderPane.setSongInfos(mediaAPI.getCurrentSong());
        });
        edit.setOnAction(event -> Util.openEditDialogue(rootBorderPane, mediaAPI, song));
        playPause.setOnAction(event -> {
            if (mediaAPI.isPlaying()) {
                playPause.setText(">");
                mediaAPI.pause();
            } else {
                playPause.setText("||");
                mediaAPI.play();
            }
        });
    }

    private void initComponents() {
        playPause = new Button("||");
        next = new Button("Next");
        edit = new Button("Edit Song");
    }

    private void addComponents() {
        getChildren().addAll(playPause, next, edit);
    }

    public void setSong(Song song) {
        this.song = song;
    }
}