package edu.uco.apatel19.tpaakashp.Objects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import edu.uco.apatel19.tpaakashp.R;
import edu.uco.apatel19.tpaakashp.Threads.GameLoopThread;

public class Game {
    private Helicopter helicopter;

    public int shootX, shootY;

    public static int screenWidth;
    public static int screenHeight;
    public static float screenDensity;
    private boolean gameOver;

    private Paint paintForImages;

    Bitmap pause;
    private static Bitmap background;
    public static Bitmap player;
    public static Bitmap heliBullet, enemyBullet;
    public static Bitmap enemy;

    private List<HeliBullet> bullets;
    private ArrayList<EnemyPlane> planeList;

    ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();

    // Color and size for text.
    private Paint paintText;
    private int textSize;

    // Position of text for restarting the game.
    private float textForRestart_x;
    private float textForRestart_y;

    private SurfaceHolder holder;
    private int z = 0;
    int show = 0, sx, sy, height, width;
    int pausecount = 0;

    // Sound Effects***************************************************
    private SoundPool sounds;

    // ****************************************************************
    public Game(int screenWidth, int screenHeight, Resources resources) {
        Game.screenWidth = screenWidth;
        Game.screenHeight = screenHeight;
        Game.screenDensity = resources.getDisplayMetrics().density;

        sx = 2500;
        sy = screenHeight;

		/*------------------------------------------------------------------------------------*/

        this.LoadContent(resources);

        paintForImages = new Paint();
        paintForImages.setFilterBitmap(true);

        bullets = new ArrayList<>();
        planeList = new ArrayList<>();

        textSize = 25;
        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(textSize * Game.screenDensity);
    }

    /**
     * Load files.
     */
    private void LoadContent(Resources resources) {

        background = BitmapFactory.decodeResource(resources, R.drawable.game_scene_background);

        // create helicopter and load bitmap
        helicopter = new Helicopter(BitmapFactory.decodeResource(resources,
                R.drawable.helicopter), sx / 10, 15 * sy / 27);
        player = BitmapFactory.decodeResource(resources, R.drawable.helicopter);
        enemy = BitmapFactory.decodeResource(resources, R.drawable.enemy_plane);
        pause = BitmapFactory.decodeResource(resources, R.drawable.pause);
        heliBullet = BitmapFactory.decodeResource(resources, R.drawable.helibullet);
        enemyBullet = BitmapFactory.decodeResource(resources, R.drawable.enemybullet);

        player = Bitmap.createScaledBitmap(player, sx /9, sy / 7, true);
        enemy = Bitmap.createScaledBitmap(enemy, sx / 9, sy / 7, true);
        background = Bitmap.createScaledBitmap(background, 2 * sx, sy, true);
        pause = Bitmap.createScaledBitmap(pause, 100, 100, true);
        heliBullet = Bitmap.createScaledBitmap(heliBullet, 60, 22, true);
        enemyBullet = Bitmap.createScaledBitmap(enemyBullet, 30, 25, true);

        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    }

    /**
     * For (re)setting some game variables before game can start.
     */
    private void ResetGame() {
        gameOver = false;
        planeList.clear();

        EnemyPlane.speed = EnemyPlane.initSpeed;
        EnemyPlane.timeBetweenPlanes = EnemyPlane.initTimeBetweenPlanes;
        EnemyPlane.timeOfLastPlane = 0;
        EnemyPlane.timeOfLastSpeedup = 0;

        // We create some starting Planes.
        this.addNewPlane();

        Helicopter.health = 100;

    }

    public void enemyShoot() {
        for (EnemyPlane p : planeList) {

            if (Math.random() * 400 < 10) {
                enemyBullets.add(new EnemyBullet(this, enemyBullet, (int) p.x + 20, (int) p.y + 25));
            }

        }
    }

    /**
     * Game update method.
     *
     * @param gameTime
     *            Elapsed game time in milliseconds.
     */
    public void Update(long gameTime) {
        if (Helicopter.health <= 0) {
            gameOver = true;
        }

        if (gameOver) {
            return;
        }

        // Create new Plane, if time.
        if ((gameTime - EnemyPlane.timeOfLastPlane) > EnemyPlane.timeBetweenPlanes) {
            EnemyPlane.timeOfLastPlane = gameTime;

            this.addNewPlane();
        }

        // Update Planes
        for (int i = 0; i < planeList.size(); i++) {
            EnemyPlane Plane = planeList.get(i);

            Plane.update();

            // Check if any Plane got away and if did end game.
            /*if (Plane.x > Game.screenWidth || Plane.x < 0 - Game.enemy.getWidth()) {
                gameOver = true;
              }*/
        }

        // Speedup the game, if time
        if ((gameTime - EnemyPlane.timeOfLastSpeedup) > EnemyPlane.timeBetweenSpeedups) {
            EnemyPlane.timeOfLastSpeedup = gameTime;

            EnemyPlane.speed += 0.03;
            if (EnemyPlane.timeBetweenPlanes > (0.5 * 1000))
                EnemyPlane.timeBetweenPlanes -= 30;
        }

        enemyShoot();

    }

    /**
     * Draw the game to the screen.
     *
     * @param canvas
     *            Canvas on which we will draw.
     */
    public void Draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        // background moving
        z = z - 25;
        if (z == -sx) {
            z = 0;
            canvas.drawBitmap(background, z, 0, null);

        } else {
            canvas.drawBitmap(background, z, 0, null);
        }

        Iterator<HeliBullet> j = bullets.iterator();
        while (j.hasNext()) {
            HeliBullet b = j.next();
            if (b.x >= 1000 || b.x <= 1000) {
                b.onDraw(canvas);
            } else {
                j.remove();
            }
        }

        Iterator<EnemyBullet> k = enemyBullets.iterator();
        while (k.hasNext()) {
            EnemyBullet c = k.next();
            if (c.getLocx() >= 1000 || c.getLocx() <= 1000) {
                c.onDraw(canvas);
            } else {
                k.remove();
            }
        }

        for (int i = 0; i < planeList.size(); i++) {
            planeList.get(i).draw(canvas);

        }

        helicopter.draw(canvas);
        canvas.drawBitmap(pause, 500, 10, null);
    }

    /**
     * When touch on screen is detected.
     *
     * @param event
     *            MotionEvent
     */
    public void touchEvent_actionDown(MotionEvent event) {

        if (!gameOver) {
            if (event.getX() > (520)&& event.getX() < (580) && event.getY() > (25) && event.getY() < (100)) {
                if (pausecount == 0) {
                    GameLoopThread.setPause(1);

                    pausecount = 1;
                } else if (pausecount == 1) {
                    GameLoopThread.setPause(0);

                    pausecount = 0;
                }
            } else {
                show = 1;

                shootX = (int) event.getX();
                shootY = (int) event.getY();

                bullets.add(new HeliBullet(this, heliBullet, helicopter.getX(), helicopter.getY()));

                // delegating event handling to the helicopter
                helicopter.handleActionDown((int) event.getX(), (int) event.getY());
            }
        } else {
            if (event.getX() > textForRestart_x
                    && event.getX() < textForRestart_x + 280
                    && event.getY() > textForRestart_y - 50
                    && event.getY() < textForRestart_y + 50) {
                this.ResetGame();
            }
        }
    }

    /**
     * When moving on screen is detected.
     *
     * @param event
     *            MotionEvent
     */
    public void touchEvent_actionMove(MotionEvent event) {
        if (helicopter.isTouched()) {
            // the helicopter was picked up and is being dragged
            helicopter.setX((int)event.getX());
            helicopter.setY((int) event.getY());
        }
    }

    /**
     * When touch on screen is released.
     *
     * @param event
     *            MotionEvent
     */
    public void touchEvent_actionUp(MotionEvent event) {
        // touch was released
        if (helicopter.isTouched()) {
            helicopter.setTouched(false);
        }
    }

    private int newYcoordinate() {
        int min = Game.enemy.getHeight();

        int max = Game.screenHeight - Game.enemy.getHeight();

        int height = max - min;

        Random r = new Random();

        int newYcoordiante = r.nextInt(height) + min;

        return newYcoordiante;
    }

    private void addNewPlane() {
        this.planeList.add(new EnemyPlane(newYcoordinate()));
    }
}
