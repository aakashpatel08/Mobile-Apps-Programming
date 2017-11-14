package edu.uco.apatel19.tpaakashp.Threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import edu.uco.apatel19.tpaakashp.Objects.Game;

public class GameLoopThread extends Thread{
    private final static int MAX_FPS = 30; // Number of times per second the game should be updated, drawn?
    private final static int MAX_FRAME_SKIPS = 5; // Maximum number of frames to be skipped
    private final static int FRAME_PERIOD = 1000 / MAX_FPS; // The frame period
    public static boolean isPaused;

    // Surface holder that can access the physical surface.
    private SurfaceHolder surfaceHolder;

    private Game game;

    // Elapsed game time in milliseconds.
    private long gameTime;

    // Holds the state of the game loop.
    public boolean running;

    public GameLoopThread(SurfaceHolder surfaceHolder, Game game) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.game = game;

        this.gameTime = 0;
    }

    public static void setPause(int i) {
        if (i == 0) {
            isPaused = false;
        }
        if (i == 1) {
            isPaused = true;
        }
    }

    @Override
    public void run() {
        Canvas canvas;

        long beginTime; //Time when the cycle begun
        long timeDiff; //Time it take for the cycle to execute
        int sleepTime; //ms to sleep (<0 if we're behind)
        int framesSkipped; //Number of frames being skipped

        sleepTime = 0;

        while (running) {

            if (isPaused) {
                try {
                    this.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                canvas = null;

                try {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        beginTime = System.currentTimeMillis();
                        framesSkipped = 0; //Resetting the frames skipped.

                        this.game.Update(this.gameTime);
                        this.game.Draw(canvas);

                        //Calculate how long did the cycle take.
                        timeDiff = System.currentTimeMillis() - beginTime;
                        //Calculate sleep time.
                        sleepTime = (int) (FRAME_PERIOD - timeDiff);

                        if (sleepTime > 0) {
                            try {
                                //Send the thread to sleep
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                            this.game.Update(this.gameTime);
                            sleepTime += FRAME_PERIOD;
                            framesSkipped++;
                        }

                        this.gameTime += System.currentTimeMillis() - beginTime;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
