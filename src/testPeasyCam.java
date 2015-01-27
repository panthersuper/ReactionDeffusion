import igeo.IVec;

import java.io.PrintWriter;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import peasy.*;
import toxi.color.NamedColor;
import toxi.color.ToneMap;

public class testPeasyCam extends PApplet {

	PeasyCam cam;
	PatternedGrayScott gs;
	ToneMap toneMap;
	PImage img;
	int step = 0;

	int numw = 30;
	int numh = 30;
	ArrayList<IVec> matrixs = new ArrayList<>();
	PrintWriter output;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setup() {
		size(200, 200, OPENGL);
		cam = new PeasyCam(this, 100);
		cam.setMinimumDistance(5);
		cam.setMaximumDistance(5000);
		output = createWriter("positions4.txt");

		img = loadImage("C:/Users/Administrator/Pictures/bat.png");
		gs = new PatternedGrayScott(width, height, true, img, this);
		// create a duo-tone gradient map with 256 steps
		toneMap = new ToneMap(0, (float) 0.33, NamedColor.CRIMSON,
				NamedColor.WHITE, 256);
		gs.seedImage(img.pixels, img.width, img.height);
		//gs.setCoefficients(0.021f, 0.069f, 0.095f, 0.03f);
		gs.setCoefficients(0.02f,0.069f,0.095f,0.03f);
	}

	public void draw() {
		step++;

		if (step < 500) {

			loadPixels();
			for (int i = 0; i < 10; i++)
				gs.update(2);
			// read out the V result array
			// and use tone map to render colours
			for (int i = 0; i < gs.v.length; i++) {
				pixels[i] = toneMap.getARGBToneFor(gs.v[i]);

			}
			updatePixels();

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					float tone = gs.getCurrentUAt(i, j);
					//println (tone);
					if (tone < 0.3) {
						this.matrixs.add(new IVec(i, j, step));
					}

				}
			}
			println("step: " + step);
			println("size: " + this.matrixs.size());

		} /*else {
			for (int i = 0; i < this.matrixs.size(); i++) {
				this.pushMatrix();
				this.translate((float) this.matrixs.get(i).x,
						(float) this.matrixs.get(i).y);
				this.point(0, 0);
				this.popMatrix();
			}

		}*/

		// read out the V result array
		// and use tone map to render colours

	}

	public void keyPressed() {
		
		for (int i = 0; i < this.matrixs.size(); i++) {
			if(this.matrixs.get(i).x%2==0 && this.matrixs.get(i).y%2==0 && this.matrixs.get(i).z%3==0)
			this.output.println(this.matrixs.get(i).x+","+this.matrixs.get(i).y+","+this.matrixs.get(i).z/2);
			
			
			
		}
		
		
		
		output.flush(); // Writes the remaining data to the file
		output.close(); // Finishes the file
		// exit(); // Stops the program

		// else {
		// gs.reset();
		// }
	}

}
