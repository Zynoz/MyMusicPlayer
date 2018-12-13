package com.zynoz;

import com.zynoz.controller.MediaManager;
import com.zynoz.exception.MediaManagerException;
import com.zynoz.util.Util;

public class Main {
    public static void main(String[] args) {
        MediaManager mediaManager = new MediaManager();
        Util util = Util.getInstance();

        try {
            mediaManager.loadMedia();
        } catch (MediaManagerException e) {
            System.err.println(e.getMessage());
        }
    }
}