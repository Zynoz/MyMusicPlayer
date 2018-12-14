package com.zynoz.controller;

import com.zynoz.Main;
import com.zynoz.exception.MediaManagerException;
import com.zynoz.exception.SongException;
import com.zynoz.exception.TagException;
import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import com.zynoz.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaManager {
    private static MediaManager instance;

    private MediaPlayer mediaPlayer;
    private ObservableList<Song> songs;
    private List<String> mp3paths = new ArrayList<>();

    private MediaManager() throws SongException {
        songs = FXCollections.observableArrayList();
        getMp3s(new File(Util.mediaDirectory));
        addMp3s(mp3paths);
        mediaPlayer = MediaPlayer.getInstance();
    }

    public static synchronized MediaManager getInstance() throws SongException {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void reload() {
        mp3paths.clear();
        songs.clear();
        getMp3s(new File(Util.mediaDirectory));
        try {
            addMp3s(mp3paths);
        } catch (SongException e) {
            Main.alert(e.getCause().toString(), e.getMessage());
        }
    }

    private void addMp3s(final List<String> mp3paths) throws SongException {
        for (String s : mp3paths) {
            songs.add(new Song(s));
        }
    }

    private void getMp3s(final File dir) {
       File[] files;
       if (dir.isDirectory() && (files = dir.listFiles()) != null) {
           for (File file : files) {
               getMp3s(file);
           }
       } else {
           String path = dir.getPath();
           if (path.endsWith(".mp3")) {
               //System.out.println("path old: " + path);
               path = path.replace("\\", "/");
               System.out.println("path new: " + path);

               mp3paths.add(path);
           }
       }
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public boolean editSong(final Song song, final FieldKey fieldKey, final String title) throws MediaManagerException {
        try {
            return Tags.set(song, fieldKey, title);
        } catch (TagException e) {
            throw new MediaManagerException(e.getCause() + ": " + e.getMessage());
        }
    }

    public boolean editSongCover(final Song song, final File image) throws MediaManagerException {
        try {
            return Tags.setCover(song, image);
        } catch (TagException e) {
            throw new MediaManagerException(e.getClass() + ": " + e.getMessage());
        }
    }

    public boolean removeSong(final Song song) {
        return songs.remove(song);
    }

    public boolean deleteSong(final Song song) {
        File file = new File(String.valueOf(song.getSongPath()));
        return file.delete();
    }

    public boolean addSong(final Song song) {
        return songs.add(song);
    }

    public void playSong(Song song) {
        mediaPlayer.playSong(song);
    }
}