package be.uantwerpen.fti.ei.Entity;


import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.*;

import java.io.IOException;



public abstract class Entity {
    /**
     * De beweging wordt overgenomen
     */
    private Movement movement = new Movement();
    public double speedX;
    public double speedY;

    public double width;


    public double height;
    public boolean onGround = true;
    public double WorldX;
    public double WorldY; // beginpositie van de map
    public boolean Dead = false;
    public double Xbullet;
    public double getWorldX() {
        return WorldX;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setWorldY(double worldY) {
        WorldY = worldY;
    }

    public void setWorldX(double worldX) {
        WorldX = worldX;
    }

    protected Entity() throws IOException{
    }
    public Movement getMovement(){
        return movement;
    }

    public void move(String dir, boolean jump, double getmovementY)   {
            if (dir.equals("Right")) {
                this.getMovement().setAx((float) this.speedX);
                WorldX += this.getMovement().getAx();
            }
            if (dir.equals("Left")) {
                this.getMovement().setAx((float) (-this.speedX));
                WorldX += this.getMovement().getAx();
            }
            if(dir.equals("Fire")){
                Xbullet = getMovement().getDx() + this.speedX;
                this.getMovement().setAx((float) Xbullet);
            }


            if (jump && dir == "Jump") { //&& !CollisionOn.equals("Collision Up")
                double getspeedY = 0;
                while (getspeedY <= this.speedY) {
                    getspeedY += 0.1;
                    this.getMovement().setDy(getMovement().getDy() - (float) (getspeedY));
                    //System.out.println(this.getMovement().getDy());

                }
            }
            if (!jump && dir == "Down") {
                double getspeedY = 0;

                //System.out.println(getmovementY);
                while (getspeedY <= this.speedY) {
                    getspeedY += 0.1;
                    this.getMovement().setDy(getMovement().getDy() + (float) (getspeedY));
                }
            }



    }


    public abstract void Vis() throws IOException;

}
//&& !CollisionOn.equals("Collision Right")
