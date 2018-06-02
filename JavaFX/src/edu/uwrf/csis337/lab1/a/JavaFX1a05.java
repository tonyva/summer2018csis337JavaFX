package edu.uwrf.csis337.lab1.a;

/**
 * JavaFX1a05 - This is an example of a basic Java FX program
 *   that creates a window with a scene various 2D shapes.
 *   
 * 
 * For more details see: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Shape.html
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.GaussianBlur;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;




public class JavaFX1a05 extends Application {
	/**
	 * Data members of the class
	 */
	// generates a warning, not necessary:
	// private static final long serialVersionUID = 1L;
	private final String TITLE = "JavaFX1a05: Simple JavaFX Example with More 2D Shapes";
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

	/*
	 * Text and fonts
	 */
	private final String text1 = "CSIS 337";
	private final String text2 = "Computer Graphics";
	private int font_size = 80;
	private final Font font1 = new Font( "Arial", font_size );
	private final Font font2 = new Font( "Times", font_size/2 );

	
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

	private void addShapes(ObservableList<Node> obs) {
		myshapes = new Shape[ number_shapes ];
		
		float minY = 30.0f;
		
		/* Two ways to create a line */
		Line line1 = new Line();
		line1.setStartX(0.0f);
		line1.setStartY( minY );
		line1.setEndX(200.0f);
		line1.setEndY( minY );
		
		/* Rectangles */
		Rectangle rectangle1 = new Rectangle(275,minY,40,50);
		rectangle1.setFill(Color.GREEN);
		Rectangle rectangle2 = new Rectangle(50,75,60,50);
		rectangle2.setArcWidth(30);
		rectangle2.setArcHeight(30);
		rectangle2.setFill(Color.RED);
		Ellipse ellipse = new Ellipse(205,150,70,30);
		ellipse.setFill(Color.BLUE);

		/* Create Text Objects */
		Text t1 = new Text( 15, 240, text1 );
		t1.setFont( font1 );
		t1.setEffect( new GaussianBlur() );
		Text t2 = new Text( 15, 290, text2 );
		t2.setFont( font2 );

		/* Polymorphism!! */
		myshapes[0] = rectangle1;
		myshapes[1] = rectangle2;
		myshapes[2] = ellipse;
		myshapes[3] = t1;
		myshapes[4] = t2;

		
		/* Add all the shapes to the window. */
		for (Shape s : myshapes)
			obs.add( s );
	}

	public static void main(String[] args) {
		launch(args);
	}
}