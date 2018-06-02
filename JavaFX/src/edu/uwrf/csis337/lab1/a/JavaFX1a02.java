package edu.uwrf.csis337.lab1.a;

/*
 * JavaFX1a02
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class JavaFX1a02 extends Application {

	/**
	 * Data members of the class
	 */
	private final String TITLE = "Simple JavaFX Example";
	private final int SCENE_WIDTH = 400;
	private final int SCENE_HEIGHT = 300;


	/**
	 * The start method is where the window gets set up
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);

			primaryStage.setScene(scene);
			primaryStage.setTitle( TITLE );
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * main - this is where the program begins
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
