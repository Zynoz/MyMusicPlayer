package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;

public class EditGridPane extends GridPane {
    private Label title, artist;
    private ImageView artcover;
    private TextField textTitle, textArtist;
    private Button ok, cancel;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Song songToEdit;

    private URL resource = getClass().getResource("../resources/images/default.png");

    public EditGridPane(RootBorderPane rootBorderPane, MediaAPI mediaAPI, Song song) {
        this.rootBorderPane = rootBorderPane;
        this.mediaAPI = mediaAPI;
        songToEdit = song;
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(0, 10, 0, 10));
        initComponents();
        addComponents();
        addListeners();
    }

    private void initComponents() {
        title = new Label("Title: ");
        artist = new Label("Artist: ");
        //artcover = new ImageView(String.valueOf(Tags.getCover(songToEdit)));
        textTitle = new TextField(songToEdit.getSongName());
        textArtist   = new TextField(songToEdit.getSongArtist());
        ok = new Button("Save");
        cancel = new Button("Cancel");
    }

    private void addComponents() {
        add(title, 0, 0);
        add(textTitle, 1, 0);
        add(artist, 0, 1);
        add(textArtist, 1, 1);
        //add(artcover, 0, 2);

        add(ok, 0, 3);
        add(cancel, 1, 3);
    }

    private void addListeners() {
        //TODO add listeners.
    }
}