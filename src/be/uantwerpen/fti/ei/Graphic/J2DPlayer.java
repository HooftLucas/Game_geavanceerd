package be.uantwerpen.fti.ei.Graphic;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class J2DPlayer extends Player {
    private final J2DFact graph;
    private BufferedImage playerIm;



    public J2DPlayer(J2DFact graph) throws IOException {
        this.graph = graph;
        playerIm = J2DFact.loadImages("src/Resource/player.png");


        CommonGraph.Getinstance();
        CommonGraph.Getinstance();
        playerIm = J2DFact.resizeImage(playerIm,
                 (CommonGraph.tileSize),
                 (CommonGraph.tileSize));
    }

    @Override
    public void Vis()  {
        Graphics2D g2d = graph.get2d();
        Coordinate coordinate = J2DFact.CoordinateConverter(playerIm, getMovement().getDx(), getMovement().getDy());
        g2d.drawImage(playerIm, (int) coordinate.x, (int) coordinate.y, null);

    }
}

