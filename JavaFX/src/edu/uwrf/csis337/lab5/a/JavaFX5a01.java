package edu.uwrf.csis337.lab5.a;

/**
 * JavaFX5a01 - JavaFX program with colors for custom 3D shapes
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
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX5a01 extends Application {
	
	private final String TITLE = "JavaFX5a01: Colors on 2D and 3D Shapes";
	private final int SCENE_WIDTH  = 600;
	private final int SCENE_HEIGHT = 300;

	private boolean enableDepthBuffer = true;
	private Rotate rotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS );
    

	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			root.setSpacing( 5 );
			root.setAlignment( Pos.TOP_CENTER );
			
			Scene scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT, enableDepthBuffer );
			scene.setFill( Color.BLACK );
			ObservableList<Node> list = root.getChildren();
			
			list.add( addPane() );
						
			if (enableDepthBuffer){
				PerspectiveCamera cam = new PerspectiveCamera();
				// Position the camera to the left, up a little and back a bit
				//  mainly so that we can see all three axes and
				//  the cylinder as the objects rotate around the y axis  
				cam.setTranslateX(  -50 );
				cam.setTranslateY( -150 );
				cam.setTranslateZ( -100 );
				scene.setCamera( cam );
			}
			primaryStage.setScene(scene);
			primaryStage.setTitle( TITLE );
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	private Pane addPane(){
		PhongMaterial rpm = new PhongMaterial();	rpm.setDiffuseColor(Color.RED);
		PhongMaterial gpm = new PhongMaterial();	gpm.setDiffuseColor(Color.GREEN);
		PhongMaterial bpm = new PhongMaterial();	bpm.setDiffuseColor(Color.BLUE);

		
		Pane box = new Pane();
		ObservableList<Node> list = box.getChildren();
		
		/*
		 * Nodes in this scene
		 */
		final Point3D CuboidP1 = new Point3D( 200, 120, 100);
		Group cuboid = createCuboid( CuboidP1 );
		
		// Use 3-d boxes - long narrow ones - for each of the three axes.
		Box xaxis = new Box(300,   2,   2);
		Box yaxis = new Box(  2, 300,   2);
		Box zaxis = new Box(  2,   2, 300);
		xaxis.setMaterial( rpm );
		yaxis.setMaterial( gpm );
		zaxis.setMaterial( bpm );

		// group the elements and apply the rotation to the group 
		//    as a whole
		Group things = new Group( cuboid, xaxis, yaxis, zaxis );
		things.getTransforms().add(rotate);
	
	    // rotate animation - timeline affects the rotation angle
	    Timeline rAnimation = new Timeline();
	    rAnimation.getKeyFrames().addAll( new KeyFrame( Duration.seconds( 20), new KeyValue( rotate.angleProperty(), 360 ) ) );
	    rAnimation.setCycleCount(Animation.INDEFINITE);
	    rAnimation.play();

		list.add( things );
		return box;
	}
	
	
	/*
	 * Set up a cuboid from 1 point in 3D space
	 *  The edges of the cuboid are parallel to the 3 axes - one point is 
	 *  the origin and a second point specifies the cuboid. Therefore, the
	 *  x, y, and z components are the width, height and depth of the cuboid.
	 *   
	 */
	private Group createCuboid( Point3D p1 ) {
		MeshView meshview = new MeshView();
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
		
		/*
		 * mesh is done - set it in the MeshView object
		 */
		meshview.setMesh( mesh );
		PhongMaterial mymatcolor = new PhongMaterial();
		mymatcolor.setDiffuseColor( Color.RED );
		meshview.setMaterial( mymatcolor );
		meshview.setTranslateX( 20 );
		meshview.setTranslateY( 20 );
		meshview.setTranslateZ( 20 );
		
		
		// Create a Canvas object to set up an ImagePattern
		final int canvasW = 256;
		final int canvasH = 256;
		Canvas canvas = new Canvas( canvasW, canvasH );
		GraphicsContext gc = canvas.getGraphicsContext2D();
		LinearGradient lg = new LinearGradient(0, 0, canvasW, canvasH,
                false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED  ),
                new Stop(1, Color.BLUE ));
		gc.setFill( lg );
		gc.fillOval(0, 0, 256, 256);
		canvas.setTranslateX( -100 );
		canvas.setTranslateY( -100 );
		canvas.setTranslateZ( 150 );

		
		Group result = new Group( meshview, canvas );
		return result;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
