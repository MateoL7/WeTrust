package userInterface;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CommunicationGUI {
	@FXML
    private Label info;
    
    
    private Scene scene;
    
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	@FXML
	public void initialize() {
		info.setVisible(false);
	}
	
    public void setTextCom(String p) {
    	info.setVisible(true);
    	info.setText(p);
    }
    
    public void close() {
    	((Stage) scene.getWindow()).close();
    }
    

}
