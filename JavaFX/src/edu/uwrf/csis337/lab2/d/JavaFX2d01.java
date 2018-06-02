package edu.uwrf.csis337.lab2.d;

/**
 * JavaFX2d01 - This is an example of a basic JavaFX program with 2D shapes
 * 			 and colors
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX2d01 extends Application {
	
	private final String TITLE = "JavaFX2d01: JavaFX Example with shapes and colors";
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;
	private final int WIDTH  = 175;
	private final int HEIGHT = 100;
	private Rectangle rectangle = new Rectangle( WIDTH, HEIGHT);
	private final int ERX    = 25;
	private final int ERY    = 75;
	private Ellipse   oval = new Ellipse( ERX, ERY );
	private int rectangle_red   = 127;
	private int rectangle_green = 127;
	private int rectangle_blue  = 127;
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

	/*
	 * Creates an VBox with two buttons for the left region
	 */
    private VBox addLeftBox() {
	   VBox box = new VBox();
	   box.setSpacing(10);   // Gap between nodes
	   ObservableList<Node> list = box.getChildren();
	   
	   Text t = new Text("Change Colors: ");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

	   Text tred = new Text("Rectangle red");
	   tred.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Slider red = new Slider(0,1,0.5);
	   red.setPrefSize(20, 200);
	   red.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rectangle_red = (int) (255 * red.getValue());
				rectangle.setFill( Color.rgb(rectangle_red, rectangle_green, rectangle_blue ) );
			}
		});
		

	   Text tgreen = new Text("Rectangle green");
	   tgreen.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Slider green = new Slider(0,1,0.5);
	   green.setPrefSize(20, 200);
	   green.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rectangle_green = (int) (255 * green.getValue());
				rectangle.setFill( Color.rgb(rectangle_red, rectangle_green, rectangle_blue ) );
			}
		});

	   Text tblue = new Text("Rectangle blue");
	   tblue.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Slider blue = new Slider(0,1,0.5);
	   blue.setPrefSize(20, 200);
	   blue.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rectangle_blue = (int) (255 * blue.getValue());
				rectangle.setFill( Color.rgb(rectangle_red, rectangle_green, rectangle_blue ) );
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
	 * -- all the nodes in a stackpane have the same "origin"
	 *  - the nodes will be stacked on top of each other
	 */
    private StackPane addCenterPane() {
	   StackPane box = new StackPane();
	   box.setAlignment(Pos.CENTER);
	   box.setMinSize(0, 0);
	   
	   wlist = box.getChildren();
	   
	   wlist.add( rectangle );
	   wlist.add( oval );
	   
	   rectangle.setFill( Color.rgb(rectangle_red, rectangle_green, rectangle_blue ) );
	   
	   return box;
	}

	public static void main(String[] args) {
		launch(args);
	}
}