package de.logic.states;

import de.logic.data.DataGame;

public class RestPhase extends StateAdapter{
    
    public RestPhase(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates IP_addHealthPoint(){
        this.getGame().IP_addHealthPoint();
        return this;
    }
    
    @Override
    public IStates IP_repairHull(){
        this.getGame().IP_repairHull();
        return this;
    }
    
    @Override
    public IStates IP_buildOrganicDetonator(){
        this.getGame().IP_buildOrganicDetonator();
        return this;
    }
    
    @Override
    public IStates IP_addMovement(int activeCrewMember){
        this.getGame().IP_addMovement(activeCrewMember);
        return this;
    }
    
    @Override
    public IStates IP_buildParticleDesperser(){
        this.getGame().IP_buildParticleDesperser();
        return this;
    }
    
    @Override
    public IStates IP_addSealedRoomToken(){
        this.getGame().IP_addSealedRoomToken();
        return this;
    }
    
    @Override
    public IStates IP_addAttackDie(int activeCrewMember){
        this.getGame().IP_addAttackDie(activeCrewMember);
        return this;
    }
    
    @Override
    public IStates IP_addValueToAttackDie(){
        this.getGame().IP_addValueToAttackDie();
        return this;
    }
    
    @Override
    public IStates leaveRestPhase(){
        return new JourneyPhase(this.getGame());
    }
}
