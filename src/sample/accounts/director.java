package sample.accounts;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import sample.Row3;
import sample.controllers.Controller;

public class director {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button customReach;

    @FXML
    private Button listOfWorkers;

    @FXML
    private Button marketingCategories;

    @FXML
    private Button requiredBudget;

    @FXML
    private Button changeSalary;

    @FXML
    private Button constructionEquipment;

    @FXML
    private Button ExitButton;

    @FXML
    private Button BackMenu1;

    @FXML
    private TableView<Row3> tableview;

    @FXML
    private Button BackMenu;

    private final ObservableList<Row3> data =
            FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert customReach != null : "fx:id=\"customReach\" was not injected: check your FXML file 'director.fxml'.";
        assert marketingCategories != null : "fx:id=\"marketingCategories\" was not injected: check your FXML file 'director.fxml'.";
        assert requiredBudget != null : "fx:id=\"requiredBudget\" was not injected: check your FXML file 'director.fxml'.";
        assert changeSalary != null : "fx:id=\"changeSalary\" was not injected: check your FXML file 'director.fxml'.";
        assert constructionEquipment != null : "fx:id=\"constructionEquipment\" was not injected: check your FXML file 'director.fxml'.";
        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'director.fxml'.";
        assert BackMenu1 != null : "fx:id=\"BackMenu1\" was not injected: check your FXML file 'director.fxml'.";
        assert tableview != null : "fx:id=\"tableview\" was not injected: check your FXML file 'director.fxml'.";
        assert BackMenu != null : "fx:id=\"BackMenu\" was not injected: check your FXML file 'director.fxml'.";

        customReach.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/map.fxml", customReach, false);
        });

        requiredBudget.setOnAction(actionEvent -> {
            String query = "SELECT sum(salary), (Select sum(budget) FROM course.marketing_category), \"\"  FROM course.users\n";
            Controller.table3(new String[] {"Salary budget", "Marketing budget", ""}, data, tableview, 2, query);
        });

        marketingCategories.setOnAction(actionEvent -> {
            String query = "SELECT name, budget, \"\" FROM course.marketing_category;";
            Controller.table3(new String[] {"Category", "Budget", ""}, data, tableview,
                    2, query);
        });

        listOfWorkers.setOnAction(actionEvent -> {
            String query = "SELECT concat(firstname, \" \", lastname) as name, type, salary FROM course.users";
            Controller.table3(new String[] {"Name", "Type", "Salary"}, data, tableview, 3, query);
        });

        constructionEquipment.setOnAction(actionEvent -> {
            String query = "SELECT name, quantity, \"\" FROM course.equipment";
            Controller.table3(new String[] {"Name", "Quantity", ""}, data, tableview, 2, query);
        });

        ExitButton.setOnAction(actionEvent -> {
            ExitButton.getScene().getWindow().hide();
        });

        changeSalary.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/salary.fxml", changeSalary, false);
        });

        BackMenu.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/sample.fxml", BackMenu, true);
        });

    }
}

