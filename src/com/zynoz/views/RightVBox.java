package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;

public class RightVBox extends VBox {

    private ImageView imageView;
    private ButtonsHBox buttonsHBox;
    private VolumeHBox volumeHBox;
    private ProgressHBox progressHBox;
    private InfoGridPane infoGridPane;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    private Song song;

    public RightVBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.mediaAPI = mediaAPI;
        this.rootBorderPane = rootBorderPane;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        imageView = new ImageView();
        buttonsHBox = new ButtonsHBox(rootBorderPane, mediaAPI);
        volumeHBox = new VolumeHBox(rootBorderPane, mediaAPI);
        progressHBox = new ProgressHBox(rootBorderPane, mediaAPI);
        infoGridPane = new InfoGridPane(rootBorderPane, mediaAPI);
        setWidth(200);
        setPrefWidth(200);
        setMinWidth(200);
        setMaxWidth(200);
    }

    private void addComponents() {
        getChildren().addAll(imageView, buttonsHBox, volumeHBox, progressHBox, infoGridPane);
    }

    public void setSong(Song song) {
        this.song = song;
        BufferedImage bufferedImage = Tags.getCover(song);
        if (bufferedImage != null) {
            System.out.println(SwingFXUtils.toFXImage(bufferedImage, null));
            imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            //imageView.setImage(image);
        } else {
            System.out.println("song cover is null");
            //imageView.setImage(new Image(getClass().getResource("../resources/images/default.png")));
        }
    }

    public Song getSong() {
        return song;
    }
}