package edu.uwrf.csis337.lab3.f;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class World extends Pane {
	private Player p1;
	private int imageWidth;
	private int imageHeight;
	private static final String DIR = "file:src/edu/uwrf/csis337/lab3/f/";
	private static final String worldFile = DIR + "worldimage.jpg";

	public World(){
		Image im = new Image( worldFile );
		imageWidth  = (int) im.getWidth();
		imageHeight = (int) im.getHeight();
		setPrefSize( imageWidth/2 + 20, imageHeight+20 );
		
		ImageView iv = new ImageView( im );
		getChildren().add( iv );

		p1 = new Player();
		ImageView p = new ImageView( p1.getImage() );
		getChildren().add( p );
		p.setTranslateX( p1.getX() );
		p.setTranslateY( p1.getY() );
	}
	
	public void setDirectionX(int d){
		p1.setDelta_x( d );
		p1.move();
		getChildren().remove( 1 );
		ImageView p = new ImageView( p1.getImage() );
		getChildren().add( p );
		p.setTranslateX( p1.getX() );
		p.setTranslateY( p1.getY() );
	}
	
	public void setDirectionY(int d){
		p1.setDelta_y( d );
		p1.move();
		getChildren().remove( 1 );
		ImageView p = new ImageView( p1.getImage() );
		getChildren().add( p );
		p.setTranslateX( p1.getX() );
		p.setTranslateY( p1.getY() );
	}
}
