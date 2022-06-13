package be.uantwerpen.fti.ei.Game;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.*;
public class Movement {
    //deze 2 snelheden hebben te maken met de horizontale snelheden. Hier moet geen vertraging of versnelling inzitten
    private float dx ;
    private float dy ;
    //dit zijn versnellingen en vertragingen voor te springen
    private float ax ;
    private float ay ;

    public boolean onGround;

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getAx() {
        return ax;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public float getAy() {
        return ay;
    }

    public void setAy(float ay) {
        this.ay =ay;
    }

}
