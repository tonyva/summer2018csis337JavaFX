package edu.uwrf.csis337.lab2.c;

/**
 * JavaFX2c01 - This is an example of a basic JavaFX program with 2D shapes
 * 			 - Quadratic and Cubic curves:
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/package-summary.html
 * 
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX2c01 extends Application {
	
	private final String TITLE = "JavaFX2c01: JavaFX Quadratic and Cubic curves";
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;
	private QuadCurve	quadc = new QuadCurve( );
	private CubicCurve	cubic = new CubicCurve( );
	private ObservableList<Node> wlist;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Create a Border Pane and its 5 areas
			 */
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
			wlist = root.getChildren();

			wlist.add( quadc );
			wlist.add( cubic );

			quadc.setStartX(100.0);
			quadc.setStartY(250.0);
			quadc.setEndX(150.0);
			quadc.setEndY(250.0);
			quadc.setControlX(125.0);
			quadc.setControlY(100.0);

			cubic.setStartX(200.0);
			cubic.setStartY(150.0);
			cubic.setEndX(275.0);
			cubic.setEndY(150.0);
			cubic.setControlX1(225.0);
			cubic.setControlY1(0.0);
			cubic.setControlX2(250.0);
			cubic.setControlY2(250.0);
			
			
			VBox box   = addLeftBox();
			root.setLeft( box );
			
			
			primaryStage.setTitle( TITLE );
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	/*
	 * Creates an VBox with two buttons for the left region
	 */
    private VBox addLeftBox() {
	   VBox box = new VBox();
	   box.setSpacing(10);   // Gap between nodes
	   ObservableList<Node> list = box.getChildren();
	   
	   Text t = new Text("Change Curves: ");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

	   Button button1 = new Button("Change Quad Curve");
	   button1.setPrefSize(200, 20);
	   button1.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				double currentX = quadc.getControlX();
			}
		});
		

	   Button button2 = new Button("Change 1 Cubic Curve");
	   button2.setPrefSize(200, 20);
	   
	   Button button3 = new Button("Change 2 Cubic Curve");
	   button3.setPrefSize(200, 20);

	   list.add(t);
	   list.add( button1 );
	   list.add( button2 );
	   list.add( button3 );
	        
	   return box;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}