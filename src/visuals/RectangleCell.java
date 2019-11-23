package visuals;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import visuals.Cell;

public class RectangleCell extends Cell {

	private int empId;
	
    public RectangleCell(String id, int empId) {
        super(id);
        this.empId = empId;

        Rectangle view = new Rectangle( 50,50);

        view.setStroke(Color.BLACK);
        view.setFill(Color.BLACK);

        setView( view);

    }

	public RectangleCell(String id) {
		  super(id);
		  Rectangle view = new Rectangle( 50,50);

	        view.setStroke(Color.BLACK);
	        view.setFill(Color.BLACK);

	        setView( view);
	}

	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

}