package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ActionSelection extends VBox implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    
    private Label mainTitle;
    
    private Label moveTitle;
    private Label moveText;
    private Button cancelBtn;
    
    private HashMap<Integer, Button> actions;
    
    public ActionSelection(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(NORMAL_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setPadding(new Insets(INSIDE_PADDING));
        setSpacing(INSIDE_PADDING);
        
        setPrefWidth(ACTION_SELECTION_X);
        setPrefHeight(INTERACTION_Y);
        
        setPropertyChangeListeners();
        
        initCrewClassInfo();
    }
    
    private void setPropertyChangeListeners(){
        observableModel.addPropertyChangeListener(FPC_GAME_STARTED, this);
        observableModel.addPropertyChangeListener(FPC_AP_AVAILABLE_UPDATE, this);
        observableModel.addPropertyChangeListener(FPC_ACTION_SELECTION_UPDATE, this);
    }
    
    private void initCrewClassInfo(){
        mainTitle = new Label("Actions: ");
        getChildren().add(mainTitle);
        
        actions = new HashMap<>();
        
        moveTitle = new Label("Move Crew Member: ");
        moveText = new Label("To move a crew member choose a room on the ship display!");
        cancelBtn = new Button("Cancel");
    }
    
    private void processActions(){
        int i;
        
        //Defaults
        for(i = 0; i < DEF_ACTIONS.length; i++){
            Button actionBtn = new Button(DEF_ACTIONS_COST[i] + "AP " + DEF_ACTIONS[i]);
            actionBtn.setId("DEFAULT");
            actions.put(i, actionBtn);
            getChildren().add(actions.get(i));
        }
        
        //Specials
        if(observableModel.have_Doctor()){
            Button actionBtn = new Button(DEF_COST_A_HEAL + "AP " + DEF_ACTION_HEAL);
            actionBtn.setId("DOCTOR");
            actionBtn.setOnAction(e -> {
                observableModel.AP_healPlayer();
            });
            actions.put(i, actionBtn);
            getChildren().add(actions.get(i));
            i++;
        }
        
        if(observableModel.have_Engineer()){
            Button actionBtn = new Button(DEF_COST_A_FIX_HULL + "AP " + DEF_ACTION_FIX_HULL);
            actionBtn.setId("ENGINEER");
            actionBtn.setOnAction(e -> {
                observableModel.AP_fixHull();
            });
            actions.put(i, actionBtn);
            getChildren().add(actions.get(i));
            i++;
        }
    }

    private void setComponentsHandlers(){
        for(int i = 0; i < DEF_ACTIONS.length; i++){
            final int fi = i;
            actions.get(i).setOnAction(e -> {
                switch(fi){
                    case 0:
                        observableModel.AP_moveCrewMember(0);
                        break;
                    case 1:
                        observableModel.AP_attackAlien();
                        break;
                    case 2:
                        observableModel.AP_placeTrap();
                        break;
                    case 3:
                        observableModel.AP_detonateParticleDispenser();
                        break;
                    case 4:
                        observableModel.AP_sealRoom();
                        break;
                }
            });
        }
        
        cancelBtn.setOnAction(e -> {
            observableModel.cancelAction();
        });
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case FPC_GAME_STARTED:
                processActions();
                setComponentsHandlers();
                break;
            case FPC_ACTION_SELECTION_UPDATE:
                switch(observableModel.currentState()){
                    case STATE_CREW_PHASE:
                        getChildren().clear();
                        getChildren().add(mainTitle);
                        for(int i = 0; i < actions.size(); i++){
                            if(actions.get(i).getId().equals("DEFAULT")){
                                if(DEF_ACTIONS_COST[i] > observableModel.getActionPoints())
                                    actions.get(i).setDisable(true);
                                else
                                    actions.get(i).setDisable(false);
                                getChildren().add(actions.get(i));
                            }else{
                                if(actions.get(i).getId().equals("DOCTOR") && observableModel.activeIsDoctor()){
                                    if(DEF_COST_A_HEAL > observableModel.getActionPoints() || observableModel.getHealthTracker() >= MAX_HEALTH)
                                        actions.get(i).setDisable(true);
                                    else
                                        actions.get(i).setDisable(false);
                                    getChildren().add(actions.get(i));
                                }else if(actions.get(i).getId().equals("ENGINEER") && observableModel.activeIsEngineer()){
                                    if(DEF_COST_A_FIX_HULL > observableModel.getActionPoints() || observableModel.getHullTracker() >= MAX_HULL)
                                        actions.get(i).setDisable(true);
                                    else
                                        actions.get(i).setDisable(false);
                                    getChildren().add(actions.get(i));
                                }
                            }
                        }
                        break;
                    case STATE_MOVE_CREW_MEMBER:
                        getChildren().clear();
                        getChildren().addAll(moveTitle, moveText, cancelBtn);
                }
                break;
        }
    }
}