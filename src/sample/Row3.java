package sample;

import javafx.beans.property.SimpleStringProperty;

public class Row3 {
    private final SimpleStringProperty cell1;
    private final SimpleStringProperty cell2;
    private final SimpleStringProperty cell3;

    public Row3(String cell1, String cell2, String cell3){
        this.cell1 = new SimpleStringProperty(cell1);
        this.cell2 = new SimpleStringProperty(cell2);
        this.cell3 = new SimpleStringProperty(cell3);
    }

    public String getCell2() {
        return cell2.get();
    }

    public SimpleStringProperty cell2Property() {
        return cell2;
    }

    public String getCell1() {
        return cell1.get();
    }

    public SimpleStringProperty cell1Property() {
        return cell1;
    }

    public String getCell3() {
        return cell3.get();
    }

    public SimpleStringProperty cell3Property() {
        return cell3;
    }
}
