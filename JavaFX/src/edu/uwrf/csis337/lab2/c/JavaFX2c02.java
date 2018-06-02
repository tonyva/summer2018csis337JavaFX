package edu.uwrf.csis337.lab2.c;

/**
 * Java02e - This is an example of a basic JavaFX program with 2D shapes
 *           - Path
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/package-summary.html
 * 
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;

public class JavaFX2c02 extends Application {
	
	private final String TITLE = "JavaFX2c02: JavaFX Path Example";
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;
	private Path        path  = new Path();
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

			wlist.add( path );

			
			/*
			 * Start of path = 1st digit
			 */
			final int PathStartX =  25;
			final int PathStartY =  25;
			MoveTo mov = new MoveTo();
			mov.setX( PathStartX );
			mov.setY( PathStartY );
			
			// top line
			LineTo line = new LineTo();
			line.setX( PathStartX + 100);
			line.setY( PathStartY   );
			
			// line down to middle
			LineTo line1 = new LineTo();
			line1.setX( PathStartX + 50 );
			line1.setY( PathStartY + 50 );

			// the curve to the end
			CubicCurveTo cc = new CubicCurveTo();
			cc.setX( PathStartX );
			cc.setY( PathStartY + 100 );
			cc.setControlX1( PathStartX + 150 );
			cc.setControlY1( PathStartY +  75 );
			cc.setControlX2( PathStartX +  75 );
			cc.setControlY2( PathStartY + 200 );

			/*
			 * Start of 2nd digit
			 */
			final int PathStartX2 =  150;
			final int PathStartY2 =   25;
			MoveTo mov2 = new MoveTo();
			mov2.setX( PathStartX2 );
			mov2.setY( PathStartY2 );
			
			// top line
			LineTo line2 = new LineTo();
			line2.setX( PathStartX2 + 100);
			line2.setY( PathStartY2   );
			
			// line down to middle
			LineTo line3 = new LineTo();
			line3.setX( PathStartX2 + 50 );
			line3.setY( PathStartY2 + 50 );

			// the curve to the end
			CubicCurveTo cc2 = new CubicCurveTo();
			cc2.setX( PathStartX2 );
			cc2.setY( PathStartY2 + 100 );
			cc2.setControlX1( PathStartX2 + 150 );
			cc2.setControlY1( PathStartY2 +  75 );
			cc2.setControlX2( PathStartX2 +  75 );
			cc2.setControlY2( PathStartY2 + 200 );

			/*
			 * Start of 3rd digit
			 */
			final int PathStartX3 =  275;
			final int PathStartY3 =   25;
			MoveTo mov3 = new MoveTo();
			mov3.setX( PathStartX3 );
			mov3.setY( PathStartY3 );
			
			// top line
			LineTo line4 = new LineTo();
			line4.setX( PathStartX3 + 100);
			line4.setY( PathStartY3   );
			
			// line down to left
			LineTo line5 = new LineTo();
			line5.setX( PathStartX3 );
			line5.setY( PathStartY3 + 140 );

			/*
			 * Start of closed path
			 */
			final int PathStartX4 =  150;
			final int PathStartY4 =  225;
			MoveTo mov4 = new MoveTo();
			mov4.setX( PathStartX4 );
			mov4.setY( PathStartY4 );
			
			// quad curve
			QuadCurveTo qc1 = new QuadCurveTo();
			qc1.setX( PathStartX4 + 50);
			qc1.setY( PathStartY4 );
			qc1.setControlX( PathStartX4 + 25 );
			qc1.setControlY( PathStartY4 + 25 );

			// quad curve
			QuadCurveTo qc2 = new QuadCurveTo();
			qc2.setX( PathStartX4 + 50);
			qc2.setY( PathStartY4 + 50 );
			qc2.setControlX( PathStartX4 + 25 );
			qc2.setControlY( PathStartY4 + 25 );
			
			// quad curve
			QuadCurveTo qc3 = new QuadCurveTo();
			qc3.setX( PathStartX4 );
			qc3.setY( PathStartY4 + 50 );
			qc3.setControlX( PathStartX4 + 25 );
			qc3.setControlY( PathStartY4 + 25 );
			
			// close this path
			ClosePath cp = new ClosePath();
			
			
			ObservableList<PathElement> pathlist = path.getElements();
			pathlist.add( mov );
			pathlist.add( line );
			pathlist.add( line1 );
			pathlist.add( cc );
			pathlist.add( mov2 );
			pathlist.add( line2 );
			pathlist.add( line3 );
			pathlist.add( cc2 );
			pathlist.add( mov3 );
			pathlist.add( line4 );
			pathlist.add( line5 );
			pathlist.add( mov4 );
			pathlist.add( qc1 );
			pathlist.add( qc2 );
			pathlist.add( qc3 );
			pathlist.add( cp );
			
			path.setStrokeWidth( 5 );
			
			primaryStage.setTitle( TITLE );
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}