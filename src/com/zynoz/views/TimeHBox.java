package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class TimeHBox extends HBox {
    private Slider timeSlider;
    private Label time = new Label("00:00");

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Song song;

    public TimeHBox(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        initComponents();
        addComponents();
        addListeners();
        timeSlider.setPrefWidth(120);
    }

    private void initComponents() {
        timeSlider = new Slider();
    }

    private void addComponents() {
        getChildren().addAll(time, timeSlider);
    }

    private void addListeners() {
        timeSlider.valueProperty().addListener(observable -> {
            if (timeSlider.isValueChanging()) {
                mediaAPI.seek(mediaAPI.getDuration().multiply(timeSlider.getValue() / 100.0));
            }
        });
    }

    public void setSong(Song song) {
        this.song = song;
        timeSlider.setValue(0);
    }

    public Slider getTimeSlider() {
        return timeSlider;
    }

    public Label getTime() {
        return time;
    }
}