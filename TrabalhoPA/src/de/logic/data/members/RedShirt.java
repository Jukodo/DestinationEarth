package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class RedShirt extends CrewMember implements Serializable{

    private boolean alive;
    
    public RedShirt(DataGame dataGame) {
        super(dataGame, 1, 1);
        alive = true;
    }

    public RedShirt(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    @Override
    public String getName() {
        return "RedShirt";
    }
}
