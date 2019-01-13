package com.zynoz.controller;

import com.zynoz.model.Song;
import com.zynoz.views.RootBorderPane;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;

public class MediaAPI {

    private static  MediaAPI instance;

    private MediaManager mediaManager;
    private com.zynoz.controller.MediaPlayer mediaPlayer;

    private MediaAPI() {
        mediaManager = MediaManager.getInstance();
        mediaPlayer = com.zynoz.controller.MediaPlayer.getInstance();
        mediaManager.reload();
        mediaPlayer.setSongs(mediaManager.getSongs());
    }

    public static MediaAPI getInstance() {
        if (instance == null) {
            instance = new MediaAPI();
        }
        return instance;
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void playPause() {
        mediaPlayer.playPause();
    }

    public Song getCurrentSong() {
        return mediaPlayer.getCurrentSong();
    }

    public MediaPlayer playSong(Song selectedItem) {
        return mediaPlayer.playSong(selectedItem);
    }

    public MediaPlayer playRandomSong() {
        return mediaPlayer.playRandomSong();
    }

    public MediaPlayer playNextSong() {
        return mediaPlayer.playNextSong();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer.getFxPlayer();
    }

    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public ObservableList<Song> getSongList() {
        return mediaManager.getSongs();
    }

    public void reload() {
        mediaManager.reload();
        System.out.println("reloaded");
    }

    public ObservableList<Song> getSongs() {
        return mediaManager.getSongs();
    }

    public boolean editSong(Song song, FieldKey fieldKey, String title) {
        return mediaManager.editSong(song, fieldKey, title);
    }

    public boolean editSongCover(final Song song, final File image) {
        return mediaManager.editSongCover(song, image);
    }

    public boolean removeSong(Song song) {
        return mediaManager.removeSong(song);
    }

    public boolean deleteSong(Song song) {
        return mediaManager.deleteSong(song);
    }

    public boolean addSong(Song song) {
        return mediaManager.addSong(song);
    }

    public void seek(Duration v) {
        mediaPlayer.seek(v);
    }

    public void setRootBorderPane(RootBorderPane rootBorderPane) {
        mediaPlayer.setRootBorderPane(rootBorderPane);
    }

    public Duration getDuration() {
        return mediaPlayer.getDuration();
    }

    public void setPrevSong(Song song) {
        mediaManager.setPrevSong(song);
    }

    public Song getPrevSong() {
        return mediaManager.getPrevSong();
    }
}