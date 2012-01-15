package com.sergiandreplace.appunta.pointdrawer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sergiandreplace.appunta.Point;

public class DrawablePointRenderer implements PointRenderer {
	private Bitmap b=null;
	private Resources res;
	private int id;
	public DrawablePointRenderer(Resources res, int id) {
		this.id=id;
		this.res=res;
	}
	@Override
	public Bitmap getPoint(Point point, float azimuth) {
		if (b==null) {
			b=BitmapFactory.decodeResource(res, id);
		}
		return b;
	}

}
