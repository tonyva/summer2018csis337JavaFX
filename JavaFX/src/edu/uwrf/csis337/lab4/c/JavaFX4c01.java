package edu.uwrf.csis337.lab4.c;

/**
 * JavaFX4c01 - This is a basic JavaFX program with programmer-defined 3D shapes using TiangleMesh
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
import javafx.geometry.Point2D;
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
import javafx.scene.shape.VertexFormat;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX4c01 extends Application {
	
	private final String TITLE = "JavaFX4c01: JavaFX Basic Mesh Shapes";
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
				// Position the camera up a little and back a bit
				//  mainly so that we can see all three axes and
				//  the cylinder as the objects rotate around the y axis  
				cam.setTranslateX( -50 );
				cam.setTranslateY( -10 );
				cam.setTranslateZ( -50 );
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
		final Point3D TriangleP1 = new Point3D( 150,  20,  10);
		final Point3D TriangleP2 = new Point3D(  70, 150,  50);
		final Point3D TriangleP3 = new Point3D( 250, 200, 180);
		MeshView triangle = createTriangle( TriangleP1, TriangleP2, TriangleP3 );
		triangle.setMaterial( rpm );
		
		final Point3D RectangleP1 = new Point3D( 20, 320,  40);
		MeshView rectangle = createRectangle( RectangleP1 );
		rectangle.setMaterial( gpm );
		
		// Use 3-d boxes - long narrow ones - for each of the three axes.
		Box xaxis = new Box(300,   2,   2);
		Box yaxis = new Box(  2, 300,   2);
		Box zaxis = new Box(  2,   2, 300);
		xaxis.setMaterial( rpm );
		yaxis.setMaterial( gpm );
		zaxis.setMaterial( bpm );

		// group the elements and apply the rotation to the group 
		//    as a whole
		Group things = new Group( rectangle, triangle, xaxis, yaxis, zaxis );
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
	 * Set up a triangle from 3 points in 3D space
	 */
	private MeshView createTriangle(Point3D p0, Point3D p1, Point3D p2) {
		MeshView result = new MeshView();
		TriangleMesh mesh = new TriangleMesh( VertexFormat.POINT_TEXCOORD );
		
		/*
		 * A TriangleMesh is a mesh of triangles. The simplest mesh, other 
		 *    than the empty mesh is a single triangle.
		 */
		double[] trianglepoints = {
			p0.getX(), p0.getY(), p0.getZ(),
			p1.getX(), p1.getY(), p1.getZ(),
			p2.getX(), p2.getY(), p2.getZ()
		};
		
		/*
		 * Need texture coordinates even if we don't have texture
		 * Map the 3 vertices in the trianglepoints array to the 
		 * 2D unit square ( (0,0), (1,0), (1,1), (0,1) )
		 * 
		 *             t0
		 *  0,0 ---------------- 1,0
		 *      |      /\      |
		 *      |     /  \     |
		 *      |    /    \    |
		 *      |   /      \   |
		 *      |  /        \  |
		 *      | /          \ |
		 *      |/            \|
		 *  0,1 ----------------- 1,1
		 *  
		 *      t1              t2
		 */
		Point2D t0 = new Point2D( 0.5, 0 );
		Point2D t1 = new Point2D( 0,   1 );
		Point2D t2 = new Point2D( 1,   1 );
		double[] triangletextures = {
				t0.getX(), t0.getY(),
				t1.getX(), t1.getY(),
				t2.getX(), t2.getY()
		};
		
		/*
		 * Create faces
		 */
		int[] trianglefaces = {
				// front face:
				0, 0, // p0, t0
				1, 1, // p1, t1
				2, 2  // p2, t2
		};

		/*
		 * Add the 3 sets of data - locations, texture map, and definition of face(s) 
		 */
		mesh.getPoints().addAll( convertToFloat( trianglepoints ) );
		mesh.getTexCoords().addAll( convertToFloat(triangletextures) );
		mesh.getFaces().addAll( trianglefaces );
		
		/*
		 * mesh is done - set it in the MeshView object
		 */
		result.setMesh( mesh );
		return result;
	}


	/*
	 * Set up a rectangle from 1 point in 3D space
	 *  This method is for the simple case where the edges of the cuboid
	 *  that encloses the rectangle are parallel to the 3 axes - with this
	 *  constraint one point is the origin and a second point is all we
	 *  need to specify the rectangle
	 *  Therefore, the x, y, and z components of the one point specify the
	 *  width, height and depth of the cuboid enclosing the rectangle which
	 *  will be like a door on hinges.
     *
	 *  The more general case with two points in 3-d space to 
	 *   specify a rectangle is harder because it means having to figure out
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
		 * The four points of the rectangle are:
		 * 
		 *    p0  t0               t2 p2       
		 *    
		 *  0,0,0 ---------------- x,0,z
		 *        |\             |
		 *        |  \      f1   |
		 *        |    \         |
		 *        |      \       |
		 *        |        \     |
		 *        |   f2     \   |
		 *        |            \ |
		 *  0,y,0 ---------------  x,y,z
		 *   
		 *   p3 t3                t1 p1
		 *   
		 */
		float[] points = {
			0, 0, 0,   // p0
			x, y, z,   // p1
			x, 0, z,   // p2
			0, y, 0    // p3
		};
		
		/*
		 * Need texture coordinates even if we don't have texture
		 * Map the 4 vertices in the points array to the 
		 * 2D unit square ( (0,0), (1,0), (1,1), (0,1) )
		 * 
		 */
		float[] textures = {
				0.0f, 0.0f,
				1.0f, 1.0f,
				1.0f, 0.0f,
				0.0f, 1.0f
		};
		
		/*
		 * Create faces
		 */
		int[] faces = {
				0, 0, 1, 1, 2, 2,  // face f1 is p0 p1 p2 - counterclockwise
				0, 0, 3, 3, 1, 1,  // face f2 is p0 p3 p1 - counterclockwise
				0, 0, 2, 2, 1, 1,  // face b1 is p0 p2 p1 - clockwise
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
