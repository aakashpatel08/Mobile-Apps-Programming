package edu.uco.apatel19.tpaakashp.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Helicopter {
    private Bitmap bitmap; // the actual bitmap
    private int x; // the X coordinate
    private int y; // the Y coordinate
    private boolean touched; // if helicopter is touched/picked up
    public static int health = 100;

    public Helicopter(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;

        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, 400, 400, true);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2),
                y - (bitmap.getHeight() / 2), null);
    }

    /**
     * Handles the {@link MotionEvent.ACTION_DOWN} event. If the event happens
     * on the bitmap surface then the touched state is set to <code>true</code>
     * otherwise to <code>false</code>
     *
     *  eventX
     *            - the event's X coordinate
     *  eventY
     *            - the event's Y coordinate
     */
    public void handleActionDown(int eventX, int eventY) {
        if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth() / 2))) {
            if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
                // helicopter touched
                setTouched(true);
            } else {
                setTouched(false);
            }
        } else {
            setTouched(false);
        }

    }
}
