/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data.members;

import de.logic.data.DataGame;

/**
 *
 * @author Tiago
 */
public class SecurityOfficer extends CrewMember {
    
    public SecurityOfficer(DataGame dataGame, int color) {
        super(dataGame, 1, 2, color);
    }
    
}