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

import android.graphics.Canvas;

import com.sergiandreplace.appunta.point.Point;

/***
 * This interface will be used to represent all the classes able to draw a point
 * @author sergi
 *
 */
public interface PointRenderer {
	/***
	 * This method is called when the point needs be drawn. It gives the sufficient
	 * information to perform all kind of drawing.
	 * @param point The point being drawn
	 * @param canvas The canvas where to draw
	 * @param azimuth The current azimuth
	 */
	public void drawPoint(Point point, Canvas canvas, float azimuth);
}
