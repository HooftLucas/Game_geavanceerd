package be.uantwerpen.fti.ei.Graphic;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class J2DPlayer extends Player {
    private final J2DFact graph;
    private BufferedImage playerIm;
    private CommonGraph commonGraph =  CommonGraph.Getinstance();;



    public J2DPlayer(J2DFact graph) throws IOException {
        this.graph = graph;
        playerIm = J2DFact.loadImages("src/Resource/player.png");
                playerIm = J2DFact.resizeImage(playerIm,
                 (commonGraph.tileSize),
                 (commonGraph.tileSize));
    }

    @Override
    public void Vis()  {
        Graphics2D g2d = graph.get2d();
        Coordinate coordinate = graph.CoordinateConverter(playerIm, 65, getMovement().getDy());
        g2d.drawImage(playerIm, (int) coordinate.x, (int) coordinate.y, null);

    }
}

