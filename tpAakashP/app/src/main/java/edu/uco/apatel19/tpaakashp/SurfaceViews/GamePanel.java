package edu.uco.apatel19.tpaakashp.SurfaceViews;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.uco.apatel19.tpaakashp.Objects.Game;
import edu.uco.apatel19.tpaakashp.Threads.GameLoopThread;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Game game;
    private GameLoopThread gameLoopThread;
    public static Context context;

    public GamePanel(Context context) {
        super(context);

        // Focus must be on GamePanel so that events can be handled.
        this.setFocusable(true);

        // For intercepting events on the surface.
        this.getHolder().addCallback(this);

        this.context = context;

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // Start the game loop
        startGame();
    }

    private void startGame() {
        game = new Game(getWidth(), getHeight(), getResources());

        gameLoopThread = new GameLoopThread(this.getHolder(), game);

        gameLoopThread.running = true;
        gameLoopThread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        gameLoopThread.running = false;

        // Shut down the game loop thread cleanly.
        boolean retry = true;
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            game.touchEvent_actionDown(event);
        }

        if (action == MotionEvent.ACTION_MOVE) {
            game.touchEvent_actionMove(event);
        }

        if (action == MotionEvent.ACTION_UP) {
            game.touchEvent_actionUp(event);
        }

        return true;
    }

}

