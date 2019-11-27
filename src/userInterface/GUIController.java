package userInterface;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Employee;
import model.WeTrust;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;
import visuals.CellType;
import visuals.Graph;
import visuals.Layout;
import visuals.Model;
import visuals.RandomLayout;

public class GUIController {

    @FXML
    private ImageView FirstScreen;

    @FXML
    private Button createBtt;

    @FXML
    private TextField numOfEmp;

    @FXML
    private Button showBtt;

    @FXML
    private Label optionsLabel;

    @FXML
    private Label worstLab;
    

    @FXML
    private Button bestEmployeeBtt;

    @FXML
    private TextField employeeIdDes;

    @FXML
    private Label bestLabl;

    @FXML
    private Button comBtt;

    @FXML
    private TextField employeeIdDes1;

    @FXML
    private Button worstEmployeeBtt;

	private boolean MorL;

	private Graph graph;
	private WeTrust wt;
	
	private Scene scene;
	
	private int size;
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@FXML
	public void initialize() {
		graph = new Graph();
		showBtt.setVisible(false);
		employeeIdDes.setVisible(false);
		bestEmployeeBtt.setVisible(false);
		optionsLabel.setVisible(false);
		bestLabl.setVisible(false);
		worstEmployeeBtt.setVisible(false);
		employeeIdDes1.setVisible(false);
		comBtt.setVisible(false);
		worstLab.setVisible(false);
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
				int num = Integer.parseInt(numOfEmp.getText());
				size = num;
				wt = new WeTrust(MorL, num);
				wt.loadEmployees();
				wt.generateEmployees(num);
				wt.generateEmployeesTrust();
				wt.loademployeesTrust();

				addGraphComponents(wt.getemployeesTrust());
				createBtt.setVisible(false);
				numOfEmp.setVisible(false);
				try {
					wt.loadEmployees();
				} catch (IOException e) {
				} catch (EmployeeAlreadyCreatedException e) {
				}

			}else if(resulType.get() == buttonTypeTwo) {
				MorL = false;
				int num = Integer.parseInt(numOfEmp.getText());
				size = num;
				wt = new WeTrust(MorL, num);
				wt.loadEmployees();
				wt.generateEmployees(num);
				wt.generateEmployeesTrust();
				wt.loademployeesTrust();

				addGraphComponents(wt.getemployeesTrust());
				createBtt.setVisible(false);
				numOfEmp.setVisible(false);
			}

			showBtt.setVisible(true);
			FirstScreen.setVisible(false);
			employeeIdDes.setVisible(true);
			bestEmployeeBtt.setVisible(true);
			optionsLabel.setVisible(true);
			worstEmployeeBtt.setVisible(true);
			employeeIdDes1.setVisible(true);
			comBtt.setVisible(true);


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

	public void FloydWarshall() {
		double[][] done = wt.FloydWarshall();
		graph.getPane().clear();
		clean();
		addGraphComponents(done);

		showGraph();
	}

	private void addGraphComponents(double[][] matrix) {

		Model model = graph.getModel();

		graph.beginUpdate();

		for(int i = 0; i < matrix.length; i++) {
			model.addCell(String.valueOf(i), CellType.LABEL, i);
		}

		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] < Integer.MAX_VALUE) {
					model.addEdge(String.valueOf(i), String.valueOf(j), matrix[i][j]);
				}
			}
		}
		graph.endUpdate();

	}

	public void getBestOption() {
		try {
			String id = employeeIdDes.getText();
			Employee des = wt.searchEmployee(Integer.parseInt(id));
			Employee best = wt.getBestOption(des);
			if(best == null) bestLabl.setText("No best employee possible");
			else bestLabl.setText(best.toString());
			bestLabl.setVisible(true);

		}catch(NumberFormatException n) {
			Alert exception = new Alert(AlertType.ERROR);
			exception.setHeaderText("Error");
			exception.setTitle("ERROR");
			exception.setContentText("Please provide the information required");
			exception.showAndWait();
		}catch(ArrayIndexOutOfBoundsException e) {
			Alert exception = new Alert(AlertType.ERROR);
			exception.setHeaderText("Error");
			exception.setTitle("ERROR");
			exception.setContentText("There is no Employee with that id");
			exception.showAndWait();
		}
	}

	public void getBestEmployeeCom() throws IOException {
		try {
			String best = wt.getBestCommunication();
			if(size > 20) {
				Alert exception = new Alert(AlertType.INFORMATION);
				exception.setHeaderText("Number of employees exceeds the maximum allowed");
				exception.setTitle("Information");
				exception.setContentText("For this reason, only the first 20 relationships will be shown on the screen, the rest can be found in the folder called 'data' in the File named 'Communication'");
				exception.showAndWait();
				PrintWriter pr = new PrintWriter(new File("data/Communication.txt"));
				pr.write(best);
				pr.close();
			}
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CommunicationPath.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			CommunicationGUI cg = fxmlLoader.getController();
			cg.setScene(scene);
			cg.setTextCom(best);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Trust - Communication");
			stage.show();
			
		}catch(NumberFormatException n) {
			Alert exception = new Alert(AlertType.ERROR);
			exception.setHeaderText("Error");
			exception.setTitle("ERROR");
			exception.setContentText("Please provide the information required");
			exception.showAndWait();
		}catch(ArrayIndexOutOfBoundsException e) {
			Alert exception = new Alert(AlertType.ERROR);
			exception.setHeaderText("Error");
			exception.setTitle("ERROR");
			exception.setContentText("There is no Employee with that id");
			exception.showAndWait();
		}
	}
	
	public void getWorstOption() {
		try {
			String id = employeeIdDes1.getText();
			Employee des = wt.searchEmployee(Integer.parseInt(id));
			Employee worst = wt.getWorstOption(des);
			if(worst == null) worstLab.setText("No worst employee possible");
			else worstLab.setText(worst.toString());
			worstLab.setVisible(true);
		}catch(NumberFormatException n) {
			Alert exception = new Alert(AlertType.ERROR);
			exception.setHeaderText("Error");
			exception.setTitle("ERROR");
			exception.setContentText("Please provide the information required");
			exception.showAndWait();
		}
	}

	public void clean() {
		Model model = graph.getModel();

		graph.beginUpdate();
		model.clear();
		graph.endUpdate();
	}
	
	@FXML
	public void exit(ActionEvent event) {
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

	public void showGraph() {
		if(size > 20) {
			Alert exception = new Alert(AlertType.INFORMATION);
			exception.setHeaderText("Number of employees exceeds the recommended");
			exception.setTitle("Information");
			exception.setContentText("For this reason, we offer our apologies if the graph is not very understandable and takes a while to load.");
			exception.showAndWait();
		}
		BorderPane root = new BorderPane();


		root.setCenter(graph.getScrollPane());

		Scene scene = new Scene(root, 1024, 768);

		Stage primaryStage = new Stage();
		primaryStage.setTitle("Representation");
		scene.setFill(Color.BLACK);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(scene);
		primaryStage.show();

		Layout layout = new RandomLayout(graph);
		layout.execute();
	}
}
