package be.uantwerpen.fti.ei.Game;

import be.uantwerpen.fti.ei.Entity.Projectile.Bullet;
import be.uantwerpen.fti.ei.Entity.personage.Enemy;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Graphic.J2DTile;

import java.io.IOException;

public abstract class AFact {
    /*
    Hierin gaan we alle delen van het spel inmaken. Afact zorgt ervoor dat het meerdere malen gebruikt kan worden voor deze eigenschappen
     */



    public abstract Bullet CreateBullet(Player P) throws IOException;

    protected abstract Input createInput();

    protected abstract void setGameDim(int gameWidth, int gameheigt) throws IOException;

    protected abstract void render();

    protected abstract void showStatus(int lives, int level);

    protected abstract Player CreatePlayer() throws IOException;

    protected abstract Enemy createEnemy(Player p) throws IOException;

   protected abstract J2DTile CreateTiles(Player p) throws IOException;


    //protected abstract Enemy createEnemy() throws IOException;
}
