package edu.uco.apatel19.tpaakashp.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class HeliBullet {
    private Bitmap bitmap;

    public int x;
    public int y;

    private int mSpeed = 25;

    public double angle;

    public int width;

    public int height;

    public Game gameView;

    public HeliBullet(Game gameView, Bitmap bitmap, int coX, int coY) {
        this.gameView = gameView;
        this.bitmap = bitmap;

        this.x = coX;
        this.y = coY;
        this.width = 27;
        this.height = 40;

        angle = Math.atan((double) (y - gameView.shootY) / (x - gameView.shootX));
    }

    private void update() {
        x += mSpeed * Math.cos(angle);
        y += mSpeed * Math.sin(angle);
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bitmap, x, y, null);
    }
}
