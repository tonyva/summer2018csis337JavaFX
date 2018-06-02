package edu.uwrf.csis337.lab3.a;

/**
 * JavaFX3a01 - This is an example of a basic JavaFX program with 2D shapes
 * 			 with textures using images and transformations
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

public class JavaFX3a01 extends Application {
	
	private final String TITLE = "JavaFX3a01: JavaFX Example with textures using images and transformations";
	private final int SCENE_WIDTH  = 600;
	private final int SCENE_HEIGHT = 400;
	private final int ERX    = 100;
	private final int ERY    = 75;

	/*
	 * Shapes
	 */
	Polygon triangle;
	Ellipse oval;
	Polygon hexagon;
	
	/*
	 * Image for texture
	 */
	private static final String DIR = "file:src/edu/uwrf/csis337/lab3/a";
	private static final String image10url = DIR + "/image10.jpg";
	private static final String image11url = DIR + "/image11.jpg";
	private static final String greeneryurl = DIR + "/greenery.jpg";

	/*
	 * Booleans for choosing transformations
	 */
	private boolean translateShapes = false;
	private boolean rotateShapes    = false;
	private boolean scaleShapes     = false;
	private boolean shearShapes     = false;
	

	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Create a Border Pane and its 5 areas
			 */
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);

			root.setLeft( addLeftBox() );
			root.setCenter( addCenterPane() );
			

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
    //@SuppressWarnings("unchecked")
	private VBox addLeftBox() {
	   VBox box = new VBox();
	   box.setSpacing(10);   // Gap between nodes
	   ObservableList<Node> list = box.getChildren();
	   
	   Text t = new Text("Select transforms:");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

	   CheckBox translate = new CheckBox("Translate");
	   translate.setOnAction( new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event) {
			translateShapes = translate.isSelected();

			final int largeX  = 20;
			final int largeY  = 15;
			final int mediumX = 10;
			final int mediumY = 11;
			final int smallX  =  5;
			final int smallY  =  6;
			if (translateShapes){
				triangle.setTranslateX(  largeX );
				triangle.setTranslateY(  largeY );
				oval.    setTranslateX( -smallX );
				oval.    setTranslateY( -smallY );
				hexagon. setTranslateX(  mediumX );
				hexagon. setTranslateY( -mediumY );
			} else {
				triangle.setTranslateX( 0 );
				triangle.setTranslateY( 0 );
				oval.    setTranslateX( 0 );
				oval.    setTranslateY( 0 );
				hexagon. setTranslateX( 0 );
				hexagon. setTranslateY( 0 );
			}
		}
	   });

	   CheckBox rotate = new CheckBox("Rotate");
	   rotate.setOnAction( new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event) {
			rotateShapes = rotate.isSelected();
			final int rotateAngle = 40;
			if (rotateShapes){
				triangle.setRotate(  rotateAngle );
				oval.    setRotate( -rotateAngle );
				hexagon. setRotate( -rotateAngle );
			} else {
				triangle.setRotate( 0 );
				oval.    setRotate( 0 );
				hexagon. setRotate( 0 );
			} 
		}
	   });

	   CheckBox scale = new CheckBox("Scale");
	   scale.setOnAction( new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event) {
			scaleShapes = scale.isSelected();
			final double largerX  = 1.3;
			final double largerY  = 1.2;
			final double smallerX = 0.8;
			final double smallerY = 0.9;

			if (scaleShapes){
				triangle.setScaleX( largerX  );
				triangle.setScaleY( smallerY );
				oval.    setScaleX( largerX );
				oval.    setScaleY( largerY );
				hexagon. setScaleX( smallerX );
				hexagon. setScaleY( smallerY );
			} else {
				triangle.setScaleX( 1.0 );
				triangle.setScaleY( 1.0 );
				oval.    setScaleX( 1.0 );
				oval.    setScaleY( 1.0 );
				hexagon. setScaleX( 1.0 );
				hexagon. setScaleY( 1.0 );
			}
		}
	   });

	   CheckBox shear = new CheckBox("Shear");
	   shear.setOnAction( new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event) {
			shearShapes = shear.isSelected();
			final double largeX  = 0.5;
			final double largeY  = 0.6;
			final double smallX  = 0.8;
			final double smallY  = 0.9;

			if (shearShapes){
				triangle.getTransforms().add(new Shear( -largeX, 1));
				oval.    getTransforms().add(new Shear(  largeX, -smallY ));
				hexagon. getTransforms().add(new Shear(  smallX, -largeY ));
			} else {
				triangle.getTransforms().add(new Shear(  largeX,  1));
				oval.    getTransforms().add(new Shear( -largeX,  smallY ));
				hexagon. getTransforms().add(new Shear( -smallX,  largeY ));
			}
		}
	   });

	   list.add( t );
	   list.add( translate );
	   list.add( rotate );
	   list.add( scale );
	   list.add( shear );
	   
	   return box;
	}

    /*
	 * Create a FlowPane for the center region
	 */
    private FlowPane addCenterPane() {
    	FlowPane box = new FlowPane();
    	box.setHgap(20);   // Gap between nodes
    	box.setAlignment(Pos.TOP_LEFT);
    	box.setMinSize(0, 0);
	   
		ObservableList<Node> wlist = box.getChildren();
		
		// Set up a triangle
		triangle = new Polygon();
		ObservableList<Double> tlist = triangle.getPoints();
		tlist.addAll(new Double[]{
			    50.0, 25.0,
			    200.0, 175.0,
			     10.0, 195.0 });
		
		// Create an ellipse
		oval = new Ellipse( 125, 125, ERX, ERY );

		// Create a hexagon
		final double hexstartx = 125.0;
		final double hexstarty =  25.0;
		hexagon = new Polygon();
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
	   
		return box;
    }
	
   	public static void main(String[] args) {
		launch(args);
	}
}
