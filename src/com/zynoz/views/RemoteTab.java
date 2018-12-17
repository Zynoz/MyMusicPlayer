package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import javafx.scene.control.Tab;

public class RemoteTab extends Tab {

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;

    public RemoteTab(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        super("Remote Control");
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        addComponents();
    }

    private void addComponents() {
        setContent(new RemoteGrid(rootBorderPane, mediaAPI));
    }
}