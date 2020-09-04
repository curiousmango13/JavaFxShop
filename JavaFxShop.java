package application;
	
import java.awt.Checkbox;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	// GUI window and scene names
	Stage window;
	Scene scene1, scene2;
	// Declare Comboxs
	ComboBox <String> comboBox1;
	ComboBox <String> comboBox2;
	
	// Array List
	ArrayList<String> list = new ArrayList<>();

	// Launchs GUI
	public static void main(String[] args) {
		launch(args);
		
	}
	
	// GUI Window
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		// Variables For Displaying Total Price
		double meatPrice = 0.0;
		double extras = 1.50;
		double total = 0.0;
				
		// Sandwich Ingredient ComboBox
        Label label1 = new Label("Meat Selection:"); 
		comboBox1 = new ComboBox<>();
		comboBox1.getItems().addAll(
				"Chicken - $5.00", 
				"Pork - $6.50", 
				"Brisket - $7.25"
		);
		comboBox1.setPromptText("Please make your meat selection:");
		Button button1 = new Button("Confirm Selection");
		button1.setOnAction(e -> printMeat());
		comboBox1.setOnAction(e -> System.out.println("User selected: " + comboBox1.getValue()));
		
		// Bread Selection ComboBox
        Label label2 = new Label("Bread Selection:"); 
		comboBox2 = new ComboBox<>();
		comboBox2.getItems().addAll(
				"White", 
				"Wheat", 
				"Sourdough"
		);
		comboBox2.setPromptText("Please make your bread selection:");
		Button button2 = new Button("Confirm Selection");
		button2.setOnAction(e -> printBread());
		comboBox2.setOnAction(e -> System.out.println("User selected: " + comboBox2.getValue()));
		
		// Condiment Checkbox
		Label label3 = new Label("Condiments:");
		CheckBox box1 = new CheckBox("Lettuce");
		CheckBox box2 = new CheckBox("Tomato");
		CheckBox box3 = new CheckBox("Bacon - (+$1.50)");
		
		
		//Set Checkbox Defaults
		box1.setSelected(true);
		box2.setSelected(true);
		box3.setSelected(true);
		// Button 3 To Confirm Checkbox Selections
		Button button3 = new Button("Confirm Selections");
		button3.setOnAction(e -> handleOptions(box1, box2, box3));
		
		// Add Order To Cart And Print List To Console
		Button button4 = new Button("Add order to cart");
		button4.setOnAction(e -> System.out.println("Current Sandwich Order\n======================\n" + list));
		
		// Button 4 to Proceed To Scene 2
		Button button5 = new Button("Go to Checkout");
		button5.setOnAction(e -> window.setScene(scene2));  //changes scene
		
		// Shop Layout
		VBox layout1 = new VBox(10);
		layout1.setPadding(new Insets(20, 20, 20, 20));
		// Add Nodes To Window
		layout1.getChildren().addAll(label1, comboBox1, button1,
				label2, comboBox2, button2, label3, box1, box2, box3, button3, button4, button5);
		scene1 = new Scene(layout1, 300, 500);
		
		// Button 5 to return to selection menu
		Button button6 = new Button("Keep Shopping");
		button6.setOnAction(e -> window.setScene(scene1));
		
		// Button To Print Total To Console
		Button button7 = new Button("Total");
		button7.setOnAction(e -> calcTotal());
		
		// Checkout Layout
		VBox layout2 = new VBox(10);
		layout2.getChildren().addAll(button6, button7);
		scene2 = new Scene(layout2, 600, 300);
	
		
		// set default scene
		window.setScene(scene1);
		window.setTitle("Valencia Sandwich Shop");
		window.show();
		
		
		
	}

	// ComboBox Method For Meat
	private void printMeat() {
		System.out.println(comboBox1.getValue() + " confirmed.");
		list.add(comboBox1.getValue());
		
	}
			
	// ComboBox Method For Meat
	private void printBread() {
		System.out.println(comboBox2.getValue() + " confirmed.");
		list.add(comboBox2.getValue());
		
	}
		
	// Method to extract data from bread drop down list
	private void handleOptions(CheckBox box1, CheckBox box2, CheckBox box3 ) {
		String message = "Codiment Selection:\n";
			
		if(box1.isSelected()) {
			message += "Lettuce\n";
			list.add("Lettuce\n");
		}
		if(box2.isSelected()) {
			message += "Tomato\n";
			list.add("Tomato");
		}
		if(box3.isSelected()) {
			message += "Bacon - (+$1.50)\n";
			list.add("Bacon - (+$1.50)\n");
		}
		System.out.println(message);
		}
		
	// Method To Calculate Total
	private double calcTotal() {
		double total = 0.0;
		boolean cCheck = list.contains("Chicken - $5.00");
		boolean pCheck = list.contains("Pork - $6.50");
		boolean bCheck = list.contains("Brisket - $7.25");
		boolean baconCheck = list.contains("Bacon - (+$1.50)\n");
		
		if(cCheck && baconCheck) {
			total = total + 6.50;	
		}else if (cCheck) {
			total = total + 5.00;	
		}
		if(pCheck && baconCheck) {
			total = total + 8.00;	
		}else if (pCheck) {
			total = total + 6.50;
		}
		if(bCheck && baconCheck) {
			total = total + 8.75;
			
		}else if (bCheck) {
			total = total + 7.25;
		}
		
		
		System.out.println("Order Total\n===========\n$" + total);
		return total;
	}
	
	
}
