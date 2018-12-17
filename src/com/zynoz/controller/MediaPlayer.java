package com.zynoz.controller;

import com.zynoz.Main;
import com.zynoz.model.Song;
import com.zynoz.util.Util;
import com.zynoz.views.RootBorderPane;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;

public final class MediaPlayer  {
    private static MediaPlayer instance;

    private Song currentSong;
    private javafx.scene.media.MediaPlayer fxPlayer;
    private double volume = 100;
    private ObservableList<Song> songs;
    private boolean isPlaying = false;
    private Duration duration;

    private RootBorderPane rootBorderPane;

    private MediaPlayer() {}

    public static synchronized MediaPlayer getInstance() {
        if (instance == null) {
            instance = new MediaPlayer();
        }
        return instance;
    }

    public void setRootBorderPane(RootBorderPane rootBorderPane) {
        this.rootBorderPane = rootBorderPane;
    }

    public void setSongs(ObservableList<Song> songs) {
        this.songs = songs;
    }

    public void setVolume(double volume) {
        this.volume =  volume;
        if (fxPlayer != null) {
            fxPlayer.setVolume(volume);
        }
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
        fxPlayer.setAutoPlay(true);
        fxPlayer.setOnEndOfMedia(this::playRandomSong);

        fxPlayer.currentTimeProperty().addListener(observable -> updateValues());
        fxPlayer.setOnReady(() -> {
            duration = fxPlayer.getMedia().getDuration();
            updateValues();
        });
        return fxPlayer;
    }

    @SuppressWarnings("deprecation")
    private void updateValues() {
//        rootBorderPane.getRightVBox().getTimeHBox().getTimeSlider().setValue(fxPlayer.getCurrentTime().divide(Tags.getDuration(currentSong)).toMillis() * 100.0);
        Platform.runLater(() -> {
            Duration currentTime = fxPlayer.getCurrentTime();
            rootBorderPane.getRightVBox().getTimeHBox().getTime().setText(Util.formatTime(currentTime, duration));
            rootBorderPane.getRightVBox().getTimeHBox().getTimeSlider().setDisable(duration.isUnknown());
            if (!rootBorderPane.getRightVBox().getTimeHBox().getTimeSlider().isDisabled()
                    && duration.greaterThan(Duration.ZERO)
                    && !rootBorderPane.getRightVBox().getTimeHBox().getTimeSlider().isValueChanging()) {
                rootBorderPane.getRightVBox().getTimeHBox().getTimeSlider().setValue(currentTime.divide(duration).toMillis()
                        * 100.0);
            }
        });
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
        int rng = Util.getRandomSong(songs.size());
        System.out.println("Random number: " + rng);
        rootBorderPane.getRightVBox().getTimeHBox().getTimeSlider().setValue(0);
        rootBorderPane.getRightVBox().setSong(songs.get(rng));
        return playSong(songs.get(rng));
    }

    public void playPause() {
        if (fxPlayer != null) {
            if (isPlaying) {
                fxPlayer.pause();
                isPlaying = false;
            } else {
                fxPlayer.play();
                isPlaying = true;
            }
        } else {
            Main.alert("Error", "No song selected.");
        }
    }

    public javafx.scene.media.MediaPlayer getFxPlayer() {
        return fxPlayer;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void seek(Duration v) {
        fxPlayer.seek(v);
    }

    public Duration getDuration() {
        return duration;
    }
}