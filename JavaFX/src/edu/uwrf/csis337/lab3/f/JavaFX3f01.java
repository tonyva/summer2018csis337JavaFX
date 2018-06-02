package edu.uwrf.csis337.lab3.f;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX3f01:  A simple game setup with
 *              - a background image that is bigger than the visible area
 *              - a set of 16 images for a character - a pirate
 *              this will have to be "cut" up into 16 pieces
 *    
 * @author Anthony Varghese
 *
 */
public class JavaFX3f01 extends Application {
	/*
	 *  Data members
	 */
	private final String TITLE = "Java03f: JavaFX 2D Game Example";
	//private static final long serialVersionUID = 1L;
	
	@Override
	public void start(Stage primaryStage) {
		// Create the game world first, then the root pane and the scene
		World world = new World();
		Pane  root = world; 
		Scene scene = new Scene(root, world.getPrefWidth(), world.getPrefHeight() );
		
		// Make the scene listen for key presses and send to player
		scene.setOnKeyPressed( new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				switch (keyEvent.getCode()){
				case LEFT:		world.setDirectionX( -1 ); break;
				case RIGHT:		world.setDirectionX( 1 );  break;
				case UP:		world.setDirectionY( -1 ); break;
				case DOWN:		world.setDirectionY( 1 );  break;
				default:
				}
				System.out.print("Key pressed, ");
			}
		});
		scene.setOnKeyReleased( new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				switch (keyEvent.getCode()){
				case LEFT:
				case RIGHT:		world.setDirectionX( 0 );	break;
				case UP:
				case DOWN:		world.setDirectionY( 0 );	break;
				default:
				}
				System.out.println("Key released");
			}
		});

		primaryStage.setTitle( TITLE );
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * main - this is where the program starts
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
