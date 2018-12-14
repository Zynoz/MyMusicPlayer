package com.zynoz.views;

import com.zynoz.model.Song;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;


public class SongOverview extends TableView<Song> {
    private TableColumn<Song, String> title, artist;

    private Song songToPlay;
    private RootBorderPane rootBorderPane;

    public SongOverview(RootBorderPane rootBorderPane) {
        initComponents();
        addComponents();
        setFactories();
        setupListeners();
        this.rootBorderPane = rootBorderPane;
        title.setResizable(true);
        artist.setResizable(true);
    }

    private void setupListeners() {
        this.getSelectionModel().selectedItemProperty().addListener((((observable, oldValue, newValue) -> displaySong(newValue))));
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                //TODO context menu here
            } else if (event.getButton() == MouseButton.PRIMARY){
                if (event.getClickCount() > 1) {
                    setSongToPlay(getSelectionModel().getSelectedItem());
                    rootBorderPane.setSongDetails(getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void setSongToPlay(Song selectedItem) {
        songToPlay = selectedItem;
        System.out.println("path: " + selectedItem.getSongPath());
        rootBorderPane.getMediaAPI().playSong(selectedItem);
        System.out.println("clicked on " + songToPlay.getSongName());
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
}