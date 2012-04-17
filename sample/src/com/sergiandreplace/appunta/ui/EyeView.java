/*
   Copyright  Sergi Martínez (@sergiandreplace)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.sergiandreplace.appunta.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Surface;

import com.sergiandreplace.appunta.point.Point;

public class EyeView extends AppuntaView {

	private static double QUADRANT = Math.PI/2;
	
	public double camZrot;
	public double camYrot;
	public double camXrot;
	private double cosCamYrot;
	private double sinCamYrot;
	private double sinCamXrot;
	private double cosCamXrot;
	private double sinCamZrot;
	private double cosCamZrot;
	private double cz;
	private double cy;
	private double cx;
	private double az;
	private double ay;
	private double ax;
	private double dx;
	private double dy;
	private double dz;
	private double by;
	private double bx;
	private double ez=1;
	private double ex=300;
	private double ey=300;

	private double sx;

	private double sy;

	private double screenZrot;

	private double sinScreenZrot;

	private double cosScreenZrot;

	
	public EyeView(Context context) {
		super(context);
	}

	public EyeView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	EyeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
    protected void preRender(Canvas canvas) {
            if (this.getPhoneRotation()==Surface.ROTATION_0) {
                    camXrot = getOrientation().getX();
                    camYrot = getOrientation().getY();
                    camZrot = 0;
                    screenZrot=-getOrientation().getZ();
                   
            }
            if (this.getPhoneRotation()==Surface.ROTATION_180) {
                    camXrot = -getOrientation().getX();
                    camYrot = getOrientation().getY(); 
                    camZrot = 0;
                    screenZrot=-getOrientation().getZ();
            }
            
            
            if (this.getPhoneRotation()==Surface.ROTATION_90){
                    camXrot = -getOrientation().getX(); //Compass
                    camYrot = -getOrientation().getY();
                    camZrot = Math.PI ;
                    screenZrot= -getOrientation().getZ() + QUADRANT ;
            }
            if (this.getPhoneRotation()==Surface.ROTATION_270){
                    camXrot = -getOrientation().getX(); // Compass
                    camYrot = -getOrientation().getY();
                    camZrot = Math.PI ;
                    screenZrot = -getOrientation().getZ() + QUADRANT ;
            }
            
            
            sinCamXrot = Math.sin(camXrot);
            cosCamXrot = Math.cos(camXrot);

            sinCamYrot = Math.sin(camYrot);
            cosCamYrot = Math.cos(camYrot);

            sinCamZrot = Math.sin(camZrot);
            cosCamZrot = Math.cos(camZrot);
            
            sinScreenZrot = Math.sin(screenZrot);
            cosScreenZrot = Math.cos(screenZrot);

            cz = getLocation().getLatitude();
            cx = getLocation().getLongitude();
            if (this.getPhoneRotation()==Surface.ROTATION_90 || this.getPhoneRotation()==Surface.ROTATION_270){
                    cy = -getLocation().getAltitude();
                    ey=(getWidth()+getHeight())/2;
                    ex=(getWidth()+getHeight())/2;
                    
            }else{
                    cy = getLocation().getAltitude();
                    ey=(getWidth()+getHeight())/2;
                    ex=(getWidth()+getHeight())/2;
                    
            }
            

    }

	@Override
	protected void calculatePointCoordinates(Point point) {
        az = point.getLocation().getLatitude();
        ax = point.getLocation().getLongitude();
        if (this.getPhoneRotation()==Surface.ROTATION_90 || this.getPhoneRotation()==Surface.ROTATION_270){
                ay = -point.getLocation().getAltitude();
        }else{
                ay = point.getLocation().getAltitude();
        }

        // Check this article before trying to only understand a simple comma
        // http://en.wikipedia.org/wiki/3D_projection#Perspective_projection
        // In fact, I don't care too much about the formula. Just C&P it.
        dx = cosCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx)) - sinCamYrot * (az - cz);
        dy = sinCamXrot * (cosCamYrot * (az - cz) + sinCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx))) + cosCamXrot * (cosCamZrot * (ay - cy) - sinCamZrot * (ax - cx));
        dz = cosCamXrot * (cosCamYrot * (az - cz) + sinCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx))) - sinCamXrot * (cosCamZrot * (ay - cy) - sinCamZrot * (ax - cx));

        
        if (dz > 0) {
                if (this.getPhoneRotation() == Surface.ROTATION_0)  {
                        bx = (dx * ex) / (ez * dz);
                        by = - (dy * ey) / (ez * dz);
                }
                if (this.getPhoneRotation() == Surface.ROTATION_180)  {

                        bx = - (dx * ex) / (ez * dz);
                        by =   (dy * ey) / (ez * dz);
                }
                
                if (this.getPhoneRotation() == Surface.ROTATION_90)  {

                        bx =  (dx * ex) / (ez * dz);
                        by =  -(dy * ey) / (ez * dz);
                }
                if (this.getPhoneRotation() == Surface.ROTATION_270)  {

                        bx = -(dx * ex) / (ez* dz);
                        by = (dy * ey) / (ez * dz);
                }
                sx=getWidth() / 2 +  bx * cosScreenZrot - by * sinScreenZrot;
                sy=getHeight() /2 +  bx * sinScreenZrot + by * cosScreenZrot;
                
	            point.setX((float) sx);
	            point.setY((float) sy);
        } else {
                point.setX(-15000);
                point.setY(-15000);
        }
}

	@Override
	protected void postRender(Canvas canvas) {
		// TODO Auto-generated method stub

	}



}