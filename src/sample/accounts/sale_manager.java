package sample.accounts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import sample.Row3;
import sample.controllers.Controller;

public class sale_manager {

    @FXML
    private Button expensiveApartament;

    @FXML
    private Button cheapestApartament;

    @FXML
    private Button freeApartaments;

    @FXML
    private Pane Exit;

    @FXML
    private Button ExitButton;

    @FXML
    private Button soldApartaments;

    @FXML
    private Button BackMenu;

    @FXML
    private TableView<Row3> tableview;

    private final ObservableList<Row3> data =
            FXCollections.observableArrayList();

    @FXML
    void initialize() {
        expensiveApartament.setOnAction(actionEvent -> {
            String query = "SELECT address, price, \"\" FROM course.apartments\n" +
                    "where price = (select max(price) from course.apartments)";
            Controller.table3(new String[] {"address", "price", ""}, data, tableview, 2, query);
        });

        cheapestApartament.setOnAction(actionEvent -> {
            String query = "SELECT address, price, \"\" FROM course.apartments\n" +
                    "where price = (select min(price) from course.apartments)";
            Controller.table3(new String[] {"address", "price", ""}, data, tableview, 2, query);
        });

        freeApartaments.setOnAction(actionEvent -> {
            String query = "SELECT address, price, \"\" FROM course.apartments\n" +
                    "WHERE sold = 0";
            Controller.table3(new String[] {"address", "price", ""}, data, tableview, 2, query);
        });

        soldApartaments.setOnAction(actionEvent -> {
            String query = "SELECT address, price, \"\" FROM course.apartments\n" +
                    "WHERE sold = 1";
            Controller.table3(new String[] {"address", "price", ""}, data, tableview, 2, query);
        });

        ExitButton.setOnAction(actionEvent -> {
            ExitButton.getScene().getWindow().hide();
        });

        BackMenu.setOnAction(actionEvent -> {
            Controller.openNewScene("/sample/fxml/sample.fxml", BackMenu, true);
        });
    }

}
