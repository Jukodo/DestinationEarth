/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data;

/**
 *
 * @author Tiago
 */
public class OrganicDetonator extends Trap implements Constants {

    public OrganicDetonator(DataGame dataGame) {
        super(dataGame, DEF_COST_TRAP_ORGANIC);
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}