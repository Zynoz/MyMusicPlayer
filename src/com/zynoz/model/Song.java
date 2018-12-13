package com.zynoz.model;

import com.zynoz.util.Tags;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

public class Song {
    private SimpleStringProperty songName;
    private SimpleStringProperty songArtist;
    private SimpleObjectProperty<UUID> songUuid;
    private SimpleObjectProperty<URI> songPath;

    public Song(URI songPath) {
        this.songPath = new SimpleObjectProperty<>(songPath);
        this.songArtist = new SimpleStringProperty(Tags.getArtist(this));
        this.songName = new SimpleStringProperty(Tags.getTitle(this));
        this.songUuid = new SimpleObjectProperty<>(UUID.randomUUID());
    }

    public String getSongName() {
        return songName.get();
    }

    public SimpleStringProperty songNameProperty() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName.set(songName);
    }

    public String getSongArtist() {
        return songArtist.get();
    }

    public SimpleStringProperty songArtistProperty() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist.set(songArtist);
    }

    public UUID getSongUuid() {
        return songUuid.get();
    }

    public SimpleObjectProperty<UUID> songUuidProperty() {
        return songUuid;
    }

    public void setSongUuid(UUID songUuid) {
        this.songUuid.set(songUuid);
    }

    public URI getSongPath() {
        return songPath.get();
    }

    public SimpleObjectProperty<URI> songPathProperty() {
        return songPath;
    }

    public void setSongPath(URI songPath) {
        this.songPath.set(songPath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return Objects.equals(songName, song.songName) &&
                Objects.equals(songArtist, song.songArtist) &&
                songUuid.equals(song.songUuid) &&
                songPath.equals(song.songPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songName, songArtist, songUuid, songPath);
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName=" + songName +
                ", songArtist=" + songArtist +
                ", songUuid=" + songUuid +
                ", songPath=" + songPath +
                '}';
    }
}