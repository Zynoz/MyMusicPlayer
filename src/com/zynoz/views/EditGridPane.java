package com.zynoz.views;

import com.zynoz.Main;
import com.zynoz.controller.MediaAPI;
import com.zynoz.model.Song;
import com.zynoz.util.Tags;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jaudiotagger.tag.FieldKey;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EditGridPane extends GridPane {
    private Label title, artist;
    private ImageView artcover;
    private TextField textTitle, textArtist;
    private Button ok, cancel;

    private URL resource = getClass().getResource("../resources/images/default.png");
    private File selectedFile;
    private String newTitle, newArtist;
    private Image newImage;

    private RootBorderPane rootBorderPane;
    private MediaAPI mediaAPI;
    private Song songToEdit;
    private Stage stage;


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

    @SuppressWarnings("ConstantConditions")
    private void initComponents() {
        title = new Label("Title: ");
        artist = new Label("Artist: ");
        if (Tags.getCover(songToEdit) != null) {
            artcover = new ImageView(SwingFXUtils.toFXImage(Tags.getCover(songToEdit), null));
            System.out.println("image present");
        } else {
            artcover = new ImageView(String.valueOf(resource));
            System.out.println("image not present");
        }
        System.out.println();
        artcover.setFitHeight(50);
        artcover.setFitWidth(50);
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
        add(artcover, 1, 2);

        add(ok, 0, 3);
        add(cancel, 1, 3);
    }

    private void addListeners() {
        //TODO add listeners.
        artcover.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select new artcover.");
                fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("png", "jpg"));
                File file = fileChooser.showOpenDialog(null);
                if (file != null && file.isFile()) {
                    if (file.toString().endsWith(".png") || file.toString().endsWith(".jpg") || file.toString().endsWith(".jpeg")) {
                        selectedFile = file;
                        artcover.setImage(new Image(file.toURI().toString()));
                    } else {
                        Main.alert("Not valid","No file selected.");
                    }
                } else {
                    Main.alert("Not valid", "Selected file is not an image.");
                }
            }
        });

        ok.setOnAction(event -> {
            if (!textTitle.getText().equals("") && !textArtist.getText().equals("")) {
                Tags.set(songToEdit, FieldKey.TITLE, textTitle.getText());
                Tags.set(songToEdit, FieldKey.ARTIST, textArtist.getText());
                try {
                    if (selectedFile != null) {
                        BufferedImage bufferedImage = ImageIO.read(selectedFile);
                        newImage = SwingFXUtils.toFXImage(bufferedImage, null);
                        Tags.setCover(songToEdit, selectedFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.close();
            }
        });

        cancel.setOnAction(event -> stage.close());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}