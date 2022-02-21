package sample.accounts;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.User;
import sample.controllers.Controller;
import sample.controllers.DatabaseHandler;
import sample.Row3;

public class manager {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ListEmployees;

    @FXML
    private Button ListCategories;

    @FXML
    private Button CoverageArea;

    @FXML
    private Button ListInstructions;

    @FXML
    private TableView<Row3> tableview;

    @FXML
    private Button ExitButton;
    @FXML
    private Button BackMenu;


    private final ObservableList<Row3> data =
            FXCollections.observableArrayList();

    @FXML
    void initialize(){
        ListEmployees.setOnAction(actionEvent -> {
            String query = "SELECT concat(firstname, \" \", lastname) as name, username, type FROM course.users";
            Controller.table3(new String[] {"Name", "Username", "Type"}, data, tableview, 3, query);
        });

        ListCategories.setOnAction(actionEvent -> {
            String query = "SELECT o.instructions as Instructions, u.username as Username, u.type as Type\n" +
                    "FROM orders o\n" +
                    "INNER JOIN users u\n" +
                    "ON u.idusers = o.userid\n" +
                    "WHERE o.isDone = 0 and o.userid = " + User.user_id;
            Controller.table3(new String[]{"Instruction", "Username", "Type"}, data, tableview, 3, query);
        });

        ListInstructions.setOnAction(actionEvent -> {
            String query = "SELECT o.instructions as Instructions, u.username as Username, u.type as Type\n" +
                    "FROM orders o\n" +
                    "INNER JOIN users u\n" +
                    "ON u.idusers = o.userid\n" +
                    "WHERE o.isDone = 0";
            Controller.table3(new String[]{"Instruction", "Username", "Type"}, data, tableview, 3, query);
        });
        ExitButton.setOnAction(actionEvent -> {
            tableview.getScene().getWindow().hide();
            System.out.println("Выход");
        });
        CoverageArea.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/map.fxml", CoverageArea, false);
        });
        BackMenu.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/sample.fxml", BackMenu, true);
        });

    }
}
