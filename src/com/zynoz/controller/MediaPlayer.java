package com.zynoz.controller;

import com.zynoz.exception.SongException;
import com.zynoz.model.Song;
import com.zynoz.util.Util;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;

import java.io.File;

public final class MediaPlayer  {
    private static MediaPlayer instance;

    private Song currentSong;
    private javafx.scene.media.MediaPlayer fxPlayer;
    private double volume = 100;
    private ObservableList<Song> songs;
    private boolean isPlaying;

    private final Util util = Util.getInstance();

    private MediaPlayer() {}

    public static synchronized MediaPlayer getInstance() {
        if (instance == null) {
            instance = new MediaPlayer();
        }
        return instance;
    }

    public void setSongs(ObservableList<Song> songs) {
        this.songs = songs;
    }

    public void setVolume(double volume) {
        this.volume =  volume;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public javafx.scene.media.MediaPlayer playSong(Song song) {
        if (fxPlayer != null) {
            fxPlayer.stop();
            fxPlayer.dispose();
            isPlaying = false;
        }

        System.out.println(song.toString());
        Media media = new Media(new File(song.getSongPath()).toURI().toString());
        fxPlayer = new javafx.scene.media.MediaPlayer(media);
        fxPlayer.play();
        currentSong = song;
        isPlaying = true;
        fxPlayer.setVolume(volume);
        isPlaying = true;
        return fxPlayer;
    }

    public javafx.scene.media.MediaPlayer playNextSong() throws SongException {
        return playRandomSong();
    }

    public javafx.scene.media.MediaPlayer playRandomSong() throws SongException {
        return playSong(util.getRandomSong());
    }

    public void togglePlayPause() {
        if (isPlaying) {
            fxPlayer.pause();
            isPlaying = false;
        } else {
            fxPlayer.play();
            isPlaying = true;
        }
    }
}