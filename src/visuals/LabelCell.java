package visuals;

import javafx.scene.control.Label;

public class LabelCell extends Cell {

    public LabelCell(String id, int empId) {
        super(id);

        Label view = new Label(id);
        view.setText(String.valueOf(empId));
        setView(view);

    }

}