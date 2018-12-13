package com.zynoz.controller;

import com.zynoz.exception.MediaManagerException;
import com.zynoz.exception.SongException;
import com.zynoz.model.Song;
import com.zynoz.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediaManager {
    private ObservableList<Song> songs = FXCollections.observableArrayList();

    public void loadMedia() throws MediaManagerException {
        File dir = new File(Util.mediaDirectory);
        List<File> listOfSongs;

        if (dir.listFiles() != null) {
            //noinspection ConstantConditions
            listOfSongs = new ArrayList<>(Arrays.asList(dir.listFiles()));
        } else {
            throw new MediaManagerException("No songs in music directory.");
        }

        if (!listOfSongs.isEmpty()) {
            songs.clear();
            for (File file : listOfSongs) {
                System.out.println(Arrays.toString(file.listFiles()));
                System.out.println(file.toString());
                if (file.isFile()) {
                    if (file.getName().endsWith(".mp3")) {
                        try {
                            songs.add(new Song(file.toURI()));
                        } catch (SongException e) {
                            throw new MediaManagerException(e.getClass() + ": " + e.getMessage());
                        }
                    } else {
                        System.out.println("empty");
                    }
                } else {
                    System.out.println("emtpy");
                }
            }
        } else {
            System.out.println("empty");
        }
    }
}
