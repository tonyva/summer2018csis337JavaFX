package edu.uwrf.csis337.lab2.b;

/**
 * JavaFX2b01 - This is an example of a basic JavaFX program with 2D shapes
 * 			 with areas and operations to combine areas:
 *             intersect
 *             subtract
 *             union
 *   
 * For more details see:
 * http://docs.oracle.com/javase/8/javafx/api/javafx/scene/transform/Affine.html
 * http://docs.oracle.com/javase/8/javafx/visual-effects-tutorial/transformations-intro.htm
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class JavaFX2b01 extends Application {
	
	private final String TITLE = "JavaFX2b01: JavaFX area Intersect, Subtract and Union";
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;
	private final int RectangleX =  SCENE_WIDTH/2;
	private final int RectangleY =  SCENE_HEIGHT/2;
	private final int CircleX    =  3*SCENE_WIDTH/4;
	private final int CircleY    =  3*SCENE_HEIGHT/4;
	private Rectangle rectangle = new Rectangle(RectangleX,RectangleY,75,75);
	private Circle    circle    = new Circle( CircleX, CircleY, 90.0 );
	private Shape     result    = null;
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

			rectangle.setFill( Color.GREEN );
			wlist.add( rectangle );

			circle.setFill( Color.BLUE );
			wlist.add( circle );
			
			HBox topbox   = addTopBox();
			VBox leftbox  = addLeftBox();
			VBox centerbox = addCenterBox();
			root.setTop( topbox );
			root.setLeft( leftbox );
			root.setCenter( centerbox );
			
			primaryStage.setTitle( TITLE );
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	/*
	 * Creates an HBox with two buttons for the top region
	 */
    private HBox addTopBox() {
	   HBox hbox = new HBox();
	   hbox.setSpacing(10);   // Gap between nodes
	   ObservableList<Node> list = hbox.getChildren();
	   
	   Text t = new Text("Transforms: ");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

	   Button button1 = new Button("Move Left");
	   button1.setPrefSize(100, 20);
	   button1.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				rectangle.getTransforms().add( new Translate( -5, 0) );
			}
		});
		

	   Button button2 = new Button("Move Right");
	   button2.setPrefSize(100, 20);
	   
	   list.add(t);
	   list.add( button1 );
	   list.add( button2 );
	        
	   return hbox;
	}
	    
	/*
	 * Creates a VBox with a list of links for the left region
	 */
	private VBox addLeftBox() {
	   VBox box = new VBox();
	   ObservableList<Node> list = box.getChildren();
	   
	   ToggleGroup tg = new ToggleGroup();
	   
	   RadioButton intersect = new RadioButton("Intersect");
	   intersect.setPrefSize(100, 20);

	   RadioButton subtract = new RadioButton("Subtract");
	   subtract.setPrefSize(100, 20);
	   
	   intersect.setToggleGroup( tg );
	   subtract.setToggleGroup( tg );
	   
	   list.add( intersect );
	   list.add( subtract );

	   intersect.setOnAction(  new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if (wlist.contains( result ))
					wlist.remove( result );
				result = Shape.intersect( rectangle, circle );
				if (result != null)
					wlist.add( result );
			}
		});
	   subtract.setOnAction(  new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if (wlist.contains( result ))
					wlist.remove( result );
				result = Shape.subtract( rectangle, circle );
				if (result != null)
					wlist.add( result );
			}
		});

	   return box;
	}

	/*
	 * Creates a VBox with a list of links for the Center region
	 */
	private VBox addCenterBox() {
		VBox box = new VBox();
		ObservableList<Node> list = box.getChildren();
		Line line = new Line(10, 150, 300, 150);
		
		list.add( line );
		return box;
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}
}