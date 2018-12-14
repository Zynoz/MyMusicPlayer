package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RightGridPane extends GridPane {

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    private Label titleLabel, artistLabel, durationLabel, title, artist, duration;

    public RightGridPane(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.mediaAPI = mediaAPI;
        this.rootBorderPane = rootBorderPane;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        titleLabel = new Label("Title: ");
        artistLabel = new Label("Artist: ");
        durationLabel = new Label("Duration: ");
        title = new Label("Title");
        artist = new Label("Artist");
        duration = new Label("Duration");
    }

    private void addComponents() {
        add(titleLabel, 0, 0);
        add(title, 1, 0);
        add(artistLabel, 0, 1);
        add(artist, 1, 1);
        add(durationLabel, 0, 2);
        add(duration, 1, 2);
    }

    public void setSong(Song song) {
        System.out.println("right: " + song.toString());
        title.setText(song.getSongName());
        artist.setText(song.getSongArtist());
        int minutes = ((Tags.getDuration(song) % (60 * 60)) / 60);
        int seconds = (Tags.getDuration(song) % 60);
        duration.setText(minutes + ":" + seconds);
    }
}