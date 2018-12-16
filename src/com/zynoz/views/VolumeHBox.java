package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class VolumeHBox extends HBox {
    private Slider volumeSlider;
    private Label volume = new Label("Volume");

    public VolumeHBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {

    }
}