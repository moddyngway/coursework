package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class registor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label title;

    @FXML
    private URL location;

    @FXML
    private TextField username_field;

    @FXML
    private PasswordField passagain_field;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField firstname_field;

    @FXML
    private TextField usertype_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField lastname_field;

    @FXML
    private ImageView logoBnk;

    @FXML
    private Button InfoButton;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        registrationButton.setOnAction(actionEvent -> {
            System.out.println(Arrays.asList(new String[] {"hr", "director", "manager", "sale manager", "worker", "marketing"}).contains(usertype_field.getText()));
            if (!password_field.getText().equals(passagain_field.getText())) {
                title.setText("Passwords do not match!");
            } else if(!Arrays.asList(new String[] {"hr", "director", "manager", "sale manager", "worker", "marketing"}).contains(usertype_field.getText())){
                title.setText("There is no such type of account!");
            }else {
                databaseHandler.signUpUser(
                        firstname_field.getText().trim(), lastname_field.getText().trim(),
                        username_field.getText().trim(), password_field.getText().trim(), usertype_field.getText().trim());
            }
        });
        InfoButton.setOnAction(actionEvent -> {
            System.out.println("Выход");
            Controller.openNewScene("/sample/fxml/info.fxml", InfoButton, false);
        });
        
        assert username_field != null : "fx:id=\"username_field\" was not injected: check your FXML file 'registor.fxml'.";
        assert passagain_field != null : "fx:id=\"passagain_field\" was not injected: check your FXML file 'registor.fxml'.";
        assert registrationButton != null : "fx:id=\"registrationButton\" was not injected: check your FXML file 'registor.fxml'.";
        assert firstname_field != null : "fx:id=\"firstname_field\" was not injected: check your FXML file 'registor.fxml'.";
        assert usertype_field != null : "fx:id=\"usertype_field\" was not injected: check your FXML file 'registor.fxml'.";
        assert password_field != null : "fx:id=\"password_field\" was not injected: check your FXML file 'registor.fxml'.";
        assert lastname_field != null : "fx:id=\"lastname_field\" was not injected: check your FXML file 'registor.fxml'.";
        assert logoBnk != null : "fx:id=\"logoBnk\" was not injected: check your FXML file 'registor.fxml'.";
        assert InfoButton != null : "fx:id=\"InfoButton\" was not injected: check your FXML file 'registor.fxml'.";

    }

    public void openNewScene(String window, Button button){
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
