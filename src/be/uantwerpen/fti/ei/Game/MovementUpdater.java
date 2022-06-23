package be.uantwerpen.fti.ei.Game;

public class MovementUpdater {
    Movement[] update(Movement[] list){
        for(var elem : list){
            elem.setDx(elem.getDx()+ elem.getAx());
            elem.setDy(elem.getDy()+ elem.getAy());
        }
        return list;
    }
}
