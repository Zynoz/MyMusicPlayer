package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;

public class SongDetails extends GridPane {
    private Song song;
    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Image songCover;
    private URL resource = getClass().getResource("../resources/images/default.png");

    public SongDetails(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(0, 10, 0, 10));

        //initComponents();
        addComponents();
    }

    private void initComponents() {

    }

    private void addComponents() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(resource)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }

    public void setSong(Song song) {
        this.song = song;
        if (Tags.getCover(song).isPresent()) {
            System.out.println("image present");
            songCover = Tags.getCover(song).get();
        } else {
            System.out.println("image not present");
            songCover = new Image(String.valueOf(resource));
        }

        BackgroundImage backgroundImage = new BackgroundImage(songCover, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }
}