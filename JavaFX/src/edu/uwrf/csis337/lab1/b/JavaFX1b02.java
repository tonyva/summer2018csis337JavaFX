package edu.uwrf.csis337.lab1.b;

/**
 * JavaFX1b02 - This is an example of a basic Java FX program
 *   that uses the built-in BorderPane layout.
 *   
 * For more details see:
 * https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ComboBox.html
 * https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/ui_controls.htm
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX1b02 extends Application {

	private final String TITLE = "JavaFX1b02: JavaFX Example with a BorderPane layout";

	
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Create a Border Pane and its 5 areas
			 */
			BorderPane root = new BorderPane();
			HBox topbox   = addTopBox();
			VBox leftbox  = addLeftBox();
			VBox rightbox = addRightBox();
			root.setTop( topbox );
			root.setLeft( leftbox );
			root.setRight( rightbox );
			
			Scene scene = new Scene(root,400,400);
			ObservableList<Node> list = root.getChildren();
			
			primaryStage.setTitle( TITLE );
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	/*
	 * Creates an HBox with two buttons for the top region
	 */
    private HBox addTopBox() {
	   HBox hbox = new HBox();
	   hbox.setSpacing(10);   // Gap between nodes
	   ObservableList<Node> list = hbox.getChildren();
	   
	   Text t = new Text("Top");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

	   Button button1 = new Button("Hot");
	   button1.setPrefSize(100, 20);

	   Button button2 = new Button("Cold");
	   button2.setPrefSize(100, 20);
	   
	   list.add(t);
	   list.add( button1 );
	   list.add( button2 );
	        
	   return hbox;
	}
	    
	/*
	 * Creates a VBox with a list of links for the left region
	 */
	private VBox addLeftBox() {
	   VBox box = new VBox();
	   ObservableList<Node> list = box.getChildren();
	   
	   Text t = new Text("Left");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	   list.add( t );
	   
	   return box;
	}

	/*
	 * Creates a VBox with a list of links for the right region
	 */
	private VBox addRightBox() {
		VBox box = new VBox();
		ObservableList<Node> list = box.getChildren();
		
		Text t = new Text("Right");
		t.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		
		/*
		 * Set up a combobox for this panel
		 */
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().add( "Alpha" );
		combo.getItems().add( "Beta" );
		combo.getItems().add( "Gamma" );
		combo.getItems().add( "Delta" );
		combo.getItems().add( "Phi" );
		
		list.add( t );
		list.add( combo );
		return box;
	}

	public static void main(String[] args) {
		launch(args);
	}
}