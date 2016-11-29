package com.cyberllama.testgame;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Date;

public class GameLoop implements Runnable {

    private boolean running = true;
    private final long max_allowed_skips = 25;  //Skips 25 max physics. (Amount of lag until slow down).
    private final long sim_time_step_ms = 5;
    private final long frame_time_ms = 20;

    private final SurfaceHolder surfaceHolder;
    private Paint paint;

    private final int num_bricks = 10;
    private final Bricks[] bricks;
    private final Bitmap brickSprite;





    //Constructor.
    public GameLoop(SurfaceHolder holder, Resources resources){

        //Initialize variables.
        this.surfaceHolder = holder;

        //Initialize sprites.
        this.bricks = new Bricks[num_bricks];
        for(int i = 0; i < num_bricks; ++i){
            bricks[i] = new Bricks();
        }

        brickSprite = BitmapFactory.decodeResource(resources, R.drawable.muuud);


        paint = new Paint();
    }




    @Override
    public void run(){

        //Keep time of 'game world'.
        //Get start time.
        long simulation_time = new Date().getTime();
        long frame_start_time = simulation_time;

        while(running){
            //Put in separate classes.

            processInput();

            //Update sim time.
            simulation_time = doPhysics(simulation_time, frame_start_time);

            drawGraphics();

            //Update frame starting time.
            frame_start_time = waitForNextFrame(frame_start_time);
        }
    }

    //Stop sleeping thread.
    public void pleaseStop() {
        running = false;
    }





    private long waitForNextFrame( long frame_start_time){

        //Get estimate of next frame starting time.
        long next_frame_start_time = new Date().getTime();

        //Get how long we took (this frame start time - next_frame start time).
        long how_long_we_took = next_frame_start_time - frame_start_time;

        //Wait (estimated frame time - frame render time.
        long wait_time = frame_time_ms - how_long_we_took;

        //Wait if required.
        if(wait_time > 0) {
            try {
                Thread.sleep(wait_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return next_frame_start_time;
    }






    private void drawGraphics(){

        //Gives us access to canvas.
        Canvas canvas = surfaceHolder.lockCanvas();

        //Check if canvas is busy.
        if(canvas == null){
            return;
        }

        try{
            //Make sure 'no one else' is interfering.
            synchronized (surfaceHolder) {
                actuallyDrawGraphics(canvas);
            }
        }finally {
            //Release hold on canvas.
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    private void actuallyDrawGraphics(Canvas canvas){

        canvas.drawColor(Color.WHITE);

        //Draw graphic.
        for(Sprite b : bricks){
            canvas.drawBitmap(
                    brickSprite,
                    -16f + ((b.getX()/100f) * canvas.getWidth()),
                    -16f + ((b.getY()/100f) * canvas.getHeight()),
                    paint
            );
        }
    }






    //Number of times moved balls is called to catch up to time in real world.
    //Stops if max is reached OR time has sim time has catched up to real time.
    private long doPhysics(long simulation_time, long frame_start_time){


        //Catch up physics if drawing took to long. (FPS handler).
        for(int skipped = 0; skipped < max_allowed_skips; ++ skipped){

            if(simulation_time >= frame_start_time){
                break;
            }

            moveSprites();
            simulation_time += sim_time_step_ms;
        }

        return simulation_time;
    }

    //Update sprite object location.
    private void moveSprites(){
        for(Sprite b : bricks){
            b.move();
        }
    }







    private void processInput() {

    }



}
