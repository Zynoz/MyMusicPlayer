package com.zynoz.views;

import com.jfoenix.controls.JFXSlider;
import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VolumeHBox extends HBox {
    private JFXSlider volumeSlider;
    private Label volume = new Label("Volume");

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Song song;

    public VolumeHBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
        addListeners();
    }

    private void initComponents() {
        volumeSlider = new JFXSlider();
        volumeSlider.setValue(100);
    }

    private void addComponents() {
        getChildren().addAll(volume, volumeSlider);
    }

    private void addListeners() {
        volumeSlider.valueProperty().addListener(observable -> {
            if (volumeSlider.isValueChanging()) {
                mediaAPI.setVolume(volumeSlider.getValue() / 100);
            }
        });
    }

    public void setSong(Song song) {
        this.song = song;
        mediaAPI.setVolume(volumeSlider.getValue() / 100);
    }

    public JFXSlider getVolumeSlider() {
        return volumeSlider;
    }
}