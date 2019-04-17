package de.logic.data;

public interface Constants {
    public static final int NUM_TURNS = 13;
    public static final int NUM_ROOMS = 12;
    public static final int NUM_CREW_MEMBERS = 2;
   
    public static final String[] DEF_JOURNEY = {"2A", "3A", "4A", "5A*", "R", "4A", "5A", "6A*", "R", "6A", "7A*", "R", "8A"};
    public static final int[] MAX_SPAWN_ALIENS_TURN = {2, 3, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
    public static final int[] MIN_SPAWN_ALIENS_TURN = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7};
    
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
    public static final int MAX_ATTACK_BUFF = 4; //AT 0 -> TO ATTACK DICE NEEDS TO BE 5+, AT 4 -> TO ATTACK DICE NEEDS TO BE 1+ (5-4)
    
    public static final int MIN_ROLL_ATTACK = 5;
    
    public static final int DEF_HULL_TRACKER = 8;
    public static final int DEF_HEALTH_TRACKER = 8;
    public static final int DEF_INSPIRATION_POINTS = 1;
    public static final int DEF_ABILITY_POINTS = 2;
    
    public static final String[] DEF_ACTIONS = {"Move", "Attack", "Place Trap", "Detonate Particle Dispenser", "Seal Room"};
    public static final int DEF_COST_TRAP_ORGANIC = 1;
    public static final int DEF_COST_TRAP_PARTICLE = 1;
    public static final int DEF_COST_MOVE = 1;
    public static final int DEF_COST_ATTACK = 1;
    public static final int DEF_COST_HEAL = 1;
    public static final int DEF_COST_FIX_HULL = 1;
    public static final int DEF_COST_DETONATE_TRAP_PARTICLE = 1;
    public static final int DEF_COST_SEAL_ROOM = 1;
    public static final int[] DEF_ACTIONS_COST = {DEF_COST_MOVE, DEF_COST_ATTACK, DEF_COST_TRAP_ORGANIC, DEF_COST_DETONATE_TRAP_PARTICLE, DEF_COST_SEAL_ROOM};
    
    public static final int DEF_COST_I_ADD_HEALTH = 1;
    public static final int DEF_COST_I_REPAIR_HULL = 1;
    public static final int DEF_COST_I_BUILD_TRAP_ORGANIC = 2;
    public static final int DEF_COST_I_BUILD_TRAP_PARTICLE = 5;
    public static final int DEF_COST_I_ADD_MOVEMENT = 4;
    public static final int DEF_COST_I_ADD_SEALED_TOKEN = 5;
    public static final int DEF_COST_I_ADD_ATTACK_DIE = 6;
    public static final int DEF_COST_I_ADD_VALUE_ATTACK_DIE = 6;
    
}
