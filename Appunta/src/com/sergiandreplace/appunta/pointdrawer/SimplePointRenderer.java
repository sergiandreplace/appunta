package com.sergiandreplace.appunta.pointdrawer;

import android.R.color;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.sergiandreplace.appunta.Point;

public class SimplePointRenderer implements PointRenderer {

	private static Bitmap b = null;

	@Override
	public Bitmap getPoint(Point point, float azimuth) {
		return getPoint();
	}
	
	public Bitmap getPoint() {
		if (b == null) {
			Paint p=new Paint();
			p.setColor(color.white);
			b = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
			Canvas c=new Canvas(b);
			c.drawCircle(5, 5, 10, p);
			
		}
		return b;
	}
	
	

}
