package be.uantwerpen.fti.ei.Game;

import be.uantwerpen.fti.ei.Entity.map.Tiles;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import java.io.IOException;

public abstract class AFact {
    /*
    Hierin gaan we alle delen van het spel inmaken. Afact zorgt ervoor dat het meerdere malen gebruikt kan worden voor deze eigenschappen
     */

    protected abstract Input createInput();

    protected abstract void setGameDim(int gameWidth, int gameheigt) throws IOException;

    protected abstract void render();

    protected abstract void showStatus(int lives, int level);

    protected abstract Player CreatePlayer() throws IOException;

    protected abstract Tiles CreateTiles() throws IOException;

}
