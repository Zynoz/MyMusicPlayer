package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class ProgressHBox extends HBox {
    private Slider progressSlider;
    private Label start = new Label("00:00");
    private Label end = new Label("00:00");

    public ProgressHBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {

    }
}