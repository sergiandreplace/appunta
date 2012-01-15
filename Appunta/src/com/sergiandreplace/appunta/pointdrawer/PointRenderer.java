package com.sergiandreplace.appunta.pointdrawer;

import android.graphics.Bitmap;

import com.sergiandreplace.appunta.Point;

public interface PointRenderer {
	public Bitmap getPoint(Point point, float azimuth);
}
