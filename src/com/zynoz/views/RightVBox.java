package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.io.File;

public class RightVBox extends VBox {

    private ImageView imageView;
    private ButtonsHBox buttonsHBox;
    private VolumeHBox volumeHBox;
    private TimeHBox timeHBox;
    private InfoGridPane infoGridPane;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    private Song song;

    public RightVBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.mediaAPI = mediaAPI;
        this.rootBorderPane = rootBorderPane;
        initComponents();
        addComponents();
        Image image = new Image(String.valueOf(new File(String.valueOf(getClass().getResource("../resources/images/default.png")))));
        imageView.setImage(image);
    }

    private void initComponents() {
        imageView = new ImageView();
        buttonsHBox = new ButtonsHBox(rootBorderPane, mediaAPI);
        volumeHBox = new VolumeHBox(rootBorderPane, mediaAPI);
        timeHBox = new TimeHBox(rootBorderPane, mediaAPI);
        infoGridPane = new InfoGridPane(rootBorderPane, mediaAPI);
        setWidth(200);
        setPrefWidth(200);
        setMinWidth(200);
        setMaxWidth(200);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
    }

    private void addComponents() {
        getChildren().addAll(imageView, buttonsHBox, volumeHBox, timeHBox, infoGridPane);
//        getChildren().addAll(imageView, buttonsHBox, volumeHBox, timeHBox);
    }

    public void setSong(Song song) {
        this.song = song;
        BufferedImage bufferedImage = Tags.getCover(song);
        if (bufferedImage != null) {
            imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        } else {
            Image image = new Image(String.valueOf(new File(String.valueOf(getClass().getResource("../resources/images/default.png")))));
            imageView.setImage(image);
        }
        buttonsHBox.setSong(song);
        volumeHBox.setSong(song);
        timeHBox.setSong(song);
        infoGridPane.setSong(song);
    }

    public Song getSong() {
        return song;
    }

    public TimeHBox getTimeHBox() {
        return timeHBox;
    }

    public ButtonsHBox getButtonsHBox() {
        return buttonsHBox;
    }
}