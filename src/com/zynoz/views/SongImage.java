package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;

public class SongImage extends AnchorPane {
    private Song song;
    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Image songCover;
    private ImageView imageView;
    private URL resource = getClass().getResource("../resources/images/default.png");

    public SongImage(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        songCover = new Image(String.valueOf(resource));
        imageView = new ImageView();
    }

    private void addComponents() {
        getChildren().add(imageView);
        imageView.setImage(songCover);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
    }

    @SuppressWarnings("ConstantConditions")
    public void setSong(Song song) {
        this.song = song;
        if (Tags.getCover(song) != null) {
            System.out.println("image present");
            songCover = SwingFXUtils.toFXImage(Tags.getCover(song), null);
            imageView.setImage(songCover);
        } else {
            System.out.println("image not present");
            songCover = new Image(String.valueOf(resource));
            imageView.setImage(songCover);
        }
    }
}