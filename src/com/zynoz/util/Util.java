package com.zynoz.util;

import com.zynoz.controller.MediaAPI;
import com.zynoz.exception.CommonException;
import com.zynoz.model.Song;
import com.zynoz.views.EditGridPane;
import com.zynoz.views.RootBorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Random;

public class Util {
    private static Util instance;
    private final File configFile = new File(getUserDir() + "cfg.properties");

    private File getUserDir() {
        return new File(System.getProperty("user.home") + File.separator + ".mmp" + File.separator);
    }

    public static final String mediaDirectory = System.getProperty("user.home") + File.separator + "Music";

    private Util() {}

    public static synchronized Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public static int getRandomSong(int size) {
        Random random = new Random();
        return random.nextInt(size - 1);
    }

    public static void openEditDialogue(RootBorderPane rootBorderPane, MediaAPI mediaAPI, Song song) {
        EditGridPane editGridPane = new EditGridPane(rootBorderPane, mediaAPI, song);
        Scene scene = new Scene(editGridPane);
        Stage stage = new Stage();
        editGridPane.setStage(stage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void open(Class klasse, RootBorderPane rootBorderPane, MediaAPI mediaAPI, Song song) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object view = Object.class.getConstructor(RootBorderPane.class, MediaAPI.class, Song.class).newInstance(rootBorderPane, mediaAPI, song);
        Scene scene = new Scene((Parent) view);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,
                        durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }

    public void createProperties() throws CommonException {
        Properties properties = new Properties();

        //TODO set properties accordingly.
        //properties.setProperty("", "");

        try {
            FileWriter fileWriter = new FileWriter(configFile);
            properties.store(fileWriter, "MMP config");
            fileWriter.close();
        } catch (IOException e) {
            throw new CommonException(e.getClass() + ": " + e.getMessage());
        }
    }

    public void loadProperties() throws CommonException {
        try {
            FileReader fileReader = new FileReader(configFile);

            Properties properties = new Properties();
            properties.load(fileReader);

            //TODO load properties accordingly.
            //properties.getProperty("");

            fileReader.close();

        } catch (IOException e) {
            throw new CommonException(e.getClass() + ": " + e.getMessage());
        }
    }
}