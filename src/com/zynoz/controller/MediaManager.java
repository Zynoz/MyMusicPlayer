package com.zynoz.controller;

import com.zynoz.Main;
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

    private ObservableList<Song> songs;
    private List<String> mp3paths = new ArrayList<>();

    private MediaManager() {
        songs = FXCollections.observableArrayList();
        getMp3s(new File(Util.mediaDirectory));
        addMp3s(mp3paths);
    }

    public static synchronized MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }


    public void reload() {
        mp3paths.clear();
        songs.clear();
        getMp3s(new File(Util.mediaDirectory));
        addMp3s(mp3paths);
    }

    private void addMp3s(final List<String> mp3paths) {
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
               path = path.replace("\\", "/");
               System.out.println("path new: " + path);
               mp3paths.add(path);
           }
       }
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public boolean editSong(final Song song, final FieldKey fieldKey, final String title) {
        try {
            return Tags.set(song, fieldKey, title);
        } catch (Exception e) {
            Main.alert(e.getCause().toString(), e.getMessage());
            return false;
        }
    }

    public boolean editSongCover(final Song song, final File image) {
            return Tags.setCover(song, image);
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
}