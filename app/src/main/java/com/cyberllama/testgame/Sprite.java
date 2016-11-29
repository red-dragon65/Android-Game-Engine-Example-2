package com.cyberllama.testgame;


abstract class Sprite {

    //Variables.
    protected float x;
    protected float y;
    protected float vx;
    protected float vy;



    //Constructor.
    public Sprite(){

        x = 0f;
        y = 0f;
        vx = 0f;
        vy = 0f;
    }


    //Method.
    public void move(){

        //Update location based on velocity.
        x += vx;
        y += vy;


        //Bounce. (Bounds control).
        if(x < 0f || x > 90f){
            vx = -vx;
        }
        if(y < 0f || y > 93f){
            vy = -vy;
        }
    }



    //Setters.
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }



    //Getters.
    public float getX() { return x; }
    public float getY() { return y; }


}
