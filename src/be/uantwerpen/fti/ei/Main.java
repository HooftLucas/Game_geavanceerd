package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.Game.AFact;
import be.uantwerpen.fti.ei.Game.Game;
import be.uantwerpen.fti.ei.Graphic.J2DFact;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AFact AF = new J2DFact();
        Game game = new Game(AF);
        game.play();
    }
}
// commentaar dat zeker nog moet gebeuren :


/*
- overal java doc commentaar
- zo weinig mogelijk omzettingen qua floats -> ints
 */