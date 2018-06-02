package edu.uwrf.csis337.lab2.f;

/**
 * JavaFX2f01 - This is an example of a basic JavaFX program with 2D shapes
 * 			 and textures using images
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/ImagePattern.html
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class JavaFX2f01 extends Application {
	
	private final String TITLE = "Java02h: JavaFX Example with textures using image files";
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;
	private final int ERX    = 100;
	private final int ERY    = 75;
	
	/*
	 * Image for texture
	 */
	private static final String DIR = "file:src/edu/uwrf/csis337/lab2/f/";
	private static final String image10url = DIR + "image10.jpg";
	private static final String image11url = DIR + "image11.jpg";
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
			
			// Set up a triangle
			Polygon triangle = new Polygon();
			ObservableList<Double> tlist = triangle.getPoints();
			tlist.addAll(new Double[]{
				    150.0, 25.0,
				    300.0, 275.0,
				     10.0, 295.0 });
			
			// Create an ellipse
			Ellipse   oval = new Ellipse( 125, 225, ERX, ERY );

			// Create a hexagon
			final double hexstartx = 225.0;
			final double hexstarty =  25.0;
			Polygon hexagon = new Polygon();
			ObservableList<Double> hlist = hexagon.getPoints();
			hlist.addAll(new Double[]{
					hexstartx,       hexstarty,
					hexstartx + 100, hexstarty,
					hexstartx + 125, hexstarty + 75,
					hexstartx + 100, hexstarty + 150,
					hexstartx,       hexstarty + 150,
					hexstartx -  25, hexstarty + 75 });
			/*
			 * Texture images
			 */
			ImagePattern image10 = new ImagePattern( new Image(image10url),  0, 0, 1, 1, true);
			ImagePattern image11 = new ImagePattern( new Image(image11url),  0, 0, 1, 1, true);
			ImagePattern green   = new ImagePattern( new Image(greeneryurl), 0, 0, 1, 1, true);
			triangle.setFill( green );
			oval.setFill( image10 );
			hexagon.setFill( image11 );

			
			wlist.add( triangle );
			wlist.add( oval );
			wlist.add( hexagon );
			
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