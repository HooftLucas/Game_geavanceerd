package be.uantwerpen.fti.ei.Graphic;
import be.uantwerpen.fti.ei.Game.*;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.event.KeyEvent;

public class J2Dinput extends Input {
    /**
     * hierin gaan we zorgen dat de player gaat bewegen onder de gekoopelde toetsen
     */
    public LinkedList<Inputs> keyInputs;
    public LinkedList<Inputs> KeyRemovals;
    private J2DFact gr;
    public J2Dinput(J2DFact gr) {
        this.gr = gr;
        keyInputs = new LinkedList<Inputs>();
        KeyRemovals = new LinkedList<Inputs>();
        gr.getFrame().addKeyListener(new KeyInputAdapter());

    }


    @Override
    public boolean inputAvaible() {
        return keyInputs.size() > 0;
    }



    @Override
    public Inputs getInput() {
        return keyInputs.poll();
    }

    @Override
    public void clearInput() {

    }



    public boolean removalAvailable() {return KeyRemovals.size() > 0;}
    public Inputs getRemoval() {return  KeyRemovals.poll(); }



    class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {

                case KeyEvent.VK_LEFT:
                    keyInputs.add(Inputs.Left);
                    break;
                case KeyEvent.VK_RIGHT:
                    keyInputs.add(Inputs.Right);

                    break;

                case KeyEvent.VK_UP:
                    keyInputs.add(Inputs.Jump);

                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {

                case KeyEvent.VK_LEFT:
                    KeyRemovals.add(Inputs.Left);
                    break;
                case KeyEvent.VK_RIGHT:
                    KeyRemovals.add(Inputs.Right);

                    break;

                case KeyEvent.VK_UP:
                    KeyRemovals.add(Inputs.Jump);

                    break;
            }
        }
    }
}