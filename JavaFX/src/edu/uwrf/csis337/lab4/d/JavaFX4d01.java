package edu.uwrf.csis337.lab4.d;

/**
 * JavaFX4d01 - JavaFX program with programmer-defined 3D shapes using TiangleMesh
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX4d01 extends Application {
	
	private final String TITLE = "JavaFX4d01: JavaFX 3D Mesh Shapes";
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
		PhongMaterial rpm = new PhongMaterial();
		rpm.setDiffuseColor(Color.RED);
		PhongMaterial gpm = new PhongMaterial();
		gpm.setDiffuseColor(Color.GREEN);
		PhongMaterial bpm = new PhongMaterial();
		bpm.setDiffuseColor(Color.BLUE);

		
		Pane box = new Pane();
		ObservableList<Node> list = box.getChildren();
		
		/*
		 * Nodes in this scene
		 */
		final Point3D CuboidP1 = new Point3D( 200, 120, 100);
		MeshView cuboid = createRectangle( CuboidP1 );
		cuboid.setMaterial( gpm );
		
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
	
	
	/**
	 * convertToFloat
	 *    create an array of floats from an array of doubles
	 *    
	 * @param in - double array
	 * @return float array
	 */
	private float[] convertToFloat( double[] in ) {
		float[] out = new float[ in.length ];

		for (int i=0; i<in.length; i++)
			out[i] = (float) in[i];
		
		return out;
	}

	/*
	 * Set up a cuboid from 1 point in 3D space
	 *  This method is for the simple case where the edges of the cuboid
	 *  are parallel to the 3 axes - with this constraint one point is 
	 *  the origin and a second point is all we need to specify the cuboid.
	 *  Therefore, the x, y, and z components of the one point specify the
	 *  width, height and depth of the cuboid.
     *
	 *  The more general case with two points in 3-d space to 
	 *   specify a cuboid is harder because it means having to figure out
	 *   the remaining 6 points of the cuboid - a lot of geometry
	 *   in 3-dimensions
	 *   
	 */
	private MeshView createRectangle( Point3D p1 ) {
		MeshView result = new MeshView();
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
			0, y, z,   // p4
			x, 0, z,   // p5
			0, y, 0,   // p6
			x, 0, 0    // p7
		};
		
		/*
		 * Need texture coordinates even if we don't have texture
		 * Map the 8 vertices in the points array to the 
		 * 2D unit square ( (0,0), (1,0), (1,1), (0,1) )
		 * 
		 * Here we decided to map the 6 rectangular faces of the cuboid to
		 * the unit square using the following layout. This is just one
		 * possibility - there are many more mappings that we can use.
		 * 
		 * 0,0 
		 *    p2       p0           p7     p5
		 *     .-------.------------.-------.
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
		 *     .-------.------------.-------. 1,1
		 *             p0           p7
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
				// front face triangles:
				0,  1,   3,  6,   7,  2,
				0,  1,   6,  5,   3,  6,
				// top face triangles:
				2, 10,   7, 13,   5, 11,
				2, 10,   0, 12,   7, 13,
				// bottom face triangles:
				6,  5,   1,  9,   3,  6,
				6,  5,   4,  8,   1,  9,
				// back face triangles:
				2, 10,   5, 11,   1,  9,
				// left face triangles:
				0,  1,   2,  0,   4,  4,
				0,  1,   4,  4,   6,  5,
				// right face triangles:
				7,  2,   3,  6,   1,  7
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
		result.setMesh( mesh );
		return result;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
