package edu.uwrf.csis337.lab1.a;

/**
 * JavaFX1a03lambda - This is an example of a basic Java FX program
 *   that creates a window with a scene and a menu bar and a
 *   basic event handler - using a lambda expression.
 *   
 * Note that we use classes like MenuBar, Menu, and MenuItem
 * just as we did in Swing but these classes come from the
 * javafx.scene.control package.
 * Handling a user event has similarities with Swing even though
 * JavaFX event handling is different.  
 * 
 * For more details see:
 *   https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/menu_controls.htm
 */

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;


public class JavaFX1a03lambda extends Application {

	/**
	 * Data members of the class
	 */
	private final String TITLE = "JavaFX1a03: Simple JavaFX Example with Menus";
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
			
			/* Set the response to Exit menu item
			 *   This is like having Swing's addActionListener and
			 *   the actionPerformed method all in one place 
			 */
			fileMenuItems[1].setOnAction( e -> {
				System.out.println("Event: " + e );
				Platform.exit();
			});
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
