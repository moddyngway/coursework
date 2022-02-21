package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import sample.User;

public class workspace {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

    @FXML
    private AnchorPane parent;

    @FXML
    private Label title;

    @FXML
    void initialize(){
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'marketing.fxml'.";
        AnchorPane subtitle = new AnchorPane();
        title.setText(title.getText() + " " + User.user_firstname);
        if (User.user_type.equals("hr")){

            parent.getChildren().add(subtitle);
            subtitle.setLayoutX(15);
            subtitle.setLayoutY(65);
            subtitle.setMinSize(50, 50);
            subtitle.setStyle("-fx-background-color: #8B0000");
        }

        exit.setOnAction(actionEvent -> {
            subtitle.setMinSize(0, 0);
        });


    }
}
