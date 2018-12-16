package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import javafx.scene.layout.GridPane;

public class InfoGridPane extends GridPane {

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    public InfoGridPane(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
    }
}