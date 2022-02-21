package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class signUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpName;

    @FXML
    private PasswordField signUpLastName;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private PasswordField signUpPost;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private ImageView logoBnk;

    @FXML
    void initialize() {
    }
}
