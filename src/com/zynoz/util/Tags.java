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
import org.jaudiotagger.tag.images.ArtworkFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

public class Tags {

    public static String getTitle(final Song song) throws Exception {
        if (song != null) {
            AudioFile audioFile = AudioFileIO.read(new File(song.getSongPath()));
            return audioFile.getTag().getFirst(FieldKey.TITLE);
        } else {
            throw new SongException("Song is null.");
        }
    }

    public static String getArtist(final Song song) throws Exception {
        if (song != null) {
            AudioFile audioFile = AudioFileIO.read(new File(song.getSongPath()));
            return audioFile.getTag().getFirst(FieldKey.ARTIST);
        } else {
            throw new SongException("Song is null.");
        }
    }

    public static Optional<Image> getCover(final Song song) throws Exception {
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
    public static boolean set(final Song song, final FieldKey fieldKey, final String string) throws TagException {
        if (song != null) {
            try {
                AudioFile audioFile = audioFile = AudioFileIO.read(new File(song.getSongPath()));
                audioFile.getTag().setField(fieldKey, string);
                audioFile.commit();
                return true;
            } catch (Exception e) {
                throw new TagException(e.getMessage());
            }
        } else {
            throw new TagException("Song is null");
        }
    }

    public static boolean setCover(final Song song, final File image) throws TagException {
        if (song != null) {
            if (image != null) {
                try {
                    AudioFile audioFile = AudioFileIO.read(new File(song.getSongPath()));
                    Artwork artwork = ArtworkFactory.createArtworkFromFile(image);
                    audioFile.getTag().addField(artwork);
                    audioFile.getTag().setField(artwork);
                    audioFile.commit();
                    return true;
                } catch (Exception e) {
                    throw new TagException(e.getMessage());
                }

            } else {
                throw new TagException("Image is null.");
            }
        } else {
            throw new TagException("Song is null.");
        }
    }
}