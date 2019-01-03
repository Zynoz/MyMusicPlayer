package com.zynoz.util;

import com.zynoz.controller.MediaAPI;
import com.zynoz.exception.CommonException;
import com.zynoz.model.Song;
import com.zynoz.server.Server;
import com.zynoz.views.BaseSettings;
import com.zynoz.views.EditGridPane;
import com.zynoz.views.RootBorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Util {
    private static final File configFile = new File(getUserDir() + "cfg.properties");
    private int port;
    private static Thread t;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stop");
            //noinspection deprecation
            t.stop();
        }));
    }

    @NotNull
    @Contract(" -> new")
    private static File getUserDir() {
        return new File(System.getProperty("user.home") + File.separator + ".mmp" + File.separator);
    }

    public static final String mediaDirectory = System.getProperty("user.home") + File.separator + "Music";

    public static int getRandomSong(int size) {
        Random random = new Random();
        return random.nextInt(size - 1);
    }

    public static void openEdit(RootBorderPane rootBorderPane, MediaAPI mediaAPI, Song song) {
        EditGridPane editGridPane = new EditGridPane(rootBorderPane, mediaAPI, song);
        Scene scene = new Scene(editGridPane);
        Stage stage = new Stage();
        editGridPane.setStage(stage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void openSettings(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        BaseSettings baseSettings = new BaseSettings(rootBorderPane, mediaAPI);
        Scene scene = new Scene(baseSettings, 600, 400);
        Stage stage = new Stage();
        baseSettings.setStage(stage);
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @SuppressWarnings("Duplicates")
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

    public static void createProperties() throws CommonException {
        Properties properties = new Properties();

        //TODO set properties accordingly.
        properties.setProperty("defaultPort", "6666");
        properties.setProperty("port", getPort());

        try {
            FileWriter fileWriter = new FileWriter(configFile);
            properties.store(fileWriter, "MMP config");
            fileWriter.close();
        } catch (IOException e) {
            throw new CommonException(e.getClass() + ": " + e.getMessage());
        }
    }

    public static void loadProperties() throws CommonException {
        try {
            FileReader fileReader = new FileReader(configFile);

            Properties properties = new Properties();
            properties.load(fileReader);

            //TODO load properties accordingly.
            setPort(properties.getProperty("port"));

            fileReader.close();

        } catch (IOException e) {
            throw new CommonException(e.getClass() + ": " + e.getMessage());
        }
    }

    public static void startServer(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        t = new Thread(() -> new Server(rootBorderPane, mediaAPI, Integer.valueOf(getPort())));
        t.start();
    }

    public static String getPort() {
        return "6666";
    }

    public static void setPort(String text) {

    }
}