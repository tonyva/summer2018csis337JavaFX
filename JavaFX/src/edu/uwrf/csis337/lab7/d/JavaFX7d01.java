package edu.uwrf.csis337.lab7.d;

/**
 * JavaFX7d01 - This is an example of a basic JavaFX animation using KeyFrames and events
 *   
 * For more details see:
 * http://docs.oracle.com/javase/8/javafx/graphics-tutorial/javafx-3d-graphics.htm
 */


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX7d01 extends Application {
	
	private final String TITLE = "JavaFX7d01: JavaFX 3D Key Frame Example";
	private final int SCENE_WIDTH  = 600;
	private final int SCENE_HEIGHT = 400;

	/*
	 * File for texture
	 */
	private final String directory = "file:src/lab04b/"; // where the image is
	private final String file      = "UWRFLogo.jpg"; // name of image file
	private final String filename  = directory + file;
	private Rotate rotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS );

	// Color of sphere
	private int red = 128;
	private int green = 12;
	private int blue = 12;

    // Set up a Text object that will be updated using a timer
    private Text message = new Text();
    private int frameNumber = 0;
    private AnimationTimer timer;

	private boolean enableDepthBuffer = true;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT, enableDepthBuffer );
			scene.setFill( Color.LIGHTGRAY );
			ObservableList<Node> list = root.getChildren();
			list.add( addPane() );

			timer = new AnimationTimer() {
				private final int gap = 4;
	            @Override
	            public void handle(long l) {
	            	if( l % gap == 0 )
	            		message.setText( "" + frameNumber++ );
	            }
	        };
	        timer.start();
	        
	        
	        
			if (enableDepthBuffer){
				PerspectiveCamera cam = new PerspectiveCamera();
				// Position the camera up and back a bit
				cam.setTranslateY( -10 );
				cam.setTranslateZ( -50 );
				scene.setCamera( cam );
			}
			primaryStage.setScene(scene);
			primaryStage.setTitle( TITLE );
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	
	
	private Pane addPane(){
		PhongMaterial rpm = new PhongMaterial();
		rpm.setDiffuseColor(Color.RED);
		PhongMaterial gpm = new PhongMaterial();
		gpm.setDiffuseColor(Color.GREEN);
		PhongMaterial bpm = new PhongMaterial();
		bpm.setDiffuseColor(Color.BLUE);

		
		Pane box = new Pane();
		ObservableList<Node> list = box.getChildren();
		
		/*
		 * Nodes in this scene
		 */
		// Text
		message.setFont(Font.font("Arial", FontWeight.BOLD, 40 ));
		message.setText("Count");
		message.setTranslateX( SCENE_WIDTH/4  );
		message.setStroke( Color.BLUE );
		
		// Rectangle
		Image rim = new Image(filename);
		Rectangle rectangle = new Rectangle(0, 0, rim.getWidth(), rim.getHeight() );
		rectangle.setTranslateX( SCENE_WIDTH/4 );
		rectangle.setFill( new ImagePattern( rim, 0, 0, rim.getWidth(), rim.getHeight(), false ) );

		Sphere sphere = new Sphere( 100 );
		sphere.setTranslateX( 200 );
		sphere.setTranslateY( 200 );
		sphere.setTranslateZ(  10 );
		PhongMaterial pm = new PhongMaterial();
		pm.setDiffuseColor( Color.rgb(red, green, blue) );
		sphere.setMaterial( pm );
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			private final int increment = 30;
			private void increment(){
            	red = (red+increment) % 256;
            	if (red<increment)
            		blue = (blue+increment) % 256;
            	if (blue<increment)
            		green = (green+increment) % 256;
			}
            public void handle(ActionEvent t) {
            	//System.out.println("event!");
            	increment();
            	pm.setDiffuseColor( Color.rgb(red, green, blue) );
                frameNumber = 0; // start over
            }
        };
		KeyValue scaleX = new KeyValue( sphere.scaleXProperty(), 1.5);
		KeyValue scaleY = new KeyValue( sphere.scaleYProperty(), 1.5);
		KeyValue scaleZ = new KeyValue( sphere.scaleZProperty(), 1.5);
		KeyFrame kf1 = new KeyFrame(Duration.seconds( 3 ), event, scaleX, scaleY, scaleZ );

		Timeline timeline = new Timeline( kf1 );
		timeline.setAutoReverse( true );
		timeline.setCycleCount( 100 );
		timeline.play();

		// Use 3-d boxes - long narrow ones - for each of the three axes.
		Box xaxis = new Box(300,   2,   2);
		Box yaxis = new Box(  2, 300,   2);
		Box zaxis = new Box(  2,   2, 300);
		xaxis.setMaterial( rpm );
		yaxis.setMaterial( gpm );
		zaxis.setMaterial( bpm );

		// group the elements
		Group things = new Group( message, rectangle, sphere, xaxis, yaxis, zaxis );
		things.setTranslateX(SCENE_WIDTH/2);
		things.setTranslateY(SCENE_HEIGHT/2);
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
