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
import android.util.Log;
import android.view.Surface;

import com.sergiandreplace.appunta.orientation.OrientationManager;
import com.sergiandreplace.appunta.point.Point;

public class EyeView extends AppuntaView {

	private double camZrot;
	private double camYrot;
	private double camXrot;
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
	private double ez =-1.5;
	
	
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
			camZrot = -getOrientation().getZ();
		}
		if (this.getPhoneRotation()==Surface.ROTATION_180) {
			camXrot = -getOrientation().getX();
			camYrot = getOrientation().getY();
			camZrot = getOrientation().getZ();
			
		}
		
		
		if (this.getPhoneRotation()==Surface.ROTATION_90){
			camXrot = -getOrientation().getY();
			camYrot = getOrientation().getZ();
			camZrot = getOrientation().getX();
		}
		if (this.getPhoneRotation()==Surface.ROTATION_270){
			camXrot = getOrientation().getY();
			camYrot = getOrientation().getZ();
			camZrot = getOrientation().getX();
		}
		
		
		sinCamXrot = Math.sin(camXrot);
		cosCamXrot = Math.cos(camXrot);

		sinCamYrot = Math.sin(camYrot);
		cosCamYrot = Math.cos(camYrot);

		sinCamZrot = Math.sin(camZrot);
		cosCamZrot = Math.cos(camZrot);

		cz = getLocation().getLatitude();
		cx = getLocation().getLongitude();
		cy = 0;//getLocation().getAltitude();

	}

	@Override
	protected void calculatePointCoordinates(Point point) {
		az = point.getLocation().getLatitude();
		if (this.getPhoneRotation()==Surface.ROTATION_90) {
			
		}
		ax = point.getLocation().getLongitude();
		ay = 0;//point.getLocation().getAltitude();

		// Check this article before trying to only understand a simple comma
		// http://en.wikipedia.org/wiki/3D_projection#Perspective_projection
		// In fact, I don't care too much about the formula. Just C&P it.
		dx = cosCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx)) - sinCamYrot * (az - cz);
		dy = sinCamXrot * (cosCamYrot * (az - cz) + sinCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx))) + cosCamXrot * (cosCamZrot * (ay - cy) - sinCamZrot * (ax - cx));
		dz = cosCamXrot * (cosCamYrot * (az - cz) + sinCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx))) - sinCamXrot * (cosCamZrot * (ay - cy) - sinCamZrot * (ax - cx));
	
		Log.v("appunta[" + point.getName() + "]",
				String.format("DX: %.3f - DY: %.3f - DZ: %.3f", dx, dy, dz));
		if (dz > 0) {
			if (this.getPhoneRotation() == Surface.ROTATION_0)  {

				bx = getWidth() / 2 + (dx * getWidth()) / (1 * dz);
				by = getHeight() / 2 - (dy * getHeight()) / (1 * dz);
			}
			if (this.getPhoneRotation() == Surface.ROTATION_180)  {

				bx = getWidth() / 2 - (dx * getWidth()) / (1 * dz);
				by = getHeight() / 2 - (dy * getHeight()) / (1 * dz);
			}
			
			if (this.getPhoneRotation() == Surface.ROTATION_90)  {

				bx = getWidth() / 2 + (dx * getWidth()) / (1 * dz);
				by = getHeight() / 2 + (dy * getHeight()) / (1 * dz);
			}
			if (this.getPhoneRotation() == Surface.ROTATION_270)  {

				bx = getWidth() / 2 - (dx * getWidth()) / (1 * dz);
				by = getHeight() / 2 + (dy * getHeight()) / (1 * dz);
			}
			
			point.setY((float) by);
			point.setX((float) bx);
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