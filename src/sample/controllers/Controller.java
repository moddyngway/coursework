package sample.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Row3;
import sample.User;

public class Controller {
    @FXML
    private Label title;

    @FXML
    private AnchorPane main;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignupButton;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private ImageView logoBnk;

    @FXML
    private Button InfoButton;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        authSignupButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText();
            String passText = password_field.getText();
            if(!loginText.equals("") && !passText.equals("") && databaseHandler.checkAuth(loginText, passText)){
                System.out.println(User.user_firstname);
                System.out.println(User.user_lastname);
                System.out.println(User.user_type);
                String path = "/sample/fxml/" + User.user_type + ".fxml";
                System.out.println(path);
                openNewScene(path, authSignupButton, true);
            } else {
                title.setText("Invalid Email or password!");
            }
        });

        registrationButton.setOnAction(actionEvent -> {
            openNewScene("/sample/fxml/registor.fxml",registrationButton,true);
        });



        InfoButton.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/info.fxml", InfoButton, false);
        });
        assert authSignupButton != null : "fx:id=\"authSignupButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert login_field != null : "fx:id=\"login_field\" was not injected: check your FXML file 'sample.fxml'.";
        assert password_field != null : "fx:id=\"password_field\" was not injected: check your FXML file 'sample.fxml'.";
        assert logoBnk != null : "fx:id=\"logoBnk\" was not injected: check your FXML file 'sample.fxml'.";
        assert InfoButton != null : "fx:id=\"InfoButton\" was not injected: check your FXML file 'sample.fxml'.";

    }

    public static void openNewScene(String window, Button button, boolean hide){
        if (hide)
            button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller.class.getResource(window));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("ADAMANTIUM CORPORATION");
        stage.setScene(new Scene(root));
        stage.show();
    }



    public static void table3(String[] text, ObservableList<Row3> data, TableView<Row3> tableview, int numberCol, String query){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        data.clear();
        tableview.setItems(data);

        double w = tableview.getWidth() / numberCol;

        TableColumn<Row3, String> [] cols = new TableColumn[3];

        double[] widths = new double[] {0d, 0d, 0d};
        int wid = 0;

        for (int i = 0; i < numberCol; i++){
            widths[wid++] = w;
        }

        for (int i = 1; i <= 3; i++){
            String cell = "cell" + i;
            TableColumn<Row3, String> catcol = new TableColumn<Row3, String>("Category");
            catcol.setCellValueFactory(new PropertyValueFactory<Row3, String>(cell));
            catcol.setMinWidth(widths[i-1]);
            catcol.setMaxWidth(widths[i-1]);

            cols[i - 1] = catcol;
        }

        tableview.getColumns().clear();
        tableview.getColumns().addAll(cols);

        ArrayList<String[]> table = databaseHandler.selectQuery(query);

        for (int i = 0; i < 3; i++){
            cols[i].setText(text[i]);
        }

        tableview.setEditable(true);

        for (String[] row : table) {
            if (row[0] == null){
                break;
            }
            Row3 cat = new Row3(row[0], row[1], row[2]);
            data.add(cat);
        }

        tableview.setEditable(false);
    }
}
