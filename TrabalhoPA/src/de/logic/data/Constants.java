package de.logic.data;

import javafx.scene.paint.Color;

public interface Constants {
    public static final String GAME_TITLE = "Destination Earth";

    public static final int STATE_UNKNOWN = -1;
    public static final int STATE_BEGINNING = 0;
    public static final int STATE_CREW_SELECTION = 1;
    public static final int STATE_CREW_PLACEMENT = 2;
    public static final int STATE_JOURNEY_SELECTION = 3;
    public static final int STATE_JOURNEY_PHASE = 4;
    public static final int STATE_SCANNING_PHASE = 5;
    public static final int STATE_REST_PHASE = 6;
    public static final int STATE_CREW_PHASE = 7;
        public static final int STATE_ATTACK_ALIENS = 8;
        public static final int STATE_DETONATE_PARTICLE = 9;
        public static final int STATE_MOVE_CREW_MEMBER = 10;
        public static final int STATE_PLACE_TRAP = 11;
        public static final int STATE_SEAL_ROOM = 12;
    public static final int STATE_GAME_OVER = 13;
    public static final int STATE_DICE_ROLLING = 14;
    public static final String[] STATES = {"STATE_BEGINNING", "STATE_CREW_SELECTION", "STATE_CREW_PLACEMENT", "STATE_JOURNEY_SELECTION",
                                           "STATE_JOURNEY_PHASE", "STATE_SCANNING_PHASE", "STATE_REST_PHASE", "STATE_CREW_PHASE",
                                           "STATE_ATTACK_ALIENS", "STATE_DETONATE_PARTICLE", "STATE_MOVE_CREW_MEMBER", "STATE_PLACE_TRAP",
                                           "STATE_SEAL_ROOM", "STATE_GAME_OVER", "STATE_DICE_ROLLING"};
    
    public static final int NUM_TURNS = 13;
    public static final int NUM_ROOMS = 12;
    public static final int NUM_CREW_MEMBERS = 2;
   
    public static final String[] DEF_JOURNEY = {"2A", "3A", "4A", "5A*", "R", "4A", "5A", "6A*", "R", "6A", "7A*", "R", "8A"};
    public static final int[] MAX_SPAWN_ALIENS_TURN = {2, 3, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
    public static final int[] MIN_SPAWN_ALIENS_TURN = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7};
    public static final String ACCEPTABLE_EVENTS = "#A | #A* | R";
    
    public static final int MAX_COLOR = 12;
    public static final String[] COLOR = {"White", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Yellow", "Magenta", "Orange", "Pink", "Red", "Black"};
    public static final int CUSTOM_COLOR = -1;
    
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
    public static final String DEF_ACTION_HEAL = "Heal";
    public static final String DEF_ACTION_FIX_HULL = "Fix Hull";
    public static final int DEF_COST_A_MOVE = 1;
    public static final int DEF_COST_A_ATTACK = 1;
    public static final int DEF_COST_A_TRAP_ORGANIC = 1;
    public static final int DEF_COST_A_TRAP_PARTICLE = 1;
    public static final int DEF_COST_A_DETONATE_TRAP_PARTICLE = 1;
    public static final int DEF_COST_A_SEAL_ROOM = 1;
    public static final int DEF_COST_A_HEAL = 1;
    public static final int DEF_COST_A_FIX_HULL = 1;
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
    
    public static final int DEF_COST_REDSHIRT_SACRIFICE = 0;
    public static final String DEF_REDSHIRT_SACRIFICE = "Sacrifice Red Shirt";
    
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
    public static final int[][] CREWMEMBER_STATS = {
        {1, 1}, 
        {1, 1}, 
        {1, 1}, 
        {1, 1}, 
        {1, 1}, 
        {1, 1}, 
        {2, 1}, 
        {1, 1}, 
        {1, 1}, 
        {1, 2}, 
        {1, 1}, 
        {0, 1}
    };
    
    public static final int STATS_MOV = 0;
    public static final int STATS_ATK = 1;
    
    public static final String[] CREWMEMBER_SPECIALS = {
        "Can attack an alien on a 3+.", 
        "6 AP per turn instead of 5.", 
        "Before an alien attacks this crew member, roll 1D6, on a 1 or 2 that alien does not attack this officer.", 
        "Can heal 2 Health for 1 AP when resting.\nCan heal 1 Health for 1 AP.\nCan heal 1 Health per round for free if in Sickbay.", 
        "Can fix 2 Hull for 1 AP when resting.\nCan fix 1 Hull for 1 AP.\nCan fix 1 Hull per round for free if in Engineering.", 
        "Starts with 5 IP.",
        "Can move 2 rooms for 1 AP.",                          
        "Can be sacrificed to gain 5 Health at any time. If you do, you play on with only the other single crew Member.", 
        "Can attack aliens in any adjacent room as long as there is an open door from this crew member to the targeted alien.", 
        "Starts with 2D6 for Attack.", 
        "Starts with 4 extra Health.", 
        "Can teleport to any room on the ship for 1 AP."
    };

    //GUI
    public static final int WINDOW_X = 1280;
    public static final int WINDOW_Y = 720;
        
    public static final boolean SHOW_MENU = false;
    public static final int MENU_Y = 50;
    public static final int WINDOW_Y_WITH_MENU = WINDOW_Y + MENU_Y;
    
    public static final int NUM_SCENES = 9;
    public static final int SCENE_BEGINNING = 0;
    public static final int SCENE_CREW_SELECTION = 1;
    public static final int SCENE_CREW_PLACEMENT = 2;
    public static final int SCENE_JOURNEY_SELECTION = 3;
    public static final int SCENE_JOURNEY_PHASE = 4;
    public static final int SCENE_REST_PHASE = 5;
    public static final int SCENE_CREW_PHASE = 6;
    public static final int SCENE_GAME_OVER = 7;
    /*Remove later*/public static final String[] SCENE = {"SCENE_BEGINNING", "SCENE_CREWSELECTION", "SCENE_CREWPLACEMENT", 
            "SCENE_JOURNEYSELECTION", "SCENE_JOURNEYPHASE", "SCENE_RESTPHASE", "SCENE_CREWPHASE", "SCENE_ALIENPHASE", "SCENE_GAMEOVER"};
    
    //Used on Beginning_layout
    public static final int STATE_INITIAL = 0;
    public static final int STATE_PLAY = 1;
    public static final int STATE_RULES = 2;
    public static final int STATE_NEW_GAME = 3;
    
    public static final int STATE_BAR_PREGAME = 0;
    public static final int STATE_BAR_INGAME = 1;
    public static final String[] STATE_BAR_PREGAME_STATES = {"Crew Selection", "Crew Placement", "Journey Selection"};
    public static final String[] STATE_BAR_INGAME_STATES = {"Journey Phase", "Rest Phase", "Crew Phase"};
    
    public static final int CREW_CLASS_PER_LINE = 3;
    
    
    //Y Sizes
    public static final int STATE_BAR_Y = ((5 * WINDOW_Y) / 100);//5%
    public static final int CREWMEMBER_BAR_Y = ((7 * WINDOW_Y) / 100);//7%
    public static final int BUTTON_BAR_Y = ((6 * WINDOW_Y) / 100);//6%
    
    public static final int INTERACTION_Y = ((77 * WINDOW_Y) / 100);
    public static final int GAME_STATS_Y = (int) ( (8 * WINDOW_Y) / 100);
    
    //X Sizes
    //  Crew Selection
    public static final int CREW_CLASS_LIST_X = ((30 * WINDOW_X) / 100);//30%
    public static final int CREW_CLASS_INFO_X = ((int)((67.2 * WINDOW_X) / 100));//67.2%
    public static final int CREW_CLASS_INFO_MOVEMENT_X = ((int)((7.5 * WINDOW_X) / 100));
    public static final int CREW_CLASS_INFO_ATTACK_X = ((int)((7.5 * WINDOW_X) / 100));
    
    //  Crew Placement
    public static final int SHIP_DISPLAY_X = ((30 * WINDOW_X) / 100);
    public static final int ROOM_SETTING_X = ((70 * WINDOW_X) / 100);
    
    //  Journey Selection
    public static final int JOURNEY_DISPLAY_X = ((3 * WINDOW_X) / 100);
    public static final int JOURNEY_EDITOR_X = ((int)((64.10 * WINDOW_X) / 100));
    
    //  Crew Phase
    public static final int ACTION_SELECTION_X = JOURNEY_EDITOR_X;
    
    //Also has journey (JOURNEY_DISPLAY_X)
    //Also has ship (SHIP_DISPLAY_X)
    //Respective Editors
    
    public static final int INACTIVE = -2;
    public static final int ACTIVE = -3;
    
    public static final int INSIDE_PADDING = 10;
    
    //Colors
    public static final Color TOKEN_ALIEN = Color.rgb(24, 142, 26);
    public static final Color TOKEN_TRAP = Color.rgb(109, 40, 29);
    public static final Color BACKGROUND_COLOR = Color.rgb(37, 37, 37);
    //Normal
    public static final double NORMAL_BACKGROUND_OPACITY = 0.5;
    public static final Color NORMAL_BACKGROUND_COLOR = Color.rgb(240, 240, 240);
    public static final Color NORMAL_BACKGROUND_COLOR_O = Color.rgb(240, 240, 240, NORMAL_BACKGROUND_OPACITY);
    public static final Color NORMAL_TEXT_COLOR = Color.rgb(0, 0, 0);
    //Selectable
    public static final double SELECTABLE_BACKGROUND_OPACITY = 0.5;
    public static final Color SELECTABLE_BACKGROUND_COLOR = Color.rgb(118, 165, 242);
    public static final Color SELECTABLE_BACKGROUND_COLOR_O = Color.rgb(118, 165, 242, SELECTABLE_BACKGROUND_OPACITY);
    public static final Color SELECTABLE_TEXT_COLOR = Color.rgb(255, 255, 255);
    //Confirm Box
    public static final double CONFIRMBOX_BACKGROUND_OPACITY = 0.5;
    public static final Color CONFIRMBOX_BACKGROUND_COLOR = BACKGROUND_COLOR;
    public static final Color CONFIRMBOX_BACKGROUND_COLOR_O = Color.rgb(118, 165, 242, CONFIRMBOX_BACKGROUND_OPACITY);
    public static final Color CONFIRMBOX_TEXT_COLOR = SELECTABLE_TEXT_COLOR;
    //Log Box
    public static final double LOGBOX_BACKGROUND_OPACITY = 0.5;
    public static final Color LOGBOX_BACKGROUND_COLOR = BACKGROUND_COLOR;
    public static final Color LOGBOX_BACKGROUND_COLOR_O = Color.rgb(118, 165, 242, LOGBOX_BACKGROUND_OPACITY);
    public static final Color LOGBOX_TEXT_COLOR = SELECTABLE_TEXT_COLOR;
    
    //FirePropertyChange
    public static final String FPC_SWAP_SCENE = "SwapScene";
    public static final String FPC_CLOSE_WINDOW = "CloseWindow";
    public static final String FPC_CREW_TAB = "CrewTab";
    public static final String FPC_CLASS_SWAPED_BAR = "ClassSwaped_Bar";
    public static final String FPC_CLASS_SWAPED_INFO = "ClassSwaped_Info";
    public static final String FPC_CLASS_SWAPED_LIST = "ClassSwaped_List";
    public static final String FPC_COLOR_SWAPED = "ColorSwaped";
    public static final String FPC_DISPLAY_SHIP_UPDATE = "DisplayShipUpdate";
    public static final String FPC_JOURNEY_EVENTS_UPDATE = "JourneyEventsUpdate";
    public static final String FPC_JOURNEY_TURN_UPDATE = "JourneyTurnUpdate";
    public static final String FPC_JOURNEY_DISPLAY_UPDATE = "JourneyDisplayUpdate";
    public static final String FPC_GAME_STATS_UPDATE = "GameStatsUpdate";
    public static final String FPC_GAME_STARTED = "GameStarted";
    public static final String FPC_DISPLAY_POSSIBLE_ROOMS = "DisplayPossibleRooms";
    public static final String FPC_ACTION_SELECTION_UPDATE = "ActionSelectionUpdate";
    public static final String FPC_INSPIRATION_SELECTION_UPDATE = "InspirationSelectionUpdate";
    public static final String FPC_REPLAY_GAME = "ReplayGame";
    public static final String FPC_SHOW_LOGS = "ShowLogs";
    
    public static final int UNKNOWN = -1;
    
    public static final int AP_MOVE = 1;
    public static final int AP_ATTACK = 2;
}
