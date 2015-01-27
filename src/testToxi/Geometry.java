package testToxi;

import processing.core.PApplet;
import toxi.geom.*;
import toxi.geom.mesh.*;
import toxi.math.waves.*;
import toxi.processing.*;

public class Geometry extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ToxiclibsSupport gfx;

	AbstractWave sphereRes=new SineWave(0,0.02f,15,18);

	public void setup() {
	  size(680,382,OPENGL);
	  gfx=new ToxiclibsSupport(this);
	}

	public void draw() {
	  AABB cube;
	  AxisAlignedCylinder cyl;
	  Cone cone,cone2;
	  Sphere ball;
	  TriangleMesh mesh;

	  int res=(int)sphereRes.update();
	  
	  background(0);
	  lights();
	  translate(width/2,height/2,0);
	  rotateX((float) (mouseY*0.01));
	  rotateY((float) (mouseX*0.01));
	  noStroke();

	  cone=new Cone(new Vec3D(0,-50,0), new Vec3D(0,1,0), 50, 100, 50);
	  //gfx.cone(cone,20,false);
	  cone2=new Cone(new Vec3D(0,50,0), new Vec3D(0,-1,0), 50, 100, 50);
	  //gfx.cone(cone2,20,true);

	  cyl=new XAxisCylinder(new Vec3D(200,0,0),20,100);
	  //gfx.cylinder(cyl,res,false);

	  SurfaceFunction f=new SuperEllipsoid(0.3f,0.3f);
	  mesh=(TriangleMesh)new SurfaceMeshBuilder(f).createMesh(null,40,50);
	  mesh.computeVertexNormals();
	  mesh.transform(new Matrix4x4().translate(0,0,200));
	  gfx.mesh(mesh,true,10);
	  //gfx.
	  
	  cube=new AABB(new Vec3D(0,0,-200),new Vec3D(50,50,50));
	  //gfx.box(cube);

	  ball=new Sphere(new Vec3D(-200,0,0),50);
/*	  gfx.sphere(ball,res);
	  
	  stroke(255,255,0);
	  Ray3D ray=new Ray3D(new Vec3D(),Vec3D.Y_AXIS);
	  gfx.ray(ray,200);
	  
	  stroke(0,255,0);
	  gfx.line(new Vec3D(),cube);
*/	  
	  stroke(255,0,255);
	  Line3D line=new Line3D(new Vec3D(),ball);
	  //gfx.points3D(line.splitIntoSegments(null,10,true));
	  
/*	  stroke(0,255,255);
	  Spline3D spline=new Spline3D();
	  spline.add(cube).add(ball).add(cone).add(cyl.getPosition()).add(mesh.computeCentroid());
	  gfx.lineStrip3D(spline.computeVertices(16));
*/	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
