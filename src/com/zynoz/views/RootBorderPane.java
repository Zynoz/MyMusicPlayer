package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Util;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

import java.lang.reflect.InvocationTargetException;

public class RootBorderPane extends BorderPane {
    private MenuBar menuBar;
    private Menu mFile, mEdit, mHelp;
    private MenuItem miReload, miSettings, miExit;
    private SongOverview songOverview;
    private RightVBox rightVBox;

    private MediaAPI mediaAPI;

    public RootBorderPane() {
        mediaAPI = MediaAPI.getInstance();
        initComponents();
        addComponents();
        addListeners();
        mediaAPI.setRootBorderPane(this);
    }

    private void initComponents() {
        menuBar = new MenuBar();

        mFile = new Menu("File");
        mEdit = new Menu("Edit");
        mHelp = new Menu("Help");

        miReload = new MenuItem("Reload songs");
        miSettings = new MenuItem("Settings");
        miExit = new MenuItem("Exit");

        songOverview = new SongOverview(this, mediaAPI);
        rightVBox = new RightVBox(this, mediaAPI);

        songOverview.setPrefWidth(300);
    }

    private void addComponents() {
        mFile.getItems().addAll(miSettings, new SeparatorMenuItem(), miExit);
        mEdit.getItems().addAll(miReload);
        menuBar.getMenus().addAll(mFile, mEdit, mHelp);

        setTop(menuBar);
        setLeft(songOverview);
        setRight(rightVBox);
        songOverview.setSongs();
    }

    private void addListeners() {
        miExit.setOnAction(event -> Platform.exit());
        miReload.setOnAction(event -> {
            mediaAPI.reload();
            songOverview.setSongs();
        });
        miSettings.setOnAction(event -> {
//            Settings settings = new Settings(this, mediaAPI);
//            Scene scene = new Scene(settings);
//            Stage stage = new Stage();
//            settings.setStage(stage);
//            stage.setResizable(false);
//            stage.setScene(scene);
//            stage.show();
            try {
                Util.open(Settings.class, this, mediaAPI, null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    public SongOverview getSongOverview() {
        return songOverview;
    }


    public void setSongInfos(Song selectedItem) {
        rightVBox.setSong(selectedItem);
    }

    public RightVBox getRightVBox() {
        return rightVBox;
    }
}