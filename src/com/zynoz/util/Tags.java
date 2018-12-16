package com.zynoz.util;

import com.zynoz.Main;
import com.zynoz.exception.TagException;
import com.zynoz.model.Song;
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

    public static BufferedImage getCover(final Song song) {
        if (song != null) {
            try {
                MP3File mp3File = (MP3File) AudioFileIO.read(new File(song.getSongPath()));
                if (mp3File.getTag() == null) {
                    return null;
                }
                Artwork artwork = mp3File.getTag().getFirstArtwork();
                return artwork == null ? null : (BufferedImage) artwork.getImage();
            } catch (CannotReadException | IOException | org.jaudiotagger.tag.TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
                Main.alert(e.getCause().toString(), e.getMessage());
                return null;
            }
        } else {
            Main.alert("", "Song is null.");
            return null;
        }
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
    public static boolean set(final Song song, final FieldKey fieldKey, final String string) {
        if (song != null) {
            try {
                AudioFile audioFile = audioFile = AudioFileIO.read(new File(String.valueOf(song.getSongPath())));
                audioFile.getTag().setField(fieldKey, string);
                audioFile.commit();
                return true;
            } catch (Exception e) {
                Main.alert(e.getCause().toString(), e.getMessage());
            }
        } else {
            Main.alert("", "Song is null");
        }
        return false;
    }

    public static boolean setCover(final Song song, final File image) {
        if (song != null) {
            if (image != null) {
                try {
                    AudioFile audioFile = AudioFileIO.read(new File(String.valueOf(song.getSongPath())));
                    Artwork artwork = ArtworkFactory.createArtworkFromFile(image);
                    if (artwork != null) {
                        if (audioFile != null) {
                            audioFile.getTag().addField(artwork);
                            audioFile.getTag().setField(artwork);
                            audioFile.commit();
                            return true;
                        } else {
                            System.out.println("audiofile is null");
                            return false;
                        }
                    } else {
                        System.out.println("Artwork is null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Main.alert(e.getCause().toString(), e.getMessage());
                }
            } else {
                Main.alert("", "Image is null");
            }
        } else {
            Main.alert("", "Song is null");
        }
        return false;
    }
}