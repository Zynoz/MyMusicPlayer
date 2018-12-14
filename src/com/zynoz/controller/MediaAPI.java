package com.zynoz.controller;

import com.zynoz.model.Song;
import javafx.collections.ObservableList;

public class MediaAPI {

    private MediaManager mediaManager;

    public MediaAPI(MediaManager mediaManager) {
        this.mediaManager = mediaManager;
    }

    public boolean isPlaying() {
        return mediaManager.getMediaPlayer().isPlaying();
    }

    public void togglePlayPause() {
        mediaManager.getMediaPlayer().togglePlayPause();
    }

    public void reload() {
        mediaManager.reload();
    }

    public ObservableList<Song> getSongs() {
        return mediaManager.getSongs();
    }

    public void playSong(Song selectedItem) {
        mediaManager.playSong(selectedItem);
    }
}