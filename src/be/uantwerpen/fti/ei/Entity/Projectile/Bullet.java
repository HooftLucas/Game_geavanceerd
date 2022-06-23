package be.uantwerpen.fti.ei.Entity.Projectile;

import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.CommonGame;

import java.io.IOException;

public abstract class Bullet extends ProjectileEntity {
    boolean dir ;

    protected Bullet() throws IOException {
        this.speedX = CommonGame.getInstance().speedBullet;
        this.speedY = 0;
        this.width = CommonGame.getInstance().BulletWidth;
        this.height = CommonGame.getInstance().BulletHeight;
        this.dir = false;

    }



    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public void fly() {

        if (!dir) { // player runs to the right
            getMovement().setDx((float) (getMovement().getDx() + speedX));

        } else
            getMovement().setDx((float) (68 - speedX));

    }
}
