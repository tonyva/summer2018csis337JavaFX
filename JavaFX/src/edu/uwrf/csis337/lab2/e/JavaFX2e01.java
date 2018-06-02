package edu.uwrf.csis337.lab2.e;


/**
 * JavaFX2e01 - This is an example of a basic JavaFX program with 2D shapes
 * 			 and Gradient paints
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Paint.html
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX2e01 extends Application {
	
	private final String TITLE = "JavaFX2e01: JavaFX Example with Gradient paints";
	private final int SCENE_WIDTH  = 700;
	private final int SCENE_HEIGHT = 300;
	private final int RX = 75;
	private final int RY = 10;
	private final int WIDTH  = 175;
	private final int HEIGHT = 100;
	private Rectangle rectangle = new Rectangle( RX, RY, WIDTH, HEIGHT);
	Stop[] rectangleStops = new Stop[]{
			new Stop(0, Color.BLUEVIOLET), 
            new Stop(1.0, Color.RED)};
	private LinearGradient rectangleGradient= new
			LinearGradient(0,0, 1,1, true, CycleMethod.REPEAT, rectangleStops);
	
	private final int ECX    = 175;
	private final int ECY    = 150;
	private final int ERX    = 25;
	private final int ERY    = 75;
	Stop[] ellipseStops = new Stop[]{
			new Stop(0, Color.BLUEVIOLET), 
            new Stop(1.0, Color.RED)};
	private RadialGradient ellipseGradient= new
			RadialGradient(10, 0, 0.5, 0.5, 1.0, true, CycleMethod.REPEAT, ellipseStops);
	private Ellipse   oval = new Ellipse( ECX, ECY, ERX, ERY );
	
	private SVGPath stringPath = new SVGPath();
	Stop[] stringStops = new Stop[]{
			new Stop(0, Color.BLUEVIOLET), 
			new Stop(1.0, Color.RED)};
	private LinearGradient stringGradient= new
			LinearGradient(0,0, 1,1, true, CycleMethod.REPEAT, stringStops);
	
	private ObservableList<Node> wlist;

	
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

	private void setGradients(int r, int g, int b){
		rectangleStops[0] = new Stop( 0, Color.rgb(r, g, b ) );
		ellipseStops[0]   = new Stop( 0, Color.rgb(r, g, b ) );
		stringStops[0]    = new Stop( 0, Color.rgb(r, g, b ) );
		rectangleGradient = new LinearGradient(0,0, 1,1, true, CycleMethod.REPEAT, rectangleStops);
		ellipseGradient   = new	RadialGradient(10, 0, 0.5, 0.5, 1.0, true, CycleMethod.REPEAT, ellipseStops);
		stringGradient    = new	LinearGradient(0,0, 1,1, true, CycleMethod.REPEAT, stringStops);
		rectangle.setFill( rectangleGradient );
		oval.setFill( ellipseGradient );
		stringPath.setStroke( stringGradient );
	}
	/*
	 * Creates an VBox with two buttons for the left region
	 */
    private VBox addLeftBox() {
	   VBox box = new VBox();
	   box.setSpacing(10);   // Gap between nodes
	   ObservableList<Node> list = box.getChildren();
	   
	   Text t = new Text("Change Colors\nFirst Stop:");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

	   Text tred = new Text("Red");
	   tred.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Slider red = new Slider(0,1,0.5);
	   red.setPrefSize(20, 200);
	   red.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				int r  = (int) (255 * red.getValue());
				int g  = (int) (255 * rectangleStops[0].getColor().getGreen() );
				int b  = (int) (255 * rectangleStops[0].getColor().getBlue() );
				setGradients( r, g, b );
			}
		});
		

	   Text tgreen = new Text("Green");
	   tgreen.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Slider green = new Slider(0,1,0.5);
	   green.setPrefSize(20, 200);
	   green.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				int r = (int) (255 * rectangleStops[0].getColor().getRed() );
				int g = (int) (255 * green.getValue());
				int b = (int) (255 * rectangleStops[0].getColor().getBlue() );
				setGradients( r, g, b );
			}
		});

	   Text tblue = new Text("Blue");
	   tblue.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Slider blue = new Slider(0,1,0.5);
	   blue.setPrefSize(20, 200);
	   blue.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				int r   = (int) (255 * rectangleStops[0].getColor().getRed() );
				int g = (int) (255 * rectangleStops[0].getColor().getGreen() );
				int b  = (int) (255 * blue.getValue());
				setGradients( r, g, b );
			}
		});

	   list.add(t);
	   list.add( tred );
	   list.add( red );
	   list.add( tgreen );
	   list.add( green );
	   list.add( tblue );
	   list.add( blue );
	        
	   return box;
	}

	/*
	 * Creates a StackPane for the center region
	 */
    private FlowPane addCenterPane() {
       FlowPane box = new FlowPane();
       box.setHgap(20);   // Gap between nodes
	   box.setAlignment(Pos.TOP_LEFT);
	   box.setMinSize(0, 0);
	   
	   wlist = box.getChildren();
	   
	   wlist.add( rectangle );
	   wlist.add( oval );
	   wlist.add( stringPath );
	   
	   // See http://www.w3.org/TR/SVG/paths.html
	   //   for the meanings of the values in the string below
	   stringPath.setContent("M10,1 C100,20 0,25 70,80");
	   stringPath.setFill( null );
	   stringPath.setStroke( stringGradient );
	   stringPath.setStrokeWidth(10);
	   stringPath.setStrokeLineCap( StrokeLineCap.ROUND );
	   
	   oval.setFill( ellipseGradient );
	   rectangle.setFill( rectangleGradient );
	   
	   return box;
	}

	public static void main(String[] args) {
		launch(args);
	}
}