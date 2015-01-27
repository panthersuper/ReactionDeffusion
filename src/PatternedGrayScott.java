import processing.core.PApplet;
import processing.core.PImage;
import toxi.sim.grayscott.GrayScott;

class PatternedGrayScott extends GrayScott {
	PImage img;
	PApplet app;
	public Object gs;

	
	// our constructor just passes things on to the parent class
	PatternedGrayScott(int w, int h, boolean tiling,PImage p,PApplet app) {
		super(w, h, tiling);
		this.img = p;
		this.app = app;
	}

	// this function is called for each cell
	// to retrieve its f coefficient
	public float getFCoeffAt(int x, int y) {
		// here we only take the x coordinate
		// and choose one of 2 options (even & odd)
		float gradiant = app.brightness(this.img.get(x, y));
		//app.println(gradiant);

		if (gradiant<100){
			return (float) (f+0.009f);
		}
		//else if(gradiant<50){
		//	return (float) (f - 0.004);
		//}
		else{
			return f;
		}
		
		
	}

	// this function is called for each cell
	// to retrieve its K coefficient
	public float getKCoeffAt(int x, int y) {
		// here we only use the y coordinate
		// and create a gradient falloff for this param
		float gradiant = app.brightness(this.img.get(x, y));
		if (gradiant<100){
			return (float) (k + 0.02f);
		}
		//else if(gradiant<50){
		//	return (float) (f - 0.004);
		//}
		else{
			return k;
		}

	}

}