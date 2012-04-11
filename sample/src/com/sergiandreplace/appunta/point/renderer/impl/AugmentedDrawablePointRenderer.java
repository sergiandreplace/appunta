/*
   Copyright Sergi Martínez (@sergiandreplace)

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

package com.sergiandreplace.appunta.point.renderer.impl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.sergiandreplace.appunta.orientation.Orientation;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;

/***
 * This class is used to generate a PointRenderer using a drawable
 * resource
 * @author Sergi Martínez
 *
 */
public class AugmentedDrawablePointRenderer implements PointRenderer {
	private Bitmap b=null;
	private Resources res;
	private int id;
	private Paint pText;
	private Paint pBlackLine;
	
	private Paint pCircle;
	/***
	 * Creates and object able to draw a drawable resource in a Canvas
	 * @param res A resources object in order to retrieve the drawable
	 * @param id Id of the drawable
	 */
	public AugmentedDrawablePointRenderer(Resources res, int id) {
		this.id=id;
		this.res=res;	
	}
	/***
	 * This methods paints the drawable received in constructor and writes the point name beside it
	 */
	@Override
	public void drawPoint(Point point, Canvas canvas, Orientation orientation) {
		if (b==null) {
			
			//Initialize drawing objects
			
			b=BitmapFactory.decodeResource(res, id);
		

			pText = new Paint(Paint.ANTI_ALIAS_FLAG);
			pText.setStyle(Paint.Style.STROKE);
			pText.setTextAlign(Paint.Align.CENTER);
			pText.setTextSize(20);
			pText.setTypeface(Typeface.SANS_SERIF);
			pText.setColor(Color.WHITE);
			
			pCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
			pCircle.setStyle(Paint.Style.STROKE);
			pCircle.setStrokeWidth(4);
			pCircle.setColor(Color.WHITE);
			
			pBlackLine=new Paint(Paint.ANTI_ALIAS_FLAG);
			pBlackLine.setColor(Color.BLACK);
			pBlackLine.setTextSize(20);
			pBlackLine.setTypeface(Typeface.SANS_SERIF);
			pBlackLine.setTextAlign(Paint.Align.CENTER);
		
		}
	
		float size=(float) (10+(1000-point.getDistance())/25);
		canvas.drawCircle(point.getX(), point.getY(), (float) size, pCircle);
		float textWidth=pText.breakText(point.getName(), true, 500, null)/2;
		canvas.drawText(point.getName(), point.getX()-textWidth+2,point.getY()+size+16, pBlackLine);
		canvas.drawText(point.getName(), point.getX()-textWidth ,point.getY()+size+14, pText);
		
	}

}
