package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CrewClassInfo extends VBox implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    
    private Label title;
    
    private Label movementLabel;
    private HBox movement;
    private HashMap<Integer, Label> movs;
    
    private Label attackLabel;
    private HBox attack;
    private HashMap<Integer, Label> atks;
    
    private Label specialsLabel;
    private Label specials;
    
    private Label colorLabel;
    private ColorPicker colorPicker;
    
    public CrewClassInfo(ObservableModel observableModel) {
        this.observableModel = observableModel;
        observableModel.addPropertyChangeListener(FPC_CLASS_SWAPED_INFO, this);
        
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setPadding(new Insets(INSIDE_PADDING));
        setSpacing(INSIDE_PADDING);
        
        setPrefWidth(CREW_CLASS_INFO_X);
        setPrefHeight(INTERACTION_Y);
        
        initCrewClassInfo();
    }
    
    private void initCrewClassInfo(){
        title = new Label();
        movementLabel = new Label("Movement");
        attackLabel = new Label("Attack");
        specialsLabel = new Label("Specials");
        specials = new Label();
        colorLabel = new Label("Color");
        colorPicker = new ColorPicker();
        colorPicker.setPadding(new Insets(0));
        
        processMovement();
        processAttack();
        setComponentsHandlers();
        
        getChildren().addAll(title, movementLabel, movement, attackLabel, attack, specialsLabel, specials, colorLabel, colorPicker);
    }
    
    private void processMovement(){
        movement = new HBox();
        movement.setMaxWidth(CREW_CLASS_INFO_MOVEMENT_X);
        movement.setMaxHeight(CREW_CLASS_INFO_MOVEMENT_X/3);
        
        movs = new HashMap<>();
        for(int i = 1; i <= 3; i++){
            Label mov = new Label(Integer.toString(i));
            mov.setPrefWidth(CREW_CLASS_INFO_MOVEMENT_X/3);
            mov.setPrefHeight(CREW_CLASS_INFO_MOVEMENT_X/3);
            mov.setAlignment(Pos.CENTER);
            mov.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            
            if(i == 1)
                mov.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            else
                mov.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 1, 1, 0))));
            
            movs.put(i, mov);
            
            movement.getChildren().add(mov);
        }
    }
    
    private void processAttack(){
        attack = new HBox();
        attack.setPrefWidth(CREW_CLASS_INFO_ATTACK_X);
        attack.setPrefHeight(CREW_CLASS_INFO_ATTACK_X/3);
        
        atks = new HashMap<>();
        for(int i = 1; i <= 3; i++){
            Label atk = new Label(Integer.toString(i));
            atk.setPrefWidth(CREW_CLASS_INFO_ATTACK_X/3);
            atk.setPrefHeight(CREW_CLASS_INFO_ATTACK_X/3);
            atk.setAlignment(Pos.CENTER);
            atk.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            
            if(i == 1)
                atk.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            else
                atk.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 1, 1, 0))));
            
            atks.put(i, atk);
            
            attack.getChildren().add(atk);
        }
    }

    private void setComponentsHandlers(){
        colorPicker.setOnAction(e -> {
            observableModel.changeCrewMemberColor(colorPicker.getValue());
        });
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        title.setText(CREWMEMBER_TYPES[Integer.parseInt(evt.getOldValue().toString())-1]);
        
        for(int i = 1; i <= 3; i++){
            if(i == CREWMEMBER_STATS[Integer.parseInt(evt.getOldValue().toString())-1][STATS_MOV]){
                movs.get(i).setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
                movs.get(i).setTextFill(Color.WHITE);
            }else{
                movs.get(i).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                movs.get(i).setTextFill(BACKGROUND_COLOR);
            }
            
            if(i == CREWMEMBER_STATS[Integer.parseInt(evt.getOldValue().toString())-1][STATS_ATK]){
                atks.get(i).setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
                atks.get(i).setTextFill(Color.WHITE);
            }else{
                atks.get(i).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                atks.get(i).setTextFill(BACKGROUND_COLOR);
            }
        }
        specials.setText(CREWMEMBER_SPECIALS[Integer.parseInt(evt.getOldValue().toString())-1]);
    }
}
