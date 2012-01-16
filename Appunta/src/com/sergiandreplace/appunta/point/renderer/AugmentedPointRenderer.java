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
public class AugmentedPointRenderer implements PointRenderer {
	private Bitmap b=null;
	private Resources res;
	private int id;
	private int xOff;
	private int yOff;
	private Paint pText;
	/***
	 * Creates and object able to draw a drawable resource in a Canvas
	 * @param res A resources object in order to retrieve the drawable
	 * @param id Id of the drawable
	 */
	public AugmentedPointRenderer(Resources res, int id) {
		this.id=id;
		this.res=res;	
	}
	
	@Override
	public void drawPoint(Point point, Canvas canvas, float azimuth) {
		if (b==null) {
			b=BitmapFactory.decodeResource(res, id);
			xOff = b.getWidth()/2;
			yOff = b.getHeight()/2;
		

			pText = new Paint(Paint.ANTI_ALIAS_FLAG);
			pText.setColor(Color.RED);
			pText.setStyle(Paint.Style.FILL);
			pText.setTextAlign(Paint.Align.LEFT);
			pText.setTextSize(20);
			pText.setTypeface(Typeface.SANS_SERIF);
			
		}
		
		canvas.drawBitmap(b, point.getX()-xOff, point.getY()- yOff, null);
		//canvas.drawCircle(point.getX(), point.getY(), 10, pText);
		pText.setColor(Color.WHITE);
		pText.setStyle(Paint.Style.STROKE);
		
		canvas.drawText(point.getName() + point.getDistance(), point.getX() + xOff,point.getY()+8, pText);
		
	}

}
