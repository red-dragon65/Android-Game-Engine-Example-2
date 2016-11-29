package com.cyberllama.testgame;

import java.util.Random;


public class Bricks extends Sprite {

    //Variables.
    Random rand = new Random();

    boolean bounce = true;

    //Constructor.
    public Bricks(){
        super();



        //Randomly space bricks. (x-axis).
        x = rand.nextFloat() * 90f;
        y = rand.nextFloat() * 85f;


        //Set vertical speed.
        vy = 0.1f;
    }


    @Override
    public void move(){

        //Update location based on velocity.
        x += vx;
        y += vy;


        if(vy > 0){ //Check direction. (down)
            if(vy < 0.5f){ //Increase down.
                vy+=0.001;
            }else{          //down cap.
                vy = 0.5f;
            }
        }else{ // (up)
            if(vy > -0.5f){ //increase down.
                vy+=0.001;
            }
        }


        //Bounce. (Bounds control). (Change brick location).
        //Horizontal.
        if(x < 0f || x > 90f){
            vx = -vx;
        }

        //Vertical.
        if(y < 0f || y > 93f){
            vy = -vy;

            //Reset values.
            if(vy > 0) {
                y = 1f;
                vy = 0.01f;
            }else {
                y = 92f;
            }

            //Reset x value.
            //x = rand.nextFloat() * 90f;

        }


    }




}
