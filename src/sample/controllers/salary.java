package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Row3;

public class salary {

    @FXML
    private Button enterButton;

    @FXML
    private TableView<Row3> tableview;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField salaryField;

    @FXML
    private Button showButton;

    private final ObservableList<Row3> data =
            FXCollections.observableArrayList();

    @FXML
    void initialize() throws InterruptedException {

        showButton.setOnAction(actionEvent -> {
            String query = "SELECT username, type, salary FROM course.users";
            Controller.table3(new String[] {"Username", "Type", "Salary"}, data, tableview, 3, query);
        });

        enterButton.setOnAction(actionEvent -> {
            String username = usernameField.getText();
            String salary = salaryField.getText();

            String query = "UPDATE course.users\n" +
                    "SET salary = " + salary + "\n" +
                    "WHERE username = \"" + username + "\"";

            DatabaseHandler databaseHandler = new DatabaseHandler();

            databaseHandler.update(query);
        });

    }
}
