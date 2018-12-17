package com.zynoz.views;

import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Util;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;


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
        title.setResizable(true);
        artist.setResizable(true);
    }

    private void addComponents() {
        //noinspection unchecked
        getColumns().addAll(title, artist);
        contextMenu.getItems().addAll(miPlay, miEdit, miRemove, miDelete);
        setContextMenu(contextMenu);
        setMinWidth(title.getPrefWidth() + artist.getPrefWidth());
        setPrefWidth(400);
        setMaxWidth(400);
        setMinWidth(400);
        title.setReorderable(false);
        artist.setReorderable(false);
        setColumnResizePolicy(param -> true);
    }

    private void setupListeners() {
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                //TODO context menu here
            } else if (event.getButton() == MouseButton.PRIMARY){
                if (event.getClickCount() > 1) {
                    setSongToPlay(getSelectionModel().getSelectedItem());
                    rootBorderPane.setSongInfos(getSelectionModel().getSelectedItem());
                }
            }
        });

        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setSongToPlay(getSelectionModel().getSelectedItem());
                rootBorderPane.setSongInfos(getSelectionModel().getSelectedItem());
            }
        });


        miPlay.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            mediaAPI.playSong(song);
            rootBorderPane.setSongInfos(song);
        });
        miEdit.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            Util.openEdit(rootBorderPane, mediaAPI, song);
            setSongs();
        });
        miRemove.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            mediaAPI.removeSong(song);
            setSongs();
        });
        miDelete.setOnAction((ActionEvent event) -> {
            Song song = getSelectionModel().getSelectedItem();
            mediaAPI.deleteSong(song);
            mediaAPI.reload();
            setSongs();
        });
    }

    private void setSongToPlay(Song selectedItem) {
        songToPlay = selectedItem;
        System.out.println("path: " + selectedItem.getSongPath());
        mediaAPI.playSong(selectedItem);
        System.out.println("clicked on " + songToPlay.getSongName());
    }

    private void setFactories() {
        title.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSongName()));
        artist.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSongArtist()));
    }

    public void setSongs() {
        getItems().setAll(mediaAPI.getSongList());
        System.out.println("set songs");
    }
}