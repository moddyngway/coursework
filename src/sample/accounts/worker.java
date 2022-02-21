package sample.accounts;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import sample.Row3;
import sample.User;
import sample.controllers.Controller;

public class worker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Row3> tableview;

    @FXML
    private Button assignedTasks;

    @FXML
    private Button completedInstructions;

    @FXML
    private Button existingTasks;

    @FXML
    private Button showSalary;

    @FXML
    private Button ExitButton;

    @FXML
    private Button BackMenu;

    private final ObservableList<Row3> data =
            FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert BackMenu != null : "fx:id=\"BackMenu\" was not injected: check your FXML file 'worker.fxml'.";
        assert tableview != null : "fx:id=\"tableview\" was not injected: check your FXML file 'worker.fxml'.";
        assert assignedTasks != null : "fx:id=\"assignedTasks\" was not injected: check your FXML file 'worker.fxml'.";
        assert completedInstructions != null : "fx:id=\"completedInstructions\" was not injected: check your FXML file 'worker.fxml'.";
        assert existingTasks != null : "fx:id=\"existingTasks\" was not injected: check your FXML file 'worker.fxml'.";
        assert showSalary != null : "fx:id=\"showSalary\" was not injected: check your FXML file 'worker.fxml'.";
        assert ExitButton != null : "fx:id=\"exit\" was not injected: check your FXML file 'worker.fxml'.";

        assignedTasks.setOnAction(actionEvent -> {
            String query = "SELECT o.instructions as Instructions, u.username as Username, \"\"\n" +
                    "FROM orders o\n" +
                    "INNER JOIN users u\n" +
                    "ON u.idusers = o.userid\n" +
                    "WHERE o.isDone = 0\n" +
                    "AND u.idusers = " + User.user_id;

            Controller.table3(new String[] {"Instruction", "Worker", ""}, data, tableview,
                    2, query);
        });

        completedInstructions.setOnAction(actionEvent -> {
            String query = "SELECT o.instructions as Instructions, u.username as Username, \"\"\n" +
                    "FROM orders o\n" +
                    "INNER JOIN users u\n" +
                    "ON u.idusers = o.userid\n" +
                    "WHERE o.isDone = 2\n" +
                    "AND u.idusers = " + User.user_id;

            Controller.table3(new String[] {"Instruction", "Worker", ""}, data, tableview,
                    2, query);
        });

        existingTasks.setOnAction(actionEvent -> {
            String query = "SELECT o.instructions as Instructions, u.username as Username, \"\"\n" +
                    "FROM orders o\n" +
                    "INNER JOIN users u\n" +
                    "ON u.idusers = o.userid\n" +
                    "WHERE o.isDone = 1\n" +
                    "AND u.idusers = " + User.user_id;

            Controller.table3(new String[] {"Instruction", "Worker", ""}, data, tableview,
                    2, query);
        });

        showSalary.setOnAction(actionEvent -> {
            String query = "SELECT concat(firstname, \" \", lastname), username, salary FROM course.users\n" +
                    "where idusers = " + User.user_id;
            Controller.table3(new String[] {"Name", "Username", "Salary"}, data, tableview, 3, query);
        });

        BackMenu.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/sample.fxml", BackMenu, true);
        });
        ExitButton.setOnAction(actionEvent -> {
            tableview.getScene().getWindow().hide();
            System.out.println("Выход");
        });
    }
}
