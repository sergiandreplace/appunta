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

package com.appunta.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.appunta.android.math3d.Math3dUtil;
import com.appunta.android.math3d.Trig1;
import com.appunta.android.math3d.Trig3;
import com.appunta.android.math3d.Vector1;
import com.appunta.android.math3d.Vector2;
import com.appunta.android.math3d.Vector3;
import com.appunta.android.point.Point;

public class EyeView extends AppuntaView {

	private static final int SCREEN_DEPTH = 1;

	
	
	private Vector3 camRot=new Vector3();
	private Trig3   camTrig=new Trig3();
	private Vector3 camPos=new Vector3();
	private Vector3 pointPos=new Vector3();
	private Vector3 relativePos=new Vector3();
	private Vector3 screenRatio=new Vector3();
	
	private Vector2 screenPos=new Vector2();
	private Vector2 screenSize=new Vector2();

	private Vector1 screenRot=new Vector1();
	private Trig1 	screenRotTrig=new Trig1();
	


	
	public EyeView(Context context) {
		super(context);
		
		init();
	}

	public EyeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	EyeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		screenRatio.z=SCREEN_DEPTH;



	}

	@Override
    protected void preRender(Canvas canvas) {
		
			Math3dUtil.getCamRotation(getOrientation(), getPhoneRotation(), camRot, camTrig, screenRot, screenRotTrig);
            Math3dUtil.getPointPos(getLocation(), getPhoneRotation(), camPos);
            screenRatio.y=(getWidth()+getHeight())/2;
            screenRatio.x=(getWidth()+getHeight())/2;
            screenSize.y=getHeight();
            screenSize.x=getWidth();
            
	       
    }

	@Override
	protected void calculatePointCoordinates(Point point) {
		Math3dUtil.getPointPos(point.getLocation(), getPhoneRotation(),pointPos);
        Math3dUtil.getRelativeCoordinates(pointPos, camPos, camTrig, relativePos);
        Math3dUtil.get2dCoordinates(relativePos, screenSize, screenRatio, getPhoneRotation(), screenRotTrig, screenPos);
        point.setX((float) screenPos.x);
	    point.setY((float) screenPos.y);
	    
}

	@Override
	protected void postRender(Canvas canvas) {
		// TODO Auto-generated method stub

	}



}