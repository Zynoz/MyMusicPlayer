package com.zynoz.util;

import com.zynoz.exception.CommonException;
import com.zynoz.exception.SongException;
import com.zynoz.model.Song;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

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

    public Song getRandomSong() throws SongException {
        //TODO
        return new Song(Paths.get(""));
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