package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class InfoGridPane extends GridPane {

    private Label songName, songArtist;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Song song;

    public InfoGridPane(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        songName = new Label("");
        songArtist = new Label("");
    }

    private void addComponents() {
        add(songArtist, 0, 0);
        add(songName, 1, 0);
    }

    public void setSong(Song song) {
        this.song = song;
        songName.setText(song.getSongName());
        songArtist.setText(song.getSongArtist() + ": ");
    }
}