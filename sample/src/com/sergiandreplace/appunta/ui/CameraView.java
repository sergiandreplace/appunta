package com.sergiandreplace.appunta.ui;

import java.lang.reflect.Method;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements Callback {

	Camera camera;
	SurfaceHolder previewHolder;

	public CameraView(Context ctx) {
		super(ctx);

		init();
	}

	private void init() {
		previewHolder = this.getHolder();
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		previewHolder.addCallback(this);
	}

	public CameraView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CameraView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		camera = Camera.open();

		try {
			camera.setPreviewDisplay(previewHolder);
		} catch (Exception e) {
           }
	}

	// http://stackoverflow.com/a/6991886/462615
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Parameters params = camera.getParameters();

		params.setPictureFormat(PixelFormat.JPEG);

		params.set("orientation", "landscape");

		camera.setParameters(params);
		camera.startPreview();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		camera.stopPreview();
		camera.release();
	}

	protected void setDisplayOrientation(Camera camera, int angle) {
		Method downPolymorphic;
		try {
			downPolymorphic = camera.getClass().getMethod(
					"setDisplayOrientation", new Class[] { int.class });
			if (downPolymorphic != null)
				downPolymorphic.invoke(camera, new Object[] { angle });
		} catch (Exception e1) {
		}
	}
	
	
}
