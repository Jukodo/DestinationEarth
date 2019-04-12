package de.logic.data;

public interface Constants {
    public static final int NUM_TURNS = 13;
    public static final int NUM_ROOMS = 12;
    public static final int NUM_CREW_MEMBERS = 2;
    
    public static final int MAX_COLOR = 12;
    public static final String[] COLOR = {"White", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Yellow", "Magenta", "Orange", "Pink", "Red", "Black"};
    
    public static final int MAX_DICES = 3;
    
    public static final int MAX_ALIENS = 15;
    public static final int MAX_SEALED_ROOMS = 6;
    public static final int MAX_TRAPS_ORGANIC = 4;
    public static final int MAX_TRAPS_PARTICLE = 2;
    
    public static final int MAX_HEALTH = 12;
    public static final int MAX_HULL = 12;
    public static final int MAX_MOVEMENT = 3;
    public static final int MAX_ATTACK = 3;
    
    public static final int MIN_ROLL_ATTACK = 5;
    
    public static final int DEF_HULL_TRACKER = 8;
    public static final int DEF_HEALTH_TRACKER = 8;
    public static final int DEF_INSPIRATION_POINTS = 1;
    public static final int DEF_ABILITY_POINTS = 1;
    
    public static final int DEF_COST_TRAP_ORGANIC = 1;
    public static final int DEF_COST_TRAP_PARTICLE = 1;
    public static final int DEF_COST_MOVE = 1;
    public static final int DEF_COST_ATTACK = 1;
    public static final int DEF_COST_HEAL = 1;
    public static final int DEF_COST_FIX_HULL = 1;
    public static final int DEF_COST_DETONATE_TRAP_PARTICLE = 1;
    public static final int DEF_COST_SEAL_ROOM = 1;
    
    public static final int DEF_COST_I_ADD_HEALTH = 1;
    public static final int DEF_COST_I_REPAIR_HULL = 1;
    public static final int DEF_COST_I_BUILD_TRAP_ORGANIC = 2;
    public static final int DEF_COST_I_BUILD_TRAP_PARTICLE = 5;
    public static final int DEF_COST_I_ADD_MOVEMENT = 4;
    public static final int DEF_COST_I_ADD_SEALED_TOKEN = 5;
    public static final int DEF_COST_I_ADD_ATTACK_DIE = 6;
    public static final int DEF_COST_I_ADD_VALUE_ATTACK_DIE = 6;
    
}
