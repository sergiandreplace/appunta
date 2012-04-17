package com.sergiandreplace.appunta.math3d;

import android.location.Location;
import android.view.Surface;

import com.sergiandreplace.appunta.orientation.Orientation;

public class Math3dUtil {

	private static double QUADRANT = Math.PI / 2;
	private static Vector2 viewPortPos=new Vector2();

	public static void getCamRotation(Orientation inOrientation, 
									  int inPhoneRotation, 
									  Vector3 outCamRot, 
									  Trig3 outCamTrig, 
									  Vector1 outScreenRot, 
									  Trig1 outScreenRotTrig) {
		if (inPhoneRotation == Surface.ROTATION_0) {
			outCamRot.x = inOrientation.getX();
			outCamRot.y = inOrientation.getY();
			outCamRot.z = 0;
			outScreenRot.v = -inOrientation.getZ();

		}
		if (inPhoneRotation == Surface.ROTATION_180) {
			outCamRot.x = inOrientation.getX();
			outCamRot.y = inOrientation.getY();
			outCamRot.z = 0;
			outScreenRot.v = -inOrientation.getZ();
		}

		if (inPhoneRotation == Surface.ROTATION_90) {
			outCamRot.x = -inOrientation.getX(); // Compass
			outCamRot.y = -inOrientation.getY();
			outCamRot.z = Math.PI;
			outScreenRot.v = -inOrientation.getZ() + QUADRANT;
		}
		if (inPhoneRotation == Surface.ROTATION_270) {
			outCamRot.x = -inOrientation.getX(); // Compass
			outCamRot.y = -inOrientation.getY();
			outCamRot.z = Math.PI;
			outScreenRot.v = -inOrientation.getZ() + QUADRANT;
		}
		outCamTrig.setVector3(outCamRot);
		outScreenRotTrig.setVector1(outScreenRot);

	}

	public static void getPointPos(Location inLocation, 
								   int inPhoneRotation, 
								   Vector3 outCamPos) {
		outCamPos.z = inLocation.getLatitude();
		outCamPos.x = inLocation.getLongitude();
		if (inPhoneRotation == Surface.ROTATION_90 || inPhoneRotation == Surface.ROTATION_270) {
			outCamPos.y = -inLocation.getAltitude();
		} else {
			outCamPos.y = inLocation.getAltitude();
		}
	}

	/***
	 * Check this article before trying to only understand a simple comma   
	 * http://en.wikipedia.org/wiki/3D_projection#Perspective_projection    
	 * In fact, I don't care too much about the formula. Just C&P it.       
	 * @param inPointPos
	 * @param inCamPos
	 * @param inCamTrig
	 * @param outRelativePos
	 */
	public static void getRelativeCoordinates(Vector3 inPointPos, 
											  Vector3 inCamPos, 
											  Trig3 inCamTrig, 
											  Vector3 outRelativePos) {
        // Check this article before trying to only understand a simple comma
        // http://en.wikipedia.org/wiki/3D_projection#Perspective_projection
        // In fact, I don't care too much about the formula. Just C&P it.
        outRelativePos.x = inCamTrig.yCos * (inCamTrig.zSin * (inPointPos.y - inCamPos.y) + inCamTrig.zCos * (inPointPos.x - inCamPos.x)) - inCamTrig.ySin * (inPointPos.z - inCamPos.z);
        outRelativePos.y = inCamTrig.xSin * (inCamTrig.yCos * (inPointPos.z - inCamPos.z) + inCamTrig.ySin * (inCamTrig.zSin * (inPointPos.y - inCamPos.y) + inCamTrig.zCos * (inPointPos.x - inCamPos.x))) + inCamTrig.xCos * (inCamTrig.zCos * (inPointPos.y - inPointPos.y) - inCamTrig.zSin * (inPointPos.x - inPointPos.x));
        outRelativePos.z = inCamTrig.xCos * (inCamTrig.yCos * (inPointPos.z - inCamPos.z) + inCamTrig.ySin * (inCamTrig.zSin * (inPointPos.y - inCamPos.y) + inCamTrig.zCos * (inPointPos.x - inCamPos.x))) - inCamTrig.xSin * (inCamTrig.zCos * (inPointPos.y - inPointPos.y) - inCamTrig.zSin * (inPointPos.x - inPointPos.x));
		
	}

	public static void get2dCoordinates(Vector3 inRelativePos,
										Vector2 inScreenSize, 
										Vector3 inScreenRatio, 
										int inPhoneRotation, 
										Trig1 inScreenRotTrig, 
										Vector2 outScreenPos) {
		if (inRelativePos.z > 0) {
		    if (inPhoneRotation == Surface.ROTATION_0)  {
		    	viewPortPos.x = (inRelativePos.x * inScreenRatio.x) / (inScreenRatio.z * inRelativePos.z);
		    	viewPortPos.y = - (inRelativePos.y * inScreenRatio.y) / (inScreenRatio.z * inRelativePos.z);
		    }
		    if (inPhoneRotation == Surface.ROTATION_180)  {
		
		    	viewPortPos.x = - (inRelativePos.x * inScreenRatio.x) / (inScreenRatio.z * inRelativePos.z);
		    	viewPortPos.y =   (inRelativePos.y * inScreenRatio.y) / (inScreenRatio.z * inRelativePos.z);
		    }
		    
		    if (inPhoneRotation == Surface.ROTATION_90)  {
		
		    	viewPortPos.x =  (inRelativePos.x * inScreenRatio.x) / (inScreenRatio.z * inRelativePos.z);
		    	viewPortPos.y =  -(inRelativePos.y * inScreenRatio.y) / (inScreenRatio.z * inRelativePos.z);
		    }
		    if (inPhoneRotation == Surface.ROTATION_270)  {
		
		    	viewPortPos.x = -(inRelativePos.x * inScreenRatio.x) / (inScreenRatio.z* inRelativePos.z);
		    	viewPortPos.y = (inRelativePos.y * inScreenRatio.y) / (inScreenRatio.z * inRelativePos.z);
		    }
		    
		    outScreenPos.x=inScreenSize.x / 2 +  viewPortPos.x * inScreenRotTrig.cos - viewPortPos.y * inScreenRotTrig.sin;
		    outScreenPos.y=inScreenSize.y /2 +  viewPortPos.x * inScreenRotTrig.sin + viewPortPos.y * inScreenRotTrig.cos;
		        
		} else {
			outScreenPos.x=-15000;
			outScreenPos.y=-15000;
		}		
	}



}
