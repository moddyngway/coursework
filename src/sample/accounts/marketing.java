package sample.accounts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.controllers.Controller;
import sample.controllers.DatabaseHandler;
import sample.Row3;

public class marketing {

    @FXML
    private TableView<Row3> tableview;

    @FXML
    private Button Customer_reach;

    @FXML
    private Button List_of_categories;

    @FXML
    private Button Total_budget;

    @FXML
    private Button Promotion;

    @FXML
    private Button Exit_button;

    @FXML
    private Button Allocated_budget;
    @FXML
    private Button BackMenu;

    private final ObservableList<Row3> data =
            FXCollections.observableArrayList();

    @FXML
    void initialize(){

        List_of_categories.setOnAction(actionEvent -> {
            String query = "SELECT name, amountUsers, \"\" FROM course.marketing_category;";
            Controller.table3( new String[] {"Category", "Amount of Users", ""}, data, tableview,
                    2, query);
        });

        Allocated_budget.setOnAction(actionEvent -> {
            String query = "SELECT name, budget, \"\" FROM course.marketing_category;";
            Controller.table3(new String[] {"Category", "Budget", ""}, data, tableview,
                                2, query);
        });

        Customer_reach.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/map.fxml", Customer_reach, false);
        });

        Total_budget.setOnAction(actionEvent -> {
            String query = "select sum(budget) as \"Total budget\", \"\", \"\" from course.marketing_category;";
            Controller.table3(new String[] {"Total Budget", "", ""}, data, tableview,
                    1, query);
        });

        Exit_button.setOnAction(actionEvent -> {
            tableview.getScene().getWindow().hide();
        });

        BackMenu.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/sample.fxml", BackMenu, true);
        });
    }

}
