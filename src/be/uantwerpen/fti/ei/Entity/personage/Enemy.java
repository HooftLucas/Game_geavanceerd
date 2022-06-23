package be.uantwerpen.fti.ei.Entity.personage;

import be.uantwerpen.fti.ei.Game.CommonGame;
import be.uantwerpen.fti.ei.Graphic.J2DTile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class Enemy extends PlayerEntity {

        public List xcoor = new Vector();
    private List ycoor = new Vector();


    protected Enemy() throws IOException {
            this.speedX = 0;
            this.speedY = 0;
            this.width = CommonGame.getInstance().playerWidth;
            this.height = CommonGame.getInstance().playerHeight;
            solidArea = new Rectangle(8,8,56,56);

        }
        public void setxList(int x){
            xcoor.add(x);
        }
        public void setyList(int y){
            ycoor.add(y);

    }
        public void removeX(int x){
            xcoor.remove(x);
            System.out.println(xcoor);
        }






}
