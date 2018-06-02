package edu.uwrf.csis337.lab3.f;

/**
 * 
 * @author tony
 * Background is from http://badonk.deviantart.com/
 * Sprites are from http://untamed.wild-refuge.net/
 */

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;


public class Player {
	private int x, y;
	private int pwidth, pheight;
	private int delta_x = 0;
	private int delta_y = 0;
	private Image allSprites;
	private Image leftSprite, centerSprite, rightSprite;
	private static final String DIR = "file:src/edu/uwrf/csis337/lab3/f/";
	private static final String pirateSpritesheet = DIR + "pirateSprites.png";

	
	public Player(){
		x = 100;
		y = 100;
		allSprites = new Image( pirateSpritesheet );
		int totalWidth  = (int) allSprites.getWidth();
		int totalHeight = (int) allSprites.getHeight();
		pwidth = totalWidth/4;
		pheight = totalHeight/4;
		
		centerSprite = getSubimage(allSprites, 0,         0, pwidth, pheight);
		leftSprite   = getSubimage(allSprites, 0,   pheight, pwidth, pheight);
		rightSprite  = getSubimage(allSprites, 0, 2*pheight, pwidth, pheight);
	}
	
	
	/*
	 * Extract sub-images from sprite sheet
	 */	
	private Image getSubimage(Image source, int sx, int sy, int w, int h) {
		PixelReader pixread = source.getPixelReader();
		WritableImage result = new WritableImage( pixread, sx, sy, w, h );
		return result;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getDelta_x() {
		return delta_x;
	}
	public int getDelta_y() {
		return delta_y;
	}

	public void setDelta_x(int delta_x) {
		this.delta_x = delta_x;
	}
	public void setDelta_y(int delta_y) {
		this.delta_y = delta_y;
	}
	
	public void move(){
		x += delta_x;
		y += delta_y;
	}

	public Image getImage() {
		if (delta_x < 0)
			return leftSprite;
		if (delta_x > 0)
			return rightSprite;
		return centerSprite;
	}
}