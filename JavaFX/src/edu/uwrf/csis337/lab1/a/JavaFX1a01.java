package edu.uwrf.csis337.lab1.a;

/*
 * JavaFX1a01 - starter JavaFX program
 *  Creates an empty window.
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class JavaFX1a01 extends Application {

	/**
	 * The start method is where the window gets set up
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);

			primaryStage.setScene(scene);
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
		System.out.println("Launching JavaFX application ... ");
		Application.launch(args);
		System.out.println(" ... done!");
	}
}
