package edu.uwrf.csis337.lab4.b;

/**
 * JavaFX4b02 - This is an example of a basic JavaFX program with 3D shapes
 *   
 * For more details see:
 * http://docs.oracle.com/javase/8/javafx/graphics-tutorial/javafx-3d-graphics.htm
 */


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX4b02 extends Application {
	
	private final String TITLE = "JavaFX4b02: JavaFX 3D Shape Example";
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;

	/*
	 * File for texture
	 */
	private final String directory = "file:src/edu/uwrf/csis337/lab4/b/"; // where the image is
	private final String file      = "UWRFLogo.jpg"; // name of image file
	private final String filename  = directory + file;

	private boolean enableDepthBuffer = false;
	private Rotate rotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS );

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT, enableDepthBuffer );
			ObservableList<Node> list = root.getChildren();
			list.add( addPane() );

			if (enableDepthBuffer)
				scene.setCamera( new PerspectiveCamera() );
			primaryStage.setScene(scene);
			primaryStage.setTitle( TITLE );
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	private Pane addPane(){
		Pane box = new Pane();
		ObservableList<Node> list = box.getChildren();
		
		/*
		 * Nodes in this scene
		 */
		Cylinder cylinder = new Cylinder( 100, 200  );
		/*
		 * Use the Phong shading material property for the cylinder
		 */
		PhongMaterial pm = new PhongMaterial();
		pm.setDiffuseColor(Color.RED);
		cylinder.setMaterial( pm );
		cylinder.setTranslateX( 50 );
		cylinder.setTranslateY( 50 );
		cylinder.setTranslateZ( 0 );

		final int RectangleX =  100;
		final int RectangleY =  20;
		Rectangle rectangle = new Rectangle(RectangleX,RectangleY,200,100);
		rectangle.setFill( new ImagePattern( new Image(filename) ) );

		// Use 3-d boxes - long narrow ones - for each of the three axes.
		Box xaxis = new Box(300,   2,   2);
		Box yaxis = new Box(  2, 300,   2);
		Box zaxis = new Box(  2,   2, 300);
		PhongMaterial rpm = new PhongMaterial();		rpm.setDiffuseColor(Color.RED);
		PhongMaterial gpm = new PhongMaterial();		gpm.setDiffuseColor(Color.GREEN);
		PhongMaterial bpm = new PhongMaterial();		bpm.setDiffuseColor(Color.BLUE);
		xaxis.setMaterial( rpm );
		yaxis.setMaterial( gpm );
		zaxis.setMaterial( bpm );


		// group the elements and apply the rotation to the group 
		//    as a whole
		Group things = new Group( rectangle, cylinder, xaxis, yaxis, zaxis );
		things.getTransforms().add(rotate);
	
	    // rotate animation - timeline affects the rotation angle
	    Timeline rAnimation = new Timeline();
	    rAnimation.getKeyFrames().addAll( new KeyFrame( Duration.seconds( 20), new KeyValue( rotate.angleProperty(), 360 ) ) );
	    rAnimation.setCycleCount(Animation.INDEFINITE);
	    rAnimation.play();

		
		list.add( things );
		return box;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
