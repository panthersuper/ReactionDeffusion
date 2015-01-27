
import java.io.PrintWriter;

import processing.core.PApplet;
import processing.core.PImage;
import toxi.sim.grayscott.*;
import toxi.math.*;
import toxi.color.*;
public class testDffusionUPS extends PApplet{
	/**
	 * <p>GrayScottImage uses the seedImage() method to use a bitmap as simulation seed.
	 * In this demo the image is re-applied every frame and the user can adjust the 
	 * F coefficient of the reaction diffusion to produce different patterns emerging
	 * from the boundary of the bitmapped seed. Unlike some other GS demos provided,
	 * this one also uses a wrapped simulation space, creating tiled patterns.</p>
	 *
	 * <p><strong>usage:</strong></p>
	 * <ul>
	 * <li>click + drag mouse to locally disturb the simulation</li>
	 * <li>press 1-9 to adjust the F parameter of the simulation</li> 
	 * <li>press any other key to reset</li>
	 * </ul>
	 */

	/* 
	 * Copyright (c) 2010 Karsten Schmidt
	 * 
	 * This demo & library is free software; you can redistribute it and/or
	 * modify it under the terms of the GNU Lesser General Public
	 * License as published by the Free Software Foundation; either
	 * version 2.1 of the License, or (at your option) any later version.
	 * 
	 * http://creativecommons.org/licenses/LGPL/2.1/
	 * 
	 * This library is distributed in the hope that it will be useful,
	 * but WITHOUT ANY WARRANTY; without even the implied warranty of
	 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
	 * Lesser General Public License for more details.
	 * 
	 * You should have received a copy of the GNU Lesser General Public
	 * License along with this library; if not, write to the Free Software
	 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
	 */

	PrintWriter output;
	PatternedGrayScott  gs;
	ToneMap toneMap;
	PImage img;
	int step = 0;
	
	int numw = 30;
	int numh = 30;
	
	
	public void setup() {
	  size(256,256);
	  output = createWriter("positions.txt"); 

	  img=loadImage("D:/UCB/1.Required Courses/ARCH 205A_Studio One (Studio)/Office Space/Site/UPSsite_RD_Gradient2.png");
	  
	  
	  gs=new PatternedGrayScott (width,height,true,img,this);
	  // create a duo-tone gradient map with 256 steps
	  toneMap=new ToneMap(0,(float) 0.33,NamedColor.CRIMSON,NamedColor.WHITE,256);
	  gs.seedImage(img.pixels,img.width,img.height);
	  
	  gs.setCoefficients(0.010f,0.049f,0.095f,0.03f);
	  //gs.setCoefficients(0.023f,0.074f,0.095f,0.03f);
	  //gs.setCoefficients(0.025f,0.074f,0.095f,0.03f);
	  //gs.setCoefficients(0.010f,0.08f,0.095f,0.03f);
	  //gs.setCoefficients(0.015f,0.074f,0.095f,0.03f);

	}

	public void draw() {
	//println(brightness(img.get(mouseX, mouseY)));

	  step++;
	  

	  
	  
	  
	  if(step<400){
	  if (mousePressed) {
	    gs.setRect(mouseX, mouseY,20,20);
	  }
	  loadPixels();
	  for(int i=0; i<10; i++) gs.update(1);
	  // read out the V result array
	  // and use tone map to render colours
	  for(int i=0; i<gs.v.length; i++) {
	    pixels[i]=toneMap.getARGBToneFor(gs.v[i]);
	  }
	  }
	  updatePixels();
	  
	  //image(img, 0, 0);
	}

	public void keyPressed() {
	  if (key>='1' && key<='9') {
	    gs.setF((float) (0.02+(key-'1')*0.001));
	  } 
	  
	  for(int i =0;i<numw;i++){
		  for(int j=numh;j>0;j--){
			  this.output.println("["+i+","+j+"]:"+gs.getCurrentVAt(i*width/numw, j*height/numh));
		  }
	  }
	  output.flush(); // Writes the remaining data to the file
	  output.close(); // Finishes the file
	  //exit(); // Stops the program

	  //else {
	  //  gs.reset();
	  //}
	}
}
