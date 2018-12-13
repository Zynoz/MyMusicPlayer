package com.zynoz.util;

import com.zynoz.exception.SongException;
import com.zynoz.exception.TagException;
import com.zynoz.model.Song;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.images.Artwork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

public class Tags {

    public static String getTitle(Song song) throws Exception {
        if (song != null) {
            AudioFile audioFile = AudioFileIO.read(new File(song.getSongPath()));
            return audioFile.getTag().getFirst(FieldKey.TITLE);
        } else {
            throw new SongException("Song is null.");
        }
    }

    public static String getArtist(Song song) throws Exception {
        if (song != null) {
            AudioFile audioFile = AudioFileIO.read(new File(song.getSongPath()));
            return audioFile.getTag().getFirst(FieldKey.ARTIST);
        } else {
            throw new SongException("Song is null.");
        }
    }

    public static Optional<Image> getCover(Song song) throws Exception{
        if (song != null) {
            MP3File mp3File = (MP3File) AudioFileIO.read(new File(song.getSongPath()));
            if (mp3File.getTag() != null) {
                Artwork artwork = mp3File.getTag().getFirstArtwork();
                return Optional.of(SwingFXUtils.toFXImage((BufferedImage) artwork.getImage(), null));
            } else {
                throw new TagException("Could not get MP3 tag.");
            }
        } else {
            throw new TagException("Song is null.");
        }
    }
    public static boolean set(final Song song, final FieldKey fieldKey, final String string) throws Exception {
        if (song != null) {
            AudioFile audioFile = AudioFileIO.read(new File(song.getSongPath()));
            audioFile.getTag().setField(fieldKey, string);
            audioFile.commit();
            return true;
        } else {
            throw new SongException("Song is null");
        }
    }
}