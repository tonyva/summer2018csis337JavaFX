package edu.uwrf.csis337.lab3.c;

/**
 * JavaFX3c01 - This is an example of a JavaFX program with 2D shapes,
 * 			 colors, and opacity
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Paint.html
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX3c01 extends Application {
	
	private final String TITLE = "JavaFX3c01: JavaFX Example with Colors and Opacity";
	
	private final int SCENE_WIDTH  = 400;
	private final int SCENE_HEIGHT = 300;
	private final int WIDTH  = 175;
	private final int HEIGHT = 100;
	
	private Rectangle rectangle = new Rectangle( WIDTH, HEIGHT);
	private final int ERX    = 25;
	private final int ERY    = 75;
	private double rectangle_opacity = 0.0;
	Stop[] rectangleStops = new Stop[]{
			new Stop(0, Color.AQUAMARINE ), 
            new Stop(1.0, Color.GREENYELLOW )};
	private LinearGradient rectangleGradient= new
			LinearGradient(0,0, 1,1, true, CycleMethod.REPEAT, rectangleStops);

	private Ellipse   oval = new Ellipse( ERX, ERY );
	private double ellipse_opacity = 0.0;
	Stop[] ellipseStops = new Stop[]{
			new Stop(0, Color.BLUEVIOLET), 
            new Stop(1.0, Color.RED)};
	private RadialGradient ellipseGradient= new
			RadialGradient(10, 0, 0.5, 0.5, 1.0, true, CycleMethod.REPEAT, ellipseStops);
	
	private BlendMode blendMode = BlendMode.SRC_OVER;
	
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
			root.setRight( addRightBox() );
			root.setCenter( addCenterPane() );
			root.setBottom( addBottomBox() );

			primaryStage.setTitle( TITLE );
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	/*
	 * Creates a FlowPane with text and a slider for the left region
	 */
    private FlowPane addLeftBox() {
    	FlowPane box = new FlowPane( Orientation.VERTICAL );
    	box.setColumnHalignment(HPos.LEFT);
    	box.setVgap(10);   // Gap between nodes
    	ObservableList<Node> list = box.getChildren();
	   
    	Text t = new Text("Rectangle\n Opacity: ");
    	t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    	Slider r = new Slider( 0, 1, rectangle_opacity );
    	r.setOrientation( Orientation.VERTICAL );
    	r.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rectangle.setFill( rectangleGradient );
				rectangle_opacity = r.getValue();
				rectangle.setOpacity( rectangle_opacity );
			}
		});

    	list.add( t );
    	list.add( r );    
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
	   box.setBackground( new Background( new BackgroundFill( Color.BEIGE , CornerRadii.EMPTY, Insets.EMPTY ) ) );
	   
	   wlist = box.getChildren();
	   
	   wlist.add( rectangle );
	   wlist.add( oval );
	   rectangle.setFill( rectangleGradient );
	   rectangle.setOpacity( rectangle_opacity );
	   oval.setFill( ellipseGradient );
	   oval.setOpacity( ellipse_opacity );
	   
	   return box;
	}

	/*
	 * Creates a FlowPane with text and a slider for the right region
	 */
    private FlowPane addRightBox() {
    	FlowPane box = new FlowPane( Orientation.VERTICAL );
    	box.setColumnHalignment(HPos.RIGHT);
    	box.setVgap(10);   // Gap between nodes
    	ObservableList<Node> list = box.getChildren();
	   
    	Text t = new Text("Ellipse\n Opacity: ");
    	t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    	Slider e = new Slider( 0, 1, ellipse_opacity );
    	e.setOrientation( Orientation.VERTICAL );
    	e.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				oval.setFill( ellipseGradient );
				ellipse_opacity = e.getValue();
				oval.setOpacity( ellipse_opacity );
			}
		});

    	list.add( t );
    	list.add( e );    
    	return box;
	}

	/*
	 * Creates a HBox with a ComboBox for the right region
	 */
	@SuppressWarnings("unchecked")
	private HBox addBottomBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		ObservableList<Node> list = box.getChildren();
		
		Text t = new Text("Blend Mode");
		t.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		
		/*
		 * Set up a combobox for this panel
		 */
		ComboBox<BlendMode> combo = new ComboBox<>();
		combo.getItems().addAll( 
				BlendMode.ADD , BlendMode.BLUE,
				BlendMode.COLOR_BURN, BlendMode.COLOR_DODGE,
				BlendMode.DARKEN,     BlendMode.DIFFERENCE, 
				BlendMode.EXCLUSION,  BlendMode.GREEN,
				BlendMode.HARD_LIGHT, BlendMode.LIGHTEN,
				BlendMode.MULTIPLY,   BlendMode.OVERLAY,
				BlendMode.RED,        BlendMode.SCREEN, 
				BlendMode.SOFT_LIGHT, BlendMode.SRC_ATOP,
				BlendMode.SRC_OVER );
		combo.setValue( BlendMode.SRC_OVER );
		combo.setOnAction( event -> {
			blendMode =  ((ComboBox<BlendMode>) event.getSource()).getValue();
			rectangle.setBlendMode( blendMode );
			oval.setBlendMode( blendMode );
		});

		list.add( t );
		list.add( combo );
		return box;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
