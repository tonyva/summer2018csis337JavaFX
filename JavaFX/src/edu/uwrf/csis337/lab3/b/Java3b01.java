package edu.uwrf.csis337.lab3.b;

/**
 * Java03b - This is an example of a basic JavaFX program with 2D shapes
 * 			 with multiple transformations
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/ImagePattern.html
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Java3b01 extends Application {
	
	private final String TITLE = "Java3b01: JavaFX Example with multiple transforms";
	
	private final int SCENE_WIDTH  = 600;
	private final int SCENE_HEIGHT = 400;
	private final int ECX    = 100;
	private final int ECY    = 100;
	private final int ERX    = 60;
	private final int ERY    = 75;

	/*
	 * Shapes
	 */
	Ellipse oval1, oval2, oval3;
	
	/*
	 * Image for texture
	 */
	private static final String DIR = "file:src/edu/uwrf/csis337/lab3/a/";
	private static final String image10url  = DIR + "image10.jpg";
	private static final String image11url  = DIR + "image11.jpg";
	private static final String greeneryurl = DIR + "greenery.jpg";


	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Create a Border Pane and its 5 areas
			 */
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);

			   
			ObservableList<Node> wlist = root.getChildren();
			
			// Create an ellipses
			oval1 = new Ellipse( ECX, ECY, ERX, ERY );
			oval2 = new Ellipse( ECX, ECY, ERX, ERY );
			oval3 = new Ellipse( ECX, ECY, ERX, ERY );
			wlist.add( oval1 );
			wlist.add( oval2 );
			wlist.add( oval3 );

			/*
			 * Apply texture maps
			 */
			ImagePattern image1 = new ImagePattern( new Image(image10url),  0, 0, 1, 1, true);
			ImagePattern image2 = new ImagePattern( new Image(image11url),  0, 0, 1, 1, true);
			oval1.setFill( Color.AQUAMARINE );
			oval2.setFill( image1 );
			oval3.setFill( image2 );

			/*
			 * Apply transformations to oval2 and oval3
			 */
			Translate translate = new Translate( 200, 30 );
			Rotate    rotate    = new Rotate( 45 );
			Scale	  scale 	= new Scale( 1.2, 0.7 );
			Shear     shear     = new Shear( -0.1, 0.2 );
			
			Transform tx1 = translate;
			Transform tx2 = rotate;
			
			ObservableList<Transform> oval2Xforms = oval2.getTransforms();
			oval2Xforms.add( tx1 );
			oval2Xforms.add( tx2 );
			
			ObservableList<Transform> oval3Xforms = oval3.getTransforms();
			oval3Xforms.add( tx2 );
			oval3Xforms.add( tx1 );
			
			primaryStage.setTitle( TITLE );
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}


   	public static void main(String[] args) {
		launch(args);
	}
}
