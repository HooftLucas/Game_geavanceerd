package be.uantwerpen.fti.ei.Graphic;

import be.uantwerpen.fti.ei.Entity.personage.Enemy;
import be.uantwerpen.fti.ei.Entity.personage.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class J2DEnemy extends Enemy {
    private final J2DFact graph;
    private Player P;
    private BufferedImage enemyIm;
    private CommonGraph commonGraph = CommonGraph.Getinstance();
    float screenX;
    int valuex;
    public J2DEnemy(J2DFact graph, Player p) throws IOException {
        this.graph = graph;
        this.P = p;
        enemyIm = J2DFact.loadImages("src/Resource/Level1/Enemy.jpeg");
        enemyIm = J2DFact.resizeImage(enemyIm,     (commonGraph.tileSize),
                (commonGraph.tileSize));
    }
    @Override
    public void Vis() {

        Graphics2D g2d = graph.get2d();

        for (int x = 0; x < xcoor.size(); x++) {
            valuex = (int) xcoor.get(x);
            Coordinate coordinate = graph.CoordinateConverter(enemyIm, valuex, 97);
            screenX = (float) (valuex*64 - P.getWorldX());
            g2d.drawImage(enemyIm, (int) screenX, (int) coordinate.y, null);
        }



    }
}



