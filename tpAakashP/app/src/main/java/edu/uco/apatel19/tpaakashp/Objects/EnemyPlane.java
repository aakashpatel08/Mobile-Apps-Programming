package edu.uco.apatel19.tpaakashp.Objects;

import android.graphics.Canvas;
import android.graphics.Rect;

public class EnemyPlane {
    // This are starting data.
    public static final float initSpeed = 4;
    public static final long initTimeBetweenPlanes = 4000; // in milliseconds

    // This is current speed that will be increased and current time that will be decreased.
    public static float speed = 5;
    public static long timeBetweenPlanes = 4000; // in milliseconds

    public static long timeOfLastPlane;

    // Planes will be coming from left and right, we will use this to know which direction is next.
    public static boolean direction = true;

    // Needed for speeding up the game
    public static long timeBetweenSpeedups = 500; // in milliseconds original
    public static long timeOfLastSpeedup;

    // Plane position on the screen.
    public float x;
    public float y;

    public int width;

    public int height;

    // Speed and direction.
    private float velocity;

    public EnemyPlane(int y) {
        this.y = y;

        this.width = 70;
        this.height = 40;

        if (EnemyPlane.direction) {
            this.x = Game.screenWidth;
            velocity = speed * -2;
        } else {
            this.x = 0 - Game.enemy.getWidth();
            velocity = speed;
        }
    }

    /**
     * Move the Plane.
     */
    public void update() {
        this.x += velocity;
    }

    /**
     * Draw the Plane to a screen.
     *
     * @param canvas
     *            Canvas to draw on.
     */
    public void draw(Canvas canvas) {
        if (velocity < 0)
            canvas.drawBitmap(Game.enemy, x, y, null);
        else
            canvas.drawBitmap(Game.enemy, x, y, null);
    }
}
