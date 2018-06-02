package edu.uwrf.csis337.lab2.g;

/**
 * JavaFX2g01 - This is an example of a basic JavaFX program with 2D lines
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Line.html
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX2g01 extends Application {
	private final String TITLE = "JavaFX2g01: JavaFX Example with 2D lines";
	private final int SCENE_WIDTH  = 700;
	private final int SCENE_HEIGHT = 300;

	private final int RX = 75;
	private final int RY = 10;
	private final int WIDTH  = 175;
	private final int HEIGHT = 100;
	private Rectangle rectangle = new Rectangle( RX, RY, WIDTH, HEIGHT);
	Stop[] stops = new Stop[]{
			new Stop(0, Color.BEIGE), 
            new Stop(1.0, Color.DEEPSKYBLUE)};
	private LinearGradient gradient= new
			LinearGradient(0,0, 1,1, true, CycleMethod.REPEAT, stops);
	
	private final int ECX    = 175;
	private final int ECY    = 150;
	private final int ERX    = 25;
	private final int ERY    = 75;
	private Ellipse   oval = new Ellipse( ECX, ECY, ERX, ERY );
	
	private Polyline line = new Polyline();
	private Path     path = new Path();
	
	private ObservableList<Node> wlist;
	private StrokeLineCap  lineCap;
	private StrokeLineJoin lineJoin;
	private int            lineWidth = 3;
	private int            lineDash  = 1;
	
	
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
    @SuppressWarnings("unchecked")
	private VBox addLeftBox() {
	   VBox box = new VBox();
	   box.setSpacing(10);   // Gap between nodes
	   ObservableList<Node> list = box.getChildren();
	   
	   Text t = new Text("Change Stroke style:");
	   t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

	   Text t1 = new Text("width");
	   t1.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Spinner<Integer> width = new Spinner<>( 1,20, lineWidth );
	   width.setPrefSize(75, 30);
	   width.setOnMouseClicked( event -> {
		  lineWidth =  ((Spinner<Integer>) event.getSource()).getValue();
		  refreshLineStyles();
	   } );
	   
	   Text t2 = new Text("cap");
	   t2.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   ObservableList<StrokeLineCap> capList = FXCollections.observableArrayList (
			   StrokeLineCap.BUTT,
			   StrokeLineCap.ROUND,
			   StrokeLineCap.SQUARE );
	   ComboBox<StrokeLineCap> cap = new ComboBox<>( capList );
	   cap.setPromptText("Line cap style");
	   cap.setPrefSize(150, 30);
	   // The next line uses a lambda for convenience
	   cap.setOnAction( event -> {
		   lineCap = ((ComboBox<StrokeLineCap>) event.getSource()).getValue();
		   refreshLineStyles();
	   });

	   Text t3 = new Text("join");
	   t3.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   ObservableList<StrokeLineJoin> joinList = FXCollections.observableArrayList ( 
				StrokeLineJoin.BEVEL,
				StrokeLineJoin.MITER,
				StrokeLineJoin.ROUND );
	   ComboBox<StrokeLineJoin> join = new ComboBox<>( joinList );
	   join.setPromptText("Line join style");
	   join.setPrefSize(150, 30);
	   join.setOnAction( event -> {
		   lineJoin = ((ComboBox<StrokeLineJoin>) event.getSource()).getValue();
		   refreshLineStyles();
	   });

	   Text t4 = new Text("Dash");
	   t1.setFont(Font.font("Arial", FontWeight.BOLD, 10 ));
	   Spinner<Integer> dash = new Spinner<>( 1, 10, lineDash );
	   dash.setPrefSize(75, 30);
	   dash.setOnMouseClicked( event -> {
		  lineDash =  ((Spinner<Integer>) event.getSource()).getValue();
		  refreshLineStyles();
	   } );
	   
	   
	   
	   list.add(t);
	   list.add( t1 );
	   list.add( width );
	   list.add( t2 );
	   list.add( cap );
	   list.add( t3 );
	   list.add( join );
	   list.add( t4 );
	   list.add( dash );
	        
	   return box;
	}

    private void refreshLineStyles(){
    	// change line widths for all
    	oval.setStrokeWidth( lineWidth );
    	rectangle.setStrokeWidth( lineWidth );
    	line.setStrokeWidth( lineWidth );
    	path.setStrokeWidth( lineWidth );
    	
    	oval.getStrokeDashArray().add( lineDash, 1.0 * lineDash );
    	rectangle.getStrokeDashArray().add( lineDash, 1.0 * lineDash );
    	line.getStrokeDashArray().add( lineDash, 1.0 * lineDash );
    	path.getStrokeDashArray().add( lineDash, 1.0 * lineDash );
    	
    	// change cap and join styles for line and path objects
    	rectangle.setStrokeLineCap(lineCap);
    	rectangle.setStrokeLineJoin(lineJoin);
    	line.setStrokeLineCap ( lineCap );
    	line.setStrokeLineJoin( lineJoin );
    	path.setStrokeLineCap ( lineCap );
    	path.setStrokeLineJoin( lineJoin );
    }

    
    
    /*
	 * Create a FlowPane for the center region
	 */
    private FlowPane addCenterPane() {
       FlowPane box = new FlowPane();
       box.setHgap(20);   // Gap between nodes
	   box.setAlignment(Pos.TOP_LEFT);
	   box.setMinSize(0, 0);
	   
	   wlist = box.getChildren();
	   
	   wlist.add( rectangle );
	   wlist.add( oval );
	   wlist.add( line );
	   wlist.add( path );

	   
	   oval.setFill( gradient );
	   oval.setStroke( Color.BLACK );
	   oval.getStrokeDashArray().addAll( 2d );
	   
	   rectangle.setFill( gradient );
	   rectangle.setStroke( Color.BLACK );
	   rectangle.getStrokeDashArray().addAll( 2d );

	   ObservableList<Double> l1p = line.getPoints();
	   l1p.addAll( new Double[]{
			    50.0,  50.0,
			    10.0,  75.0,
			    50.0, 100.0 });
	   line.getStrokeDashArray().addAll( 2d );
	   
	   ObservableList<PathElement> pathlist = path.getElements();
	   
		/*
		 * Start of path = 1st digit
		 */
		final int PathStartX =  25;
		final int PathStartY =  25;
		MoveTo mov = new MoveTo();
		mov.setX( PathStartX );
		mov.setY( PathStartY );
		
		// quad curve
		final int PathStartX4 =  15;
		final int PathStartY4 =  25;
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
		pathlist.add( mov );
		pathlist.add( qc1 );
		pathlist.add( qc2 );
		path.getStrokeDashArray().addAll( 2d );
		
		refreshLineStyles( );
	   
	   return box;
	}

	public static void main(String[] args) {
		launch(args);
	}
}