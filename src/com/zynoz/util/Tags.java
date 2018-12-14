package com.zynoz.util;

import com.zynoz.Main;
import com.zynoz.exception.TagException;
import com.zynoz.model.Song;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Tags {

    public static String getField(final Song song, final FieldKey fieldKey) throws TagException {
        if (song != null) {
            AudioFile audioFile;
            try {
                audioFile = AudioFileIO.read(new File(String.valueOf(song.getSongPath())));
            } catch (CannotReadException | IOException | org.jaudiotagger.tag.TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
                throw new TagException(e.getMessage());
            }
            return audioFile.getTag().getFirst(fieldKey);
        } else {
            throw new TagException("Song is null.");
        }
    }

    public static Optional<Image> getCover(final Song song) {
        if (song != null) {
            MP3File mp3File = null;
            try {
                mp3File = (MP3File) AudioFileIO.read(new File(String.valueOf(song.getSongPath())));
            } catch (CannotReadException | IOException | org.jaudiotagger.tag.TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
                Main.alert(e.getCause().toString(), e.getMessage());
            }
            if (mp3File != null) {
                if (mp3File.getTag() != null) {
                    Artwork artwork = mp3File.getTag().getFirstArtwork();
                    try {
                        if (artwork != null) {
                            return Optional.of(SwingFXUtils.toFXImage((BufferedImage) artwork.getImage(), null));
                        } else {
                            Main.alert("Null", "Artwork is null");
                        }
                    } catch (IOException e) {
                        Main.alert(e.getCause().toString(), e.getMessage());
                    }
                } else {
                    Main.alert("", "Could not get MP3 tag.");
                }
            } else {
                Main.alert("", "MP3File is null");
            }
        } else {
            Main.alert("", "Song is null.");
        }
        return Optional.empty();
    }

    public static int getDuration(final Song song)  {
        if (song != null) {
            try {
                AudioFile audioFile = AudioFileIO.read(new File(String.valueOf(song.getSongPath())));

                return (audioFile.getAudioHeader().getTrackLength());
            } catch (Exception e) {
                Main.alert(e.getCause().toString(), e.getMessage());
            }
        } else {
            //todo
            return 0;
        }
        return 0;
    }
    public static boolean set(final Song song, final FieldKey fieldKey, final String string) throws TagException {
        if (song != null) {
            try {
                AudioFile audioFile = audioFile = AudioFileIO.read(new File(String.valueOf(song.getSongPath())));
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
                    AudioFile audioFile = AudioFileIO.read(new File(String.valueOf(song.getSongPath())));
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