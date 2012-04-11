/*
   Copyright  Sergi Mart�nez (@sergiandreplace)

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
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.AttributeSet;

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
	private float pitch;
	private float roll;
	private float rollAngle;
	private double ez = 1;
	private float compass;
	private float pitchAngle;
	private float compassAngle;
	
	
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

	
		pitch = getOrientation().getPitch();
		roll = getOrientation().getRoll();
		compass = getOrientation().getCompass();

		if (getDeviceOrientation()==Configuration.ORIENTATION_PORTRAIT) { //IF ORIENTATION IS VERTICAL
			rollAngle=-roll;
			pitchAngle=pitch;
			compassAngle=compass+180;

			camXrot = Math.toRadians(pitchAngle);
			camYrot = Math.toRadians(rollAngle);
			camZrot = Math.toRadians(compassAngle);

		}else{
			if (pitch < -90 || pitch > 90) {
				rollAngle = roll;
				compassAngle = compass + 90;
			} else {
				rollAngle = -roll;
				compassAngle = compass - 90;
			}
			pitchAngle=-pitch;	
			

			camXrot = Math.toRadians(rollAngle);
			camYrot = Math.toRadians(pitchAngle);
			camZrot = Math.toRadians(compassAngle);
		}
			
			
			
		sinCamXrot = Math.sin(camXrot);
		cosCamXrot = Math.cos(camXrot);

		sinCamYrot = Math.sin(camYrot);
		cosCamYrot = Math.cos(camYrot);

		sinCamZrot = Math.sin(camZrot);
		cosCamZrot = Math.cos(camZrot);

		cx = getLocation().getLatitude();
		cy = getLocation().getLongitude();
		cz = getLocation().getAltitude();

	}

	@Override
	protected void calculatePointCoordinates(Point point) {
		ax = point.getLocation().getLatitude();
		ay = point.getLocation().getLongitude();
		az = point.getLocation().getAltitude();

		// Check this article before trying to only understand a simple comma
		// http://en.wikipedia.org/wiki/3D_projection#Perspective_projection
		// In fact, I don't care too much about the formula. Just C&P it.
		dx = cosCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx)) - sinCamYrot * (az - cz);
		dy = sinCamXrot * (cosCamYrot * (az - cz) + sinCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx))) + cosCamXrot * (cosCamZrot * (ay - cy) - sinCamZrot * (ax - cx));
		dz = cosCamXrot * (cosCamYrot * (az - cz) + sinCamYrot * (sinCamZrot * (ay - cy) + cosCamZrot * (ax - cx))) - sinCamXrot * (cosCamZrot * (ay - cy) - sinCamZrot * (ax - cx));

		// Log.v("appunta", "DX: "+dx+"\tDY: " +dy+"\tDX: "+dz);
		if (dz > 0) {

			bx = getWidth() / 2 - (dx * ez / dz) * getWidth();
			by = getHeight() / 2 - (dy * ez / dz) * getHeight();
			point.setX((float) bx);
			point.setY((float) by);
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