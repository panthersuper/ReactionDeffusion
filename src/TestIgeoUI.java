import processing.core.PApplet;
import processing.core.PImage;
import igeo.*;
import toxi.color.*;

public class TestIgeoUI extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PatternedGrayScott gs;
	ToneMap toneMap;
	PImage img;
	int step = 0;

	int numw = 30;
	int numh = 30;

	public void setup() {
		size(800, 400, IG.GL);

		img = loadImage("C:/Users/Administrator/Pictures/xueba.png");

		gs = new PatternedGrayScott(width, height, true, img, this);
		// create a duo-tone gradient map with 256 steps
		toneMap = new ToneMap(0, (float) 0.33, NamedColor.CRIMSON,
				NamedColor.WHITE, 256);
		gs.seedImage(img.pixels, img.width, img.height);

	}

	// showing original location

	public void draw() {
		// showing original location
		step++;
		
		if (step < 400) {
			/*if (mousePressed) {
				gs.setRect(mouseX, mouseY, 20, 20);
			}*/
			loadPixels();
			for (int i = 0; i < 10; i++)
				gs.update(1);
			// read out the V result array
			// and use tone map to render colours
			for (int i = 0; i < gs.v.length; i++) {
				pixels[i] = toneMap.getARGBToneFor(gs.v[i]);
			}
		}
		updatePixels();
	}

}
