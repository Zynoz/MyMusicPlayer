package com.zynoz.controller;

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

    public double getVolume() {
        return volume;
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

    public javafx.scene.media.MediaPlayer playNextSong() {
        int currentIndex = songs.indexOf(currentSong);
        int newIndex;
        if (currentIndex < songs.size() - 1) {
            newIndex = songs.indexOf(currentSong) + 1;
        } else {
            newIndex = 0;
        }
        return playSong(songs.get(newIndex));
    }

    public javafx.scene.media.MediaPlayer playRandomSong() {
        int rng = Util.getRandomeSong(songs.size());
        System.out.println("Random number: " + rng);
        return playSong(songs.get(rng));
    }

    public void play() {
        fxPlayer.play();
    }

    public void pause() {
        fxPlayer.pause();
    }

    public javafx.scene.media.MediaPlayer getFxPlayer() {
        return fxPlayer;
    }

    public Song getCurrentSong() {
        return currentSong;
    }
}