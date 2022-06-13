package be.uantwerpen.fti.ei.Entity;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.*;
import be.uantwerpen.fti.ei.Graphic.CommonGraph;

import java.io.IOException;

import static be.uantwerpen.fti.ei.Game.Game.player;

public abstract class Entity {
    /**
     * De beweging wordt overgenomen
     */
    private static final Movement movement = new Movement();

    public double speedX;

    public double speedY;

    public double width;

    public static double height;
    int TilesSize = CommonGraph.Getinstance().tileSize;
    public static boolean onGround = true;
    public static double WorldX;
    public double WorldY; // beginpositie van de map

    private float currentjump;

    private float currentfallspeed = 1;
    protected Entity() throws IOException{

    }
    public static Movement getMovement(){
        return movement;
    }

    public void move(String dir, boolean jump, double getmovementY)   {
            if (dir.equals("Right")) {
                getMovement().setAx((float) speedX);
                WorldX += getMovement().getAx();

            }
            if (dir.equals("Left")) {

                getMovement().setAx((float) (-speedX));
                WorldX += getMovement().getAx();


            }

            if (jump && dir == "Jump") { //&& !CollisionOn.equals("Collision Up")
                double getspeedY = 0;
                while (getspeedY <= speedY) {
                    getspeedY += 0.1;
                    getMovement().setDy(getMovement().getDy() - (float) (getspeedY));
                }
            }
            if (!jump && dir == "Down") {
                double getspeedY = 0;

                System.out.println(getmovementY);
                while (getspeedY <= speedY) {
                    getspeedY += 0.1;
                    getMovement().setDy(getMovement().getDy() + (float) (getspeedY));
                }
            }



    }


    public abstract void Vis() throws IOException;

}
//&& !CollisionOn.equals("Collision Right")
