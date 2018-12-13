package com.zynoz.ui;

import com.zynoz.model.Song;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;


public class Overview extends TableView<Song> {
    private TableColumn<Song, String> title, artist;

    private Song songToPlay;

    public Overview() {
        initComponents();
        addComponents();
        setFactories();
        setupListeners();

    }

    private void setupListeners() {
        this.getSelectionModel().selectedItemProperty().addListener((((observable, oldValue, newValue) -> displaySong(newValue))));
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                //TODO context menu here
            } else if (event.getButton() == MouseButton.PRIMARY){
                if (event.getClickCount() > 1) {
                    setSongToPlay(getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void setSongToPlay(Song selectedItem) {
        songToPlay = selectedItem;
        System.out.println("clicked on " + selectedItem.getSongName());
    }

    private void displaySong(Song song) {

    }

    private void initComponents() {
        title = new TableColumn<>("Title");
        artist = new TableColumn<>("Arist");
    }

    private void addComponents() {
        //noinspection unchecked
        getColumns().addAll(title, artist);
    }

    private void setFactories() {
        title.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSongName()));
        artist.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSongArtist()));
    }

    public void setSongs(ObservableList<Song> songs) {
        getItems().setAll(songs);
    }

    private void observerSongAndUpdate() {

    }
}