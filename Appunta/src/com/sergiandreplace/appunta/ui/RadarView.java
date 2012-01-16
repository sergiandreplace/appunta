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

package com.sergiandreplace.appunta.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.sergiandreplace.appunta.point.Point;

public class RadarView extends AppuntaView {

	private int rotableBackground = 0;
	private float center;


	public RadarView(Context context) {
		super(context);
	}

	public RadarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	RadarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	
	
	/***
	 * Returns the correct size of the control when needed (Basically
	 * maintaining the ratio)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(),
				widthMeasureSpec);
		int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);

		int size = Math.max(measuredWidth, measuredHeight);
		center = getWidth()/2;
		setMeasuredDimension(size, size);
	}

	@Override
	protected void setPointCoordinates(Point point) {
		
		double pointAngle = getAngle(point) + getAzimuthRadians();
		double pixelDistance = point.getDistance() * center
				/ getMaxDistance();
		double pointy = center - pixelDistance * Math.sin(pointAngle);
		double pointx = center+ pixelDistance * Math.cos(pointAngle);
		point.setX((float) pointx);
		point.setY((float) pointy);
	}
	
	@Override
	protected void preRender(Canvas canvas) {
		drawBackground(canvas, -getAzimuth());	
	}
	
	@Override
	protected void postRender(Canvas canvas) {
		Paint pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		pointPaint.setColor(Color.GREEN);
		pointPaint.setColor(Color.RED);
		canvas.drawCircle(center, center, 10, pointPaint);
	
	}
	private void drawBackground(Canvas canvas, double azimuth) {
		if (rotableBackground != 0) {
			Bitmap bg = BitmapFactory.decodeResource(this.getResources(),
					rotableBackground);
			Matrix transform = new Matrix();
			transform.setRectToRect(
					new RectF(0, 0, bg.getWidth(), bg.getHeight()), new RectF(
							0, 0, getWidth(), getWidth()), Matrix.ScaleToFit.CENTER);
			transform.preRotate((float) azimuth, bg.getWidth() / 2,
					bg.getHeight() / 2);
			canvas.drawBitmap(bg, transform, null);
		}
	}




}