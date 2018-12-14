package com.zynoz.views;

import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;

public class SongDetails extends GridPane {
    private Song song;
    private RootBorderPane rootBorderPane;
    private Label songTitle, songArtist, songDuration;
    private Image songCover;

    public SongDetails(RootBorderPane rootBorderPane) {
        this.rootBorderPane = rootBorderPane;
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(0, 10, 0, 10));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        songTitle = new Label("Title");
        songArtist = new Label("Artist");
        songDuration = new Label("Duration");


    }

    private void addComponents() {
        add(songTitle, 0, 0);
        add(songArtist, 0, 1);
        add(songDuration, 1, 0);

        URL test = getClass().getResource("../resources/images/default.png");
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(test)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }

    public void setSong(Song song) {
        this.song = song;
        songTitle.setText(song.getSongName());
        songArtist.setText(song.getSongArtist());
        songDuration.setText(String.valueOf(Tags.getDuration(song)));
        if (Tags.getCover(song).isPresent()) {
            System.out.println("image present");
            songCover = Tags.getCover(song).get();
        } else {
            System.out.println("image not present");
            songCover = new Image("../resources/images/default.png");
        }
        //songCover = Tags.getCover(song).orElse(new Image("../resources/images/default.png"));

        BackgroundImage backgroundImage = new BackgroundImage(songCover, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }
}