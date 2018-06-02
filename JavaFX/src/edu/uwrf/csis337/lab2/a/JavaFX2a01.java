package edu.uwrf.csis337.lab2.a;

/**
 * JavaFX2a01 - This is an example of a basic JavaFX program with 2D shapes
 * 			 and a transforms.
 *   
 * For more details see:
 * http://docs.oracle.com/javase/8/javafx/api/javafx/scene/transform/Affine.html
 * http://docs.oracle.com/javase/8/javafx/visual-effects-tutorial/transformations-intro.htm
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

public class JavaFX2a01 extends Application {

	private final String TITLE = "Java2a01: JavaFX Example with a 2D shapes and transform";

	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Create a Border Pane and its 5 areas
			 */
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			ObservableList<Node> list = root.getChildren();

			Rectangle rectangle1 = new Rectangle(100,50,60,60);
			rectangle1.setFill(Color.GREEN);
			list.add( rectangle1 );
			
			rectangle1.setRotate( 45.0 );
			
			double lsx = 20.0, lsy = 30.0, lex = 220.0, ley = 30.0;
			Line line = new Line( lsx, lsy, lex, ley );
			line.setRotate( 45.0 );
			
			list.add( line );

			Rectangle rectangle2 = new Rectangle(100,50,60,60);
			//rectangle2.setFill(Color.GREEN);
			//list.add( rectangle2 );
			
			//rectangle2.setRotate( 45.0 );
			// rectangle2.setTranslateY( -30 );
			// rectangle2.setScaleX( 1.1 );
			// rectangle2.setScaleY( 0.7 );
			// rectangle2.getTransforms().add( new Shear( 0.1, 0.1 ) );
			
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