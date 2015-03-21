# `CompassView` - Usage

# How to use `CompassView` #

In order to use the `CompassView` you can add it via layout editor as any other view. Ex:
```
    <com.sergiandreplace.appunta.ui.RadarView
        android:id="@+id/radarView1"
        android:layout_width="match_parent"
        android:layout_height="0dp"/>
```
Once added we need to provide it with several things programatically:

a) We need to add points using the `setPoints` method. You can use any implementation of the List interface. Check the implementation of Point.

b) Set a latitude and a longitude to use as center of the radar. You can update it when receiving new updates from `LocationProvider` (TODO: LINK)

c) Set orientation (azimuth). You can get the current one from the `CompassManager` class.

These are the three main things to provide in order to make it work.

## Optional parameters ##

d) Stablish de maximum distance to show with `MaxDistance`

e) Give a rotable background with the `SetRotableBackground` method. This will be drawn over the normal background and will rotate as the orientation changes.

f) Add new point renderers with the `putRenderer` method. If a point has no renderer assigned or the renderer has not been provided to `CompassView`, it will be drawn with the `SimplePointRenderer` by default (white point).

g) Set a listener for when a point is clicked with the `setOnPointPressedListener` method