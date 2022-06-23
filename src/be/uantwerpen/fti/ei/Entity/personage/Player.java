package be.uantwerpen.fti.ei.Entity.personage;

import be.uantwerpen.fti.ei.Game.CommonGame;

import java.awt.*;
import java.io.IOException;

public abstract class Player extends PlayerEntity {
    boolean killed = false ;
    protected Player() throws IOException {
        this.speedX = CommonGame.getInstance().speedPlayerx;
        this.speedY = CommonGame.getInstance().speedPlayery;
        this.width = CommonGame.getInstance().playerWidth;
        this.height = CommonGame.getInstance().playerHeight;
        solidArea = new Rectangle(8,8,56,56);

    }


    public void setKilled(boolean restart) {
        this.killed = restart;
    }
    public Boolean getkilled(){
        return killed;
    }
}


