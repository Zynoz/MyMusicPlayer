package com.zynoz.util;

import com.zynoz.exception.CommonException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public static int  getRandomeSong(int size) {
        Random random = new Random();
        return random.nextInt(size - 1);
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