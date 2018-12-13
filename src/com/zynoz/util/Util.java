package com.zynoz.util;

import java.io.File;

public class Util {
    private static Util instance;
    public static final String mediaDirectory = System.getProperty("user.home") + File.separator + "Music";

    private Util() {}

    public static synchronized Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
}