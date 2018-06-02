package edu.uwrf.csis337.lab4.b;

/**
 * JavaFX4b01 - This is an example of a basic JavaFX program with 3D shapes
 *   
 * For more details see:
 * http://docs.oracle.com/javase/8/javafx/graphics-tutorial/javafx-3d-graphics.htm
 */


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class JavaFX4b01 extends Application {
	
	private final String TITLE = "JavaFX4b01: JavaFX 3D Shape Example";
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;

	/*
	 * File for texture
	 */
	private final String directory = "file:src/edu/uwrf/csis337/lab4/b/"; // where the image is
	private final String file      = "UWRFLogo.jpg"; // name of image file
	private final String filename  = directory + file;

	private boolean enableDepthBuffer = false;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT, enableDepthBuffer );
			ObservableList<Node> list = root.getChildren();
			
			/*
			 * Nodes in this scene
			 */
			Sphere sphere = new Sphere( 100 );
			/*
			 * Use the Phong shading material property for the sphere
			 * 
			 * The name is from the computer scientist Bui Phong who
			 * came up with the shading technique in 1973 as part of his
			 * PhD thesis at Utah. He passed away in 1975 from leukemia.
			 * The key is math - interpolate the normal vectors on a surface.
			 */
			PhongMaterial pm = new PhongMaterial();
			pm.setDiffuseColor(Color.RED);
			sphere.setMaterial( pm );
			sphere.setTranslateX( 50 );
			sphere.setTranslateY( 50 );
			sphere.setTranslateZ( 0 );

			final int RectangleX =  100;
			final int RectangleY =  20;
			Rectangle rectangle1 = new Rectangle(RectangleX,RectangleY,200,100);
			rectangle1.setFill( new ImagePattern( new Image(filename) ) );

			Line line = new Line(10, 100, 300, 100);

			list.add( sphere );
			list.add( rectangle1 );
			list.add( line );

			if (enableDepthBuffer)
				scene.setCamera( new PerspectiveCamera() );
			primaryStage.setScene(scene);
			primaryStage.setTitle( TITLE );
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}


	public static void main(String[] args) {
		launch(args);
	}
}
