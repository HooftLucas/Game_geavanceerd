package be.uantwerpen.fti.ei.Entity.map;

import be.uantwerpen.fti.ei.Entity.Entity;
import be.uantwerpen.fti.ei.Game.CommonGame;
import be.uantwerpen.fti.ei.Graphic.CommonGraph;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tiles extends Entity {
    public BufferedImage Image;

    public int teller = 0;

    private CommonGraph commongraph = CommonGraph.Getinstance();

    public Tiles() throws IOException {
        width = commongraph.tileSize;
        height = commongraph.tileSize;
    }



    @Override
    public void Vis() throws IOException {
    }
}

