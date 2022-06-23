package be.uantwerpen.fti.ei.Game;

import java.awt.event.KeyEvent;

public abstract class Input {




    abstract public boolean inputAvaible();


    abstract public Inputs getInput();

    abstract public void clearInput();


    /*
    enum is een speciale functie die vaste waardes doorgeeft. We gebruiken dit omdat de bewegingen constant blijven
     */
    public enum Inputs {Left, Right,Jump, fire}
    /**
     * Returns true if the removal of the current element is available.
     *
     * @return A boolean value.
     */
    public abstract boolean removalAvailable();
    /**
     *
     * @return de losgelaten knop
     */
    public abstract Inputs getRemoval();
}

