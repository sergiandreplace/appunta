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

package com.sergiandreplace.appunta.point.renderer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.sergiandreplace.appunta.point.Point;

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
	private int xOff;
	private int yOff;
	private Paint pText;
	private Paint pBlackLine;
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
	public void drawPoint(Point point, Canvas canvas, float azimuth) {
		if (b==null) {
			
			//Initialize drawing objects
			
			b=BitmapFactory.decodeResource(res, id);
			xOff = b.getWidth()/2;
			yOff = b.getHeight()/2;
		

			pText = new Paint(Paint.ANTI_ALIAS_FLAG);
			pText.setStyle(Paint.Style.FILL_AND_STROKE);
			pText.setTextAlign(Paint.Align.LEFT);
			pText.setTextSize(20);
			pText.setTypeface(Typeface.SANS_SERIF);
			pText.setColor(Color.WHITE);
			
			pBlackLine=new Paint(Paint.ANTI_ALIAS_FLAG);
			pBlackLine.setColor(Color.BLACK);
			pBlackLine.setTextSize(20);
			pBlackLine.setTypeface(Typeface.SANS_SERIF);
			pBlackLine.setTextAlign(Paint.Align.LEFT);
				
		}
		canvas.drawLine(point.getX(), point.getY(), point.getX(), canvas.getHeight(), pText);
		canvas.drawLine(point.getX()+1, point.getY(), point.getX()+1, canvas.getHeight(), pText);
		canvas.drawLine(point.getX()-1, point.getY(), point.getX()-1, canvas.getHeight(), pText);
		canvas.drawLine(point.getX()+2, point.getY(), point.getX()+2, canvas.getHeight(), pBlackLine);
		canvas.drawLine(point.getX()-2, point.getY(), point.getX()-2, canvas.getHeight(), pBlackLine);
		canvas.drawLine(point.getX()+3, point.getY(), point.getX()+3, canvas.getHeight(), pBlackLine);
		canvas.drawLine(point.getX()-3, point.getY(), point.getX()-3, canvas.getHeight(), pBlackLine);
		
		canvas.drawBitmap(b, point.getX()-xOff, point.getY()- yOff, null);
		canvas.drawText(point.getName(), point.getX()+2 + xOff,point.getY()+10, pBlackLine);
		canvas.drawText(point.getName(), point.getX() + xOff,point.getY()+8, pText);
		
	}

}
