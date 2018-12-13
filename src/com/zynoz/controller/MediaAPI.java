package com.zynoz.controller;

import com.zynoz.Main;
import com.zynoz.exception.SongException;
import com.zynoz.model.Song;
import com.zynoz.ui.RootBorderPane;
import javafx.collections.ObservableList;

public class MediaAPI {

    private MediaManager mediaManager;

    public MediaAPI(MediaManager mediaManager) {
        this.mediaManager = mediaManager;

        try {
            mediaManager.getMediaPlayer().playRandomSong();
        } catch (SongException e) {
            Main.alert(e.getCause().toString(), e.getMessage());
        }
    }

    public MediaAPI(MediaManager mediaManager, RootBorderPane rootBorderPane) {

    }

    public void reload() {
        mediaManager.reload();
    }

    public ObservableList<Song> getSongs() {
        return mediaManager.getSongs();
    }

}