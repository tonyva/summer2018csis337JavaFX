package edu.uwrf.csis337.lab1.a;

/**
 * JavaFX1a04 - This is an example of a basic Java FX program
 *   that creates a window with a scene various 2D shapes.
 *   
 * 
 * For more details see: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Shape.html
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class JavaFX1a04 extends Application {

	/**
	 * Data members of the class
	 */
	private final String TITLE = "JavaFX1a04: Simple JavaFX Example with Shapes";
	private final int SCENE_WIDTH = 400;
	private final int SCENE_HEIGHT = 300;

	/* 
	 * Menu items
	 */
	private Menu fileMenu, editMenu;
	private final String fileMenuString = "File";
	private final String editMenuString = "Edit";
	private MenuItem[] fileMenuItems;
	private final String[] fileMenuItemStrings = {"Open" , "Exit" };
	private MenuBar menubar;

	/*
	 * Shapes to be drawn
	 */
	private final int number_shapes = 5;
	private Shape[]   myshapes = new Shape[ number_shapes ];
	
	/**
	 * The start method is where we will set up the menus
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);

			primaryStage.setScene(scene);
			primaryStage.setTitle( TITLE );
			
			/* Initialize Menu System */
			fileMenuItems = new MenuItem[2];
			fileMenuItems[0] = new MenuItem( fileMenuItemStrings[0] );
			fileMenuItems[1] = new MenuItem( fileMenuItemStrings[1] );
			fileMenu = new Menu( fileMenuString );
			fileMenu.getItems().add( fileMenuItems[0] );
			fileMenu.getItems().add( fileMenuItems[1] );
			editMenu = new Menu( editMenuString );
			menubar = new MenuBar();
			menubar.getMenus().addAll( fileMenu, editMenu );
			
			/* Add the menu bar to the window */
			root.setTop( menubar );
			
			/* Set the response to Exit menu item */
			fileMenuItems[1].setOnAction( new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					Platform.exit();
				}
			});
			
			ObservableList<Node> obs = root.getChildren();
			addShapes(obs);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * The addShapes method is where we will set up all the nodes
	 *   to be drawn. All of the 2-D Shape objects like Line, Rectangle,
	 *   and Ellipse inherit from Node - lots of polymorphism used
	 *   here.
	 */
	private void addShapes(ObservableList<Node> obs) {
		myshapes = new Shape[ number_shapes ];
		
		float minY = 30.0f;
		
		/* Two ways to create a line */
		Line line1 = new Line();
		line1.setStartX(0.0f);
		line1.setStartY( minY );
		line1.setEndX(200.0f);
		line1.setEndY( minY );
		Line line2 = new Line( 0, minY, 0, 200);
		
		/* Rectangles */
		Rectangle rectangle1 = new Rectangle(75,minY,40,50);
		rectangle1.setFill(Color.GREEN);
		Rectangle rectangle2 = new Rectangle(50,75,60,50);
		rectangle2.setArcWidth(30);
		rectangle2.setArcHeight(30);
		rectangle2.setFill(Color.RED);
		Ellipse ellipse = new Ellipse(205,150,70,100);
		ellipse.setFill(Color.BLUE);
		
		/* Polymorphism!! */
		myshapes[0] = line1;
		myshapes[1] = line2;
		myshapes[2] = rectangle1;
		myshapes[3] = rectangle2;
		myshapes[4] = ellipse;
				
		for (Shape s : myshapes)
			obs.add( s );
	}

	public static void main(String[] args) {
		launch(args);
	}
}
