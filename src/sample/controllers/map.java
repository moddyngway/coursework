package sample.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

public class map {

    @FXML
    private ImageView ChuyMap;

    @FXML
    private Button Bishkek;

    @FXML
    private ImageView BatkenMap;

    @FXML
    private ImageView IssykKulMap;

    @FXML
    private ImageView JalalAbaMap;

    @FXML
    private ImageView NarynMap;

    @FXML
    private ImageView TalasMap;

    @FXML
    private ImageView OshMap;

    @FXML
    private Button Talas;

    @FXML
    private Button Batken;

    @FXML
    private Button Osh;

    @FXML
    private Button JalalAbad;

    @FXML
    private Button Naryn;

    @FXML
    private Button IssykKul;

    @FXML
    private Label text;

    @FXML
    void initialize() {
        Bishkek.setOnAction(actionEvent -> {
            changeLabel("Bishkek");
        });

        IssykKul.setOnAction(actionEvent -> {
            changeLabel("Issyk-Kul");
        });

        Talas.setOnAction(actionEvent -> {
            changeLabel("Talas");
        });

        Naryn.setOnAction(actionEvent -> {
            changeLabel("Naryn");
        });

        Batken.setOnAction(actionEvent -> {
            changeLabel("Batken");
        });

        JalalAbad.setOnAction(actionEvent -> {
            changeLabel("Jalal-Abad");
        });

        Osh.setOnAction(actionEvent -> {
            changeLabel("Osh");
        });
    }

    private void changeLabel(String name){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query = "SELECT name, clients,\n" +
                "ROUND(clients/(SELECT sum(clients) FROM course.coverage_area) * 100, 2) as \"%\"\n" +
                "FROM course.coverage_area\n" +
                "WHERE name = \"" + name + "\"";
        ArrayList<String[]> table = databaseHandler.selectQuery(query);
        String[] row = table.get(0);
        text.setText("%s: %s clients (%s%s of total)".formatted(row[0], row[1], row[2], "%"));
    }
}