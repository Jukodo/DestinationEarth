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
    
    @Override
    public boolean special(){
        if(this.isAlive()){

          if(this.isInside()){
              this.leaveRoom();
          }

          this.setAlive(false);

          int i = 0;

          do{
              if(!getDataGame().addHealthToPlayer(1)){
                break;
              }
              i++;
          }while(i < 5);

          getDataGame().swapActiveCrewMember();
          getDataGame().addLog("You sacrificed Red Shirt and earned " + (i) + " health! Good journey comrade Red Shirt!");
          return true;
      }
        
        return false;
    }
}
