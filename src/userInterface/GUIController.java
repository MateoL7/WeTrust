package userInterface;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.WeTrust;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;

public class GUIController {

	@FXML
	private Button createBtt;

	@FXML
	private TextField numOfEmp;

	@FXML
	private Label lbMatrix;

	private boolean MorL;

	private WeTrust wt;

	@FXML
	public void initialize() {
		wt = new WeTrust();
		try {
			wt.loadEmployees();
		} catch (IOException e) {
		} catch (EmployeeAlreadyCreatedException e) {
		}
	}

	/**
	 * @return the morL
	 */
	public boolean isMorL() {
		return MorL;
	}

	/**
	 * @param morL the morL to set
	 */
	public void setMorL(boolean morL) {
		MorL = morL;
	}


	public void generate() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Data Structure");
			alert.setHeaderText("Choose the data structure you want");
			alert.setContentText("Data structures available");

			ButtonType buttonTypeOne = new ButtonType("Matrix");
			ButtonType buttonTypeTwo = new ButtonType("List");
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
			Optional<ButtonType> resulType = alert.showAndWait();
			if(resulType.get() == buttonTypeOne) {
				MorL = true;
			}else if(resulType.get() == buttonTypeTwo) {
				MorL = false;
			}
			int num = Integer.parseInt(numOfEmp.getText());
			wt.chooseS(MorL, num);
			wt.generateEmployees(num);
			wt.generateEmployeesTrust();
			wt.loademployeesTrust();
			double matrix[][] = wt.getemployeesTrust();
			String mat = "";
			for (int x=0; x < matrix.length; x++) {
				for (int y=0; y < matrix[x].length; y++) {
					if(matrix[x][y] >= 700) {
						mat += ("|$|");
					}else if(x == y){
						mat += ("|" + 0.0+ "|");	
					}
					else {
						mat += ("|" + matrix[x][y] + "|");
					}
					if (y!=matrix[x].length-1) mat +=("\t\t\t");
				}
				mat += ("\n");
			}
			lbMatrix.setText(mat);
			wt.recorrer(wt.getemployeesTrust());

		}catch(NumberFormatException e) {
			Alert exception = new Alert(AlertType.ERROR);
			exception.setHeaderText("Error");
			exception.setTitle("ERROR");
			exception.setContentText("Please provide the information required");
			exception.showAndWait();
		} catch (IOException e) {
		} catch (EmployeeAlreadyCreatedException e) {
		} catch (EmployeeNotRegisteredException e) {
		}
	}

}
