package com.sergiandreplace.appunta.math3d;

public class Trig1 {
	public double sin;
	public double cos;

	
	public Trig1(){
		
	}
	public Trig1(Vector1 point){
		setVector1(point);
	}
	
	
	
	public void setVector1(Vector1 point) {
		sin=Math.sin(point.v);
		cos=Math.cos(point.v);
	}
}
