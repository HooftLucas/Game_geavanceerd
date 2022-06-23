package be.uantwerpen.fti.ei.Graphic;

import be.uantwerpen.fti.ei.Entity.Projectile.Bullet;
import be.uantwerpen.fti.ei.Entity.Projectile.ProjectileEntity;
import be.uantwerpen.fti.ei.Entity.personage.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class  J2DBullet extends Bullet {
    private Player P;
    private J2DFact gr;
    public BufferedImage BulletImg;

    public J2DBullet(J2DFact gr, Player P) throws IOException {
        this.gr = gr;
        this.P = P;
        BulletImg = gr.loadImages("src/Resource/bullet.jpeg");
        BulletImg = gr.resizeImage(BulletImg, (int) (this.width* gr.getSize()), (int) (this.height*(gr.getSize())));

    }

    @Override
    public void Vis() {
        Graphics2D g2d = gr.get2d();
        float yvalue = P.getMovement().getDy();
        Coordinate coordinate = gr.CoordinateConverter(BulletImg, getMovement().getDx(),  (getMovement().getDy()));
        g2d.drawImage(BulletImg, (int) coordinate.x, (int) coordinate.y, null);
    }
}
