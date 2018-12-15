package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


public class SongOverview extends TableView<Song> {
    private TableColumn<Song, String> title, artist;

    private Song songToPlay;
    private MediaAPI mediaAPI;
    private RootBorderPane rootBorderPane;

    private ContextMenu contextMenu;
    private MenuItem miPlay, miEdit, miRemove, miDelete;

    public SongOverview(RootBorderPane rootBorderPane, MediaAPI mediaAPI) {
        this.mediaAPI = mediaAPI;
        this.rootBorderPane = rootBorderPane;
        initComponents();
        addComponents();
        setFactories();
        setupListeners();
        title.setResizable(true);
        artist.setResizable(true);
    }

    private void initComponents() {
        title = new TableColumn<>("Title");
        artist = new TableColumn<>("Arist");
        contextMenu = new ContextMenu();
        miPlay = new MenuItem("Play");
        miEdit = new MenuItem("Edit");
        miRemove = new MenuItem("Remove");
        miDelete = new MenuItem("Delete");

        title.setEditable(true);
        artist.setEditable(true);
    }

    private void addComponents() {
        //noinspection unchecked
        getColumns().addAll(title, artist);
        contextMenu.getItems().addAll(miPlay, miEdit, miRemove, miDelete);
        setContextMenu(contextMenu);
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
                    rootBorderPane.setSongInfos(getSelectionModel().getSelectedItem());
                }
            }
        });


        miPlay.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            mediaAPI.playSong(song);
            System.out.println("play song " + song.toString());
        });
        miEdit.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            EditGridPane editGridPane = new EditGridPane(rootBorderPane, mediaAPI, song);
            Scene scene = new Scene(editGridPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            System.out.println("edit song " + song.toString());
        });
        miRemove.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            mediaAPI.removeSong(song);
            System.out.println("remove song " + song.toString());
        });
        miDelete.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            mediaAPI.deleteSong(song);
            System.out.println("delete song " + song.toString());
        });
    }

    private void setSongToPlay(Song selectedItem) {
        songToPlay = selectedItem;
        System.out.println("path: " + selectedItem.getSongPath());
        mediaAPI.playSong(selectedItem);
        System.out.println("clicked on " + songToPlay.getSongName());
    }

    private void displaySong(Song song) {

    }

    private void setFactories() {
        title.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSongName()));
        artist.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSongArtist()));
    }

    public void setSongs() {
        getItems().setAll(mediaAPI.getSongList());
    }
}