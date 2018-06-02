package edu.uwrf.csis337.lab6.a;

/**
 * JavaFX6a01 - JavaFX program with 3D transformations of shapes
 *   
 * For more details see:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/TriangleMesh.html
 */


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SnapshotParameters;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX6a01 extends Application {
	
	private final String TITLE = "JavaFX6a01: 3D Transformations";
	private final int SCENE_WIDTH   = 700;
	private final int SCENE_HEIGHT  = 500;
	private final int LEFT_WIDTH    = 150;
	private final int RIGHT_WIDTH   = 150;
	private final int BOTTOM_HEIGHT = 200;
	private final int WINDOW_WIDTH  = SCENE_WIDTH - LEFT_WIDTH - RIGHT_WIDTH;
	private final int WINDOW_HEIGHT = SCENE_HEIGHT - BOTTOM_HEIGHT;

	/*=============================================================================
	 * Our scene with 3D objects with a camera
	 */
	private PerspectiveCamera cam = new PerspectiveCamera();
	/*
	 * Animation using Rotation transform
	 * - timeline affects the rotation angle in the animation
	 */
    Timeline rAnimation = new Timeline();
	private Rotate rotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS );
	/*
	 * 3D object
	 */
	private MeshView meshview;
	/*
	 * Ambient and directional light sources
	 */
	private AmbientLight whiteAmbientlight = new AmbientLight( Color.WHITE );
	private AmbientLight redAmbientlight   = new AmbientLight( Color.RED   );
	private AmbientLight greenAmbientlight = new AmbientLight( Color.GREEN );
	private AmbientLight blueAmbientlight  = new AmbientLight( Color.BLUE  );
	private PointLight   redPointlight     = new PointLight  ( Color.RED   ); 
	private PointLight   greenPointlight   = new PointLight  ( Color.GREEN );
	private PointLight   bluePointlight    = new PointLight  ( Color.BLUE  ); 
	private PointLight   whitePointlight   = new PointLight  ( Color.WHITE ); 
    private boolean redlights = false, greenlights = false, bluelights = false, whitelights = false;
	/*=============================================================================*/
    
    
	/*=============================================================================
	 * Our set of UI Controls
	 */
    
    
    
	@Override
	public void start(Stage primaryStage) {
			// Position the camera to the left, up a little and back a bit
			//  mainly so that we can see all three axes and
			//  the box as the objects rotate around the y axis  
			cam.setTranslateX( -150 );
			cam.setTranslateY( -150 );
			cam.setTranslateZ( -100 );
			cam.setRotationAxis( Rotate.X_AXIS); cam.setRotate( -30 );

			BorderPane root = new BorderPane();
			root.setCenter( addSubScene() );
			root.setLeft( setupLeftUIs() );
			root.setBottom( setupBottomUIs() );
			
			updateLights(); // Lights!
			Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, true );
			primaryStage.setScene(scene);
			primaryStage.setTitle( TITLE );
			primaryStage.show();
	}


	private void updateLights() {
		redAmbientlight  .setLightOn( redlights );
		greenAmbientlight.setLightOn( greenlights );
		blueAmbientlight .setLightOn( bluelights );
		whiteAmbientlight.setLightOn( whitelights );
		redPointlight    .setLightOn( redlights );
		greenPointlight  .setLightOn( greenlights );
		bluePointlight   .setLightOn( bluelights );
		whitePointlight  .setLightOn( whitelights );		
	}

	private SubScene addSubScene(){
		/*
		 * Nodes in this scene
		 */
		final Point3D CuboidP1 = new Point3D( 200, 120, 100);
		Group cuboid = createCuboid( CuboidP1 );
		
		// Use 3-d boxes - long narrow ones - for each of the three axes.
		PhongMaterial rpm = new PhongMaterial();	rpm.setDiffuseColor(Color.RED);
		PhongMaterial gpm = new PhongMaterial();	gpm.setDiffuseColor(Color.GREEN);
		PhongMaterial bpm = new PhongMaterial();	bpm.setDiffuseColor(Color.BLUE);
		Box xaxis = new Box(300,   2,   2);
		Box yaxis = new Box(  2, 300,   2);
		Box zaxis = new Box(  2,   2, 300);
		xaxis.setMaterial( rpm );
		yaxis.setMaterial( gpm );
		zaxis.setMaterial( bpm );

		// group the elements and apply the rotation to the group as a whole
		Group things = new Group( cuboid, xaxis, yaxis, zaxis );
		things.getTransforms().add(rotate);
	    rAnimation.getKeyFrames().addAll( new KeyFrame( Duration.seconds( 20), new KeyValue( rotate.angleProperty(), 360 ) ) );
	    rAnimation.setCycleCount(Animation.INDEFINITE);
	    rAnimation.play();
	
		// group the camera and the group it is view into one group
		Group myview = new Group( cam, things );

		// Create a "subwindow" with the camera and 3D objects in it 
		SubScene myscene = new SubScene( myview, WINDOW_WIDTH, WINDOW_HEIGHT, true, SceneAntialiasing.BALANCED );
		myscene.setCamera( cam );
		myscene.setFill( Color.BLACK );
	    return myscene;
	}
	
	
	/*
	 * Set up a cuboid from 1 point in 3D space
	 *  The edges of the cuboid are parallel to the 3 axes - one point is 
	 *  the origin and a second point specifies the cuboid. Therefore, the
	 *  x, y, and z components are the width, height and depth of the cuboid.
	 *   
	 */
	private Group createCuboid( Point3D p1 ) {
		meshview = new MeshView();
		TriangleMesh mesh = new TriangleMesh(); 
		/*
		 * A TriangleMesh is a mesh of triangles. 
		 * This mesh will have two triangles to create a single rectangle.
		 */
		final float x = (float) p1.getX(); 
		final float y = (float) p1.getY();
		final float z = (float) p1.getZ();
		
		/*
		 * The eight points of the cuboid are:
		 *    p2: 0,0,z ______________  p5: x,0,z
		 *             /|             /|       
		 *            / |            / |
		 *  p0: 0,0,0---------------- p7: x,0,0
		 *           |\ |           |  |
		 *           |  \      f1   |  |
		 *           |  | \         |  |
		 *           |  |   \       |  |
		 *     p4:0,y,z .-----\---- |--. p1 x,y,z
		 *           | / f2     \   | /
		 *           |/           \ |/
		 * p6: 0,y,0 ---------------  p3: x,y,0
		 *   
		 */
		float[] points = {
			0, 0, 0,   // p0
			x, y, z,   // p1
			0, 0, z,   // p2
			x, y, 0,   // p3
			0, y, z,   // p0
			x, 0, z,   // p1
			0, y, 0,   // p2
			x, 0, 0    // p3
		};
		
		/*
		 * Texture coordinates even if we don't have texture
		 * Map the 8 vertices in the points array to the 
		 * 2D unit square ( (0,0), (1,0), (1,1), (0,1) )
		 *  
		 *    p2       p0           p7     p5
		 * 0,0 .-------.------------.-------.
		 *     |       |            |       |
		 *     |     p6|          p3|       |p1
		 *  p4 .-------.------------.-------.
		 *     |       |            |       |
		 *     |       |            |       |
		 *     .     p4.------------.p1     .
		 *     |       |            |       |
		 *     |       |            |       |
		 *     .     p2.------------.p5     .
		 *     |       |            |       |
		 *     |       |            |       |
		 *     .-----p0.----------p7.-------. 1,1
		 */
		float[] textures = {
				0.0f, 0.0f,  //  t0 - p2
				0.3f, 0.0f,  //  t1 - p0
				0.7f, 0.0f,  //  t2 - p7
				1.0f, 0.0f,  //  t3 - p5
				0.0f, 0.25f, //  t4 - p4
				0.3f, 0.25f, //  t5 - p6
				0.7f, 0.25f, //  t6 - p3
				1.0f, 0.25f, //  t7 - p1
				0.3f, 0.5f,  //  t8 - p4
				0.7f, 0.5f,  //  t9 - p1
				0.3f, 0.75f, // t10 - p2
				0.7f, 0.75f, // t11 - p5
				0.3f, 1.0f,  // t12 - p0
				0.7f, 1.0f   // t13 - p7				
		};
		
		/*
		 * Create faces
		 */
		int[] faces = {
				0,  1,   3,  6,   7,  2, // front face
				0,  1,   6,  5,   3,  6,
				2, 10,   7, 13,   5, 11, // top face 
				2, 10,   0, 12,   7, 13,
				6,  5,   1,  9,   3,  6, // bottom face
				6,  5,   4,  8,   1,  9,
				2, 10,   5, 11,   1,  9, // back face
				2, 10,   1,  9,   4,  8,
				0,  1,   2,  0,   4,  4, // left face
				0,  1,   4,  4,   6,  5,
				7,  2,   3,  6,   1,  7, // right face
				7,  2,   1,  7,   5,  3
		};

		/*
		 * Add the 3 sets of data - locations, texture map, and definition of face(s) 
		 */
		mesh.getPoints().addAll( points );
		mesh.getTexCoords().addAll( textures );
		mesh.getFaces().addAll( faces );	
		/* mesh is done - set it in the MeshView object	 */
		meshview.setMesh( mesh );
		
		// Create a Canvas object to set up an Image object
		final int canvasW = 256;
		final int canvasH = 256;
		Canvas c = new Canvas( canvasW, canvasH );
		GraphicsContext gc = c.getGraphicsContext2D();
		LinearGradient lg1 = new LinearGradient(0, 0, canvasW, canvasH,
                false, CycleMethod.REPEAT,
                new Stop(0,   Color.POWDERBLUE ),
                new Stop(0.5, Color.AQUAMARINE ),
                new Stop(1.0, Color.PURPLE ));
		gc.setFill( lg1 );
		gc.fillRect(0, 0, 128, 255);
		LinearGradient lg2 = new LinearGradient(0, 0, canvasW, canvasH,
                false, CycleMethod.REPEAT,
                new Stop(0,   Color.GREEN  ),
                new Stop(0.5, Color.ROSYBROWN ),
                new Stop(1.0, Color.ORANGE ));
		gc.setFill( lg2 );
		gc.fillRect(128, 0, 128, 255);

		Image myimage = c.snapshot(new SnapshotParameters(), null);

		PhongMaterial mymaterial = new PhongMaterial();
		// set the different properties of the material
		mymaterial.setSelfIlluminationMap( myimage );
		final Color diffuseColor = Color.RED;
		final Color specularColor = Color.BLUE;
		final double specularPower = 5;
		mymaterial.setDiffuseColor( diffuseColor );
		mymaterial.setSpecularColor( specularColor );
		mymaterial.setDiffuseMap( myimage );
		mymaterial.setSpecularMap( myimage );
		mymaterial.setSpecularPower( specularPower );
		meshview.setMaterial( mymaterial );
		
		
		Group result = new Group( meshview,
				greenAmbientlight, blueAmbientlight, redAmbientlight, whiteAmbientlight,
				greenPointlight,   bluePointlight,   redPointlight,   whitePointlight );
		return result;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	private GridPane setupLeftUIs() {
		GridPane grid = new GridPane();
		grid.setPadding( new Insets( 5, 5, 5, 5) );
		
		Text lights = new Text("Lights");
		lights.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		Text red = new Text("Red");
		red.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		red.setFill( Color.RED );
		Text redX = new Text("X");
		redX.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		redX.setFill( Color.RED );
		Text redY = new Text("Y");
		redY.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		redY.setFill( Color.RED );
		Text redZ = new Text("Z");
		redZ.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		redZ.setFill( Color.RED );

	    CheckBox redlightbox   = new CheckBox("Red lights");
		redlightbox.setOnAction( new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				redlights = redlightbox.isSelected();
				updateLights();
			}
		});
		Slider redSliderX = new Slider(-50,50,0);
		redSliderX.setShowTickLabels(true);
		redSliderX.setShowTickMarks(true);
		redSliderX.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				redPointlight.setTranslateX( redSliderX.getValue() );
			}
		});
		Slider redSliderY = new Slider(-50,50,0);
		redSliderY.setShowTickLabels(true);
		redSliderY.setShowTickMarks(true);
		redSliderY.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				redPointlight.setTranslateY( redSliderY.getValue() );
			}
		});
		Slider redSliderZ = new Slider(-50,50,0);
		redSliderZ.setShowTickLabels(true);
		redSliderZ.setShowTickMarks(true);
		redSliderZ.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				redPointlight.setTranslateZ( redSliderZ.getValue() );
			}
		});

		int row = 0;
		grid.add( lights,      0, row );
		row++;
		grid.add( red,         0, row );
		grid.add( redlightbox, 1, row );
		row++;
		grid.add( redX,        0, row );
		grid.add( redSliderX,  1, row );
		row++;
		grid.add( redY,        0, row );
		grid.add( redSliderY,  1, row );
		row++;
		grid.add( redZ,        0, row );
		grid.add( redSliderZ,  1, row );

		Text green = new Text("Green");
		green.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		green.setFill( Color.GREEN );
		Text greenX = new Text("X");
		greenX.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		greenX.setFill( Color.GREEN );
		Text greenY = new Text("Y");
		greenY.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		greenY.setFill( Color.GREEN );
		Text greenZ = new Text("Z");
		greenZ.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		greenZ.setFill( Color.GREEN );

	    CheckBox greenlightbox = new CheckBox("Green lights");
		greenlightbox.setOnAction( new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				greenlights = greenlightbox.isSelected();
				updateLights();
			}
		});
		Slider greenSliderX = new Slider(-50,50,0);
		redSliderX.setShowTickLabels(true);
		redSliderX.setShowTickMarks(true);
		redSliderX.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				greenPointlight.setTranslateX( greenSliderX.getValue() );
			}
		});
		Slider greenSliderY = new Slider(-50,50,0);
		greenSliderY.setShowTickLabels(true);
		greenSliderY.setShowTickMarks(true);
		greenSliderY.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				greenPointlight.setTranslateY( greenSliderY.getValue() );
			}
		});
		Slider greenSliderZ = new Slider(-50,50,0);
		greenSliderZ.setShowTickLabels(true);
		greenSliderZ.setShowTickMarks(true);
		greenSliderZ.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				greenPointlight.setTranslateZ( greenSliderZ.getValue() );
			}
		});

		row++;
		grid.add( green,         0, row );
		//row++;
		grid.add( greenlightbox, 1, row );
		row++;
		grid.add( greenX,        0, row );
		grid.add( greenSliderX,  1, row );
		row++;
		grid.add( greenY,        0, row );
		grid.add( greenSliderY,  1, row );
		row++;
		grid.add( greenZ,        0, row );
		grid.add( greenSliderZ,  1, row );

		
		Text blue = new Text("Blue");
		blue.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		blue.setFill( Color.BLUE );
		Text blueX = new Text("X");
		blueX.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		blueX.setFill( Color.BLUE );
		Text blueY = new Text("Y");
		blueY.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		blueY.setFill( Color.BLUE );
		Text blueZ = new Text("Z");
		blueZ.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		blueZ.setFill( Color.BLUE );

	    CheckBox bluelightbox  = new CheckBox("Blue lights");
		bluelightbox.setOnAction( new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				bluelights = bluelightbox.isSelected();
				updateLights();
			}
		});
		Slider blueSliderX = new Slider(-50,50,0);
		redSliderX.setShowTickLabels(true);
		redSliderX.setShowTickMarks(true);
		redSliderX.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				bluePointlight.setTranslateX( blueSliderX.getValue() );
			}
		});
		Slider blueSliderY = new Slider(-50,50,0);
		blueSliderY.setShowTickLabels(true);
		blueSliderY.setShowTickMarks(true);
		blueSliderY.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				bluePointlight.setTranslateY( blueSliderY.getValue() );
			}
		});
		Slider blueSliderZ = new Slider(-50,50,0);
		blueSliderZ.setShowTickLabels(true);
		blueSliderZ.setShowTickMarks(true);
		blueSliderZ.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				bluePointlight.setTranslateZ( blueSliderZ.getValue() );
			}
		});

		row++;
		grid.add( blue,         0, row );
		//row++;
		grid.add( bluelightbox, 1, row );
		row++;
		grid.add( blueX,        0, row );
		grid.add( blueSliderX,  1, row );
		row++;
		grid.add( blueY,        0, row );
		grid.add( blueSliderY,  1, row );
		row++;
		grid.add( blueZ,        0, row );
		grid.add( blueSliderZ,  1, row );

		return grid;
	}

	private GridPane setupBottomUIs() {
		GridPane grid = new GridPane();
		grid.setPadding( new Insets( 5, 5, 5, 5) );
		
		Text tlabel = new Text("Transforms");
		tlabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		Text translate = new Text("Translate");
		translate.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		translate.setFill( Color.RED );
		Text transX = new Text("X");
		transX.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		transX.setFill( Color.RED );
		Text transY = new Text("Y");
		transY.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		transY.setFill( Color.RED );
		Text transZ = new Text("Z");
		transZ.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		transZ.setFill( Color.RED );

		
		Slider transSliderX = new Slider(-50,50,0);
		transSliderX.setShowTickLabels(true);
		transSliderX.setShowTickMarks(true);
		transSliderX.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				meshview.setTranslateX( transSliderX.getValue() );
			}
		});
		Slider transSliderY = new Slider(-50,50,0);
		transSliderY.setShowTickLabels(true);
		transSliderY.setShowTickMarks(true);
		transSliderY.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				meshview.setTranslateY( transSliderY.getValue() );
			}
		});
		Slider transSliderZ = new Slider(-50,50,0);
		transSliderZ.setShowTickLabels(true);
		transSliderZ.setShowTickMarks(true);
		transSliderZ.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				meshview.setTranslateZ( transSliderZ.getValue() );
			}
		});


		int row = 0;
		int col = 0;
		grid.add( tlabel,         col,   row );
		row++;
		grid.add( transX,        col,   row );
		grid.add( transSliderX,  col+1, row );
		row++;
		grid.add( transY,        col,   row );
		grid.add( transSliderY,  col+1, row );
		row++;
		grid.add( transZ,        col,   row );
		grid.add( transSliderZ,  col+1, row );

		/*
		 * Rotates
		 */
		Text rotate = new Text("rotate");
		rotate.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		rotate.setFill( Color.RED );
		Text rotX = new Text("X");
		rotX.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		rotX.setFill( Color.RED );
		Text rotY = new Text("Y");
		rotY.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		rotY.setFill( Color.RED );
		Text rotZ = new Text("Z");
		rotZ.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		rotZ.setFill( Color.RED );

		
		Slider rotSliderX = new Slider(-50,50,0);
		rotSliderX.setShowTickLabels(true);
		rotSliderX.setShowTickMarks(true);
		rotSliderX.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				meshview.setRotationAxis(Rotate.Y_AXIS);
				meshview.setRotate( rotSliderX.getValue() );
			}
		});
		Slider rotSliderY = new Slider(-50,50,0);
		rotSliderY.setShowTickLabels(true);
		rotSliderY.setShowTickMarks(true);
		rotSliderY.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				meshview.setRotationAxis(Rotate.Z_AXIS);
				meshview.setRotate( rotSliderY.getValue() );
			}
		});
		Slider rotSliderZ = new Slider(-50,50,0);
		rotSliderZ.setShowTickLabels(true);
		rotSliderZ.setShowTickMarks(true);
		rotSliderZ.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				meshview.setRotationAxis(Rotate.X_AXIS);
				meshview.setRotate( rotSliderZ.getValue() );
			}
		});


		row = 0;
		col = 2;
		grid.add( rotate,       col,   row );
		row++;
		grid.add( rotX,        col,   row );
		grid.add( rotSliderX,  col+1, row );
		row++;
		grid.add( rotY,        col,   row );
		grid.add( rotSliderY,  col+1, row );
		row++;
		grid.add( rotZ,        col,   row );
		grid.add( rotSliderZ,  col+1, row );

		return grid;
	}

}
