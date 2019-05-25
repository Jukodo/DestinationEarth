package de.logic.data;

public interface Constants {
    public static final String GAME_TITLE = "Destination Earth";
    
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
    public static final int CAPTAIN_ROLL_ATTACK = 3;
    
    public static final int DEF_HULL_TRACKER = 8;
    public static final int DEF_HEALTH_TRACKER = 8;
    public static final int DEF_INSPIRATION_POINTS = 1;
    public static final int DEF_ACTION_POINTS = 5;
    //Actions
    public static final String[] DEF_ACTIONS = {"Move", "Attack", "Place Trap", "Detonate Particle Dispenser", "Seal Room"};
    public static final int DEF_COST_A_TRAP_ORGANIC = 1;
    public static final int DEF_COST_A_TRAP_PARTICLE = 1;
    public static final int DEF_COST_A_MOVE = 1;
    public static final int DEF_COST_A_ATTACK = 1;
    public static final int DEF_COST_A_HEAL = 1;
    public static final int DEF_COST_A_FIX_HULL = 1;
    public static final int DEF_COST_A_DETONATE_TRAP_PARTICLE = 1;
    public static final int DEF_COST_A_SEAL_ROOM = 1;
    public static final int[] DEF_ACTIONS_COST = {DEF_COST_A_MOVE, DEF_COST_A_ATTACK, DEF_COST_A_TRAP_ORGANIC, DEF_COST_A_DETONATE_TRAP_PARTICLE, DEF_COST_A_SEAL_ROOM};
    //Inspitations
    public static final String[] DEF_INSPIRATIONS = {"Recover Health", "Repair Hull", "Build Organic Detonator", "Increase Member Movement", "Build Particle Desperser", "Build Room Blocker", "Increase Attack Dice", "Increase Attack"};
    public static final int DEF_COST_I_ADD_HEALTH = 1;
    public static final int DEF_COST_I_REPAIR_HULL = 1;
    public static final int DEF_COST_I_BUILD_TRAP_ORGANIC = 2;
    public static final int DEF_COST_I_ADD_MOVEMENT = 4;
    public static final int DEF_COST_I_BUILD_TRAP_PARTICLE = 5;
    public static final int DEF_COST_I_ADD_SEALED_TOKEN = 5;
    public static final int DEF_COST_I_ADD_ATTACK_DIE = 6;
    public static final int DEF_COST_I_ADD_VALUE_ATTACK_DIE = 6;
    public static final int[] DEF_INSPIRATIONS_COST = {DEF_COST_I_ADD_HEALTH, DEF_COST_I_REPAIR_HULL, DEF_COST_I_BUILD_TRAP_ORGANIC, DEF_COST_I_ADD_MOVEMENT, DEF_COST_I_BUILD_TRAP_PARTICLE, DEF_COST_I_ADD_SEALED_TOKEN, DEF_COST_I_ADD_ATTACK_DIE, DEF_COST_I_ADD_VALUE_ATTACK_DIE};
    
    public static final int ORGANIC_TRAP = 1;
    public static final int PARTICLE_TRAP = 2;
    
    public static final int CAPTAIN = 1;
    public static final int COMMANDER = 2;
    public static final int COOMS_OFFICER = 3;
    public static final int DOCTOR = 4;
    public static final int ENGINEER = 5;
    public static final int MORAL_OFFICER = 6;
    public static final int NAVIGATION_OFFICER = 7;
    public static final int RED_SHIRT = 8;
    public static final int SCIENCE_OFFICER = 9;
    public static final int SECURITY_OFFICER = 10;
    public static final int SHUTTLE_PILOT = 11;
    public static final int TRANSPORTER_CHIEF = 12;
    public static final String[] CREWMEMBER_TYPES = {"Captain", "Commander", "Cooms Officer", "Doctor", "Engineer", "Moral Officer", "Navigation Officer",
                                                     "Red Shirt", "Science Officer", "Security Officer", "Shuttle Pilot", "Transporter Chief"};

    //GUI
    public static final int WINDOW_X = 1280;
    public static final int WINDOW_Y = 720;
    
    public static final int NUM_SCENES = 9;
    public static final int SCENE_BEGINNING = 0;
        public static final int STATE_INITIAL = 0;
        public static final int STATE_PLAY = 1;
        public static final int STATE_RULES = 2;
        public static final int STATE_NEW_GAME = 3;
    public static final int SCENE_CREWSELECTION = 1;
        public static final int CREWMEMBER_TYPES_PER_LINE = 3;
        public static final int CREWMEMBER_TYPES_MAX_X = ((30 * WINDOW_X) / 100); //30%
    public static final int SCENE_CREWPLACEMENT = 2;
    public static final int SCENE_JOURNEYSELECTION = 3;
    public static final int SCENE_JOURNEYPHASE = 4;
    public static final int SCENE_RESTPHASE = 5;
    public static final int SCENE_CREWPHASE = 6;
    public static final int SCENE_ALIENPHASE = 7;
    public static final int SCENE_GAMEOVER = 8;
    /*Remove later*/public static final String[] SCENE = {"SCENE_BEGINNING", "SCENE_CREWSELECTION", "SCENE_CREWPLACEMENT", 
            "SCENE_JOURNEYSELECTION", "SCENE_JOURNEYPHASE", "SCENE_RESTPHASE", "SCENE_CREWPHASE", "SCENE_ALIENPHASE", "SCENE_GAMEOVER"};
    
    public static final int STATE_BAR_Y = ((5 * WINDOW_Y) / 100);//5%
    public static final int STATE_BAR_PREGAME = 0;
    public static final int STATE_BAR_INGAME = 1;
    public static final String[] STATE_BAR_PREGAME_STATES = {"Crew Selection", "Crew Placement", "Journey Selection"};
    public static final String[] STATE_BAR_INGAME_STATES = {"Journey Phase", "Rest/Crew Phase", "Alien Phase"};
    
    public static final int CREWMEMBER_BAR_Y = ((7 * WINDOW_Y) / 100);//7%
    
    public static final int INSIDE_PADDING = 10;
    
    //FirePropertyChange
    public static final String FPC_SWAP_SCENE = "SwapScene";
    public static final String FPC_CLOSE_WINDOW = "CloseWindow";
}
