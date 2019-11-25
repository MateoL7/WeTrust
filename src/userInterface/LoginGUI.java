package userInterface;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginGUI {

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	@FXML
	private Button loginBt;

	@FXML
	private Button cancelBt;

	private Scene scene;
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@FXML
	public void cancelLogin(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("You are about to exit the program");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			((Stage) scene.getWindow()).close();
		} else {
		}	
	}

	@FXML
	public void login(ActionEvent event) throws IOException {
		try {
			String usern = username.getText();
			String pass = password.getText();
			if(usern.equalsIgnoreCase("manager") && pass.equalsIgnoreCase("wetrust")) {
				((Stage) scene.getWindow()).close();
				Parent root = FXMLLoader.load(getClass().getResource("WeTrustGUI.fxml")); 
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("WeTrust");
				stage.show();
			}
			else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.initStyle(StageStyle.UTILITY);
				alert.setTitle("Information");
				alert.setHeaderText("WARNING!");
				alert.setContentText("Wrong username or password");

				alert.showAndWait();
			}
		}catch(NumberFormatException n) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Information");
			alert.setHeaderText("WARNING!");
			alert.setContentText("Please provide the correct information");

			alert.showAndWait();
		}
	}



}


