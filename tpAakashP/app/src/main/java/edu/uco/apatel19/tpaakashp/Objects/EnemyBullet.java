package edu.uco.apatel19.tpaakashp.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class EnemyBullet {
    private int locx, locy;

    private Bitmap bmp;

    public Game gameView;

    public boolean player;

    public EnemyBullet(Game gameView, Bitmap bmp, int x, int y) {
        this.gameView = gameView;
        this.bmp = bmp;

        locx = x;
        locy = y + 50;
    }

    public double getLocx() {
        return locx;
    }

    public double getLocy() {
        return locy;
    }

    public void moveBullet() {

        locx -= 50;

    }

    public void onDraw(Canvas canvas) {
        moveBullet();
        canvas.drawBitmap(bmp, locx, locy, null);
    }
}
