package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class BottomPane extends HBox {
    private Slider seekSlider;
    private Slider volumeSlider;
    private Button playPauseButton;
    private boolean atEndOfMedia;
    private boolean repeat;
    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    public BottomPane(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
        //addListeners();
    }

    private void initComponents() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));
        playPauseButton = new Button(">");
        seekSlider = new Slider();
        volumeSlider = new Slider();
    }

    private void addComponents() {
        getChildren().addAll(playPauseButton, seekSlider, volumeSlider);
    }

//    private void addListeners() {
//        playPauseButton.setOnAction(event -> {
//            MediaPlayer.Status status = mediaAPI.getMediaPlayer().getStatus();
//            if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
//                return;
//            }
//            if (status == MediaPlayer.Status.PAUSED
//                || status == MediaPlayer.Status.READY
//                || status == MediaPlayer.Status.STOPPED) {
//                if (atEndOfMedia) {
//                    mediaPlayer = mediaAPI.playRandomSong();
//                    atEndOfMedia = false;
//                }
//                mediaPlayer.play();
//            } else {
//                mediaPlayer.pause();
//            }
//        });
//    }
}