package com.sergiandreplace.appunta.math3d;

public class Trig3 {
	public double xSin;
	public double xCos;
	public double ySin;
	public double yCos;
	public double zSin;
	public double zCos;
	
	public Trig3(){
		
	}
	public Trig3(Vector3 point){
		setVector3(point);
	}
	
	
	
	public void setVector3(Vector3 point) {
		xSin=Math.sin(point.x);
		ySin=Math.sin(point.y);
		zSin=Math.sin(point.z);
		xCos=Math.cos(point.x);
		yCos=Math.cos(point.y);
		zCos=Math.cos(point.z);
	}
}
