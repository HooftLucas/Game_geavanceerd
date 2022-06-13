package be.uantwerpen.fti.ei.Entity.personage;
import be.uantwerpen.fti.ei.Entity.Entity;

import java.awt.*;
import java.io.IOException;

public abstract class PlayerEntity extends Entity {
    public boolean isDead = false;
    public Rectangle solidArea;
    protected PlayerEntity() throws IOException {
    }

}
