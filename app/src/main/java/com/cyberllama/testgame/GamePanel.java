package com.cyberllama.testgame;


import android.content.Context;
import android.content.res.Resources;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {


    private final Resources resources;
    private GameThread gameThread;

    public GamePanel(Context context, Resources resources){

        super(context);
        gameThread = null;
        this.resources = resources;

        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameThread(holder, resources);
        gameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        if(gameThread != null){
            gameThread.stop();
        }

        gameThread = null;
    }
}
