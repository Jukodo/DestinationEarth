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

public class InspirationSelection extends VBox implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    
    private Label mainTitle;
    
    private HashMap<Integer, Button> inspirations;
    
    public InspirationSelection(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(NORMAL_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setPadding(new Insets(INSIDE_PADDING));
        setSpacing(INSIDE_PADDING);
        
        setPrefWidth(ACTION_SELECTION_X);
        setPrefHeight(INTERACTION_Y);
        
        setPropertyChangeListeners();
        
        initInspirationSelection();
    }
    
    private void setPropertyChangeListeners(){
        observableModel.addPropertyChangeListener(FPC_GAME_STARTED, this);
        observableModel.addPropertyChangeListener(FPC_INSPIRATION_SELECTION_UPDATE, this);
    }
    
    private void initInspirationSelection(){
        mainTitle = new Label("Actions: ");
        getChildren().add(mainTitle);
        
        inspirations = new HashMap<>();
    }
    
    private void processInspirations(){
        int i;
        
        //Defaults
        for(i = 0; i < DEF_INSPIRATIONS.length; i++){
            Button inspirationBtn = new Button(DEF_INSPIRATIONS_COST[i] + "IP " + DEF_INSPIRATIONS[i]);
            if(DEF_INSPIRATIONS_COST[i] > observableModel.getInspirationPoints())
                inspirationBtn.setDisable(true);
            else
                inspirationBtn.setDisable(false);
            inspirationBtn.setId("DEFAULT");
            inspirations.put(i, inspirationBtn);
            getChildren().add(inspirations.get(i));
        }
        
        //Specials
        if(observableModel.have_RedShirt(false)){
            Button inspirationBtn = new Button(DEF_COST_REDSHIRT_SACRIFICE+ "IP " + DEF_REDSHIRT_SACRIFICE);
            inspirationBtn.setId("REDSHIRT");
            inspirationBtn.setOnAction(e -> {
                observableModel.sacrificeCrewMember();
            });
            inspirations.put(i, inspirationBtn);
            getChildren().add(inspirations.get(i));
            i++;
        }
    }

    private void setComponentsHandlers(){
        for(int i = 0; i < DEF_INSPIRATIONS.length; i++){
            final int fi = i;
            inspirations.get(i).setOnAction(e -> {
                switch(fi){
                    case 0:
                        observableModel.IP_addHealthPoint();
                        break;
                    case 1:
                        observableModel.IP_repairHull();
                        break;
                    case 2:
                        observableModel.IP_buildOrganicDetonator();
                        break;
                    case 3:
                        observableModel.IP_addMovement();
                        break;
                    case 4:
                        observableModel.IP_buildParticleDesperser();
                        break;
                    case 5:
                        observableModel.IP_addSealedRoomToken();
                        break;
                    case 6:
                        observableModel.IP_addAttackDie();
                        break;
                    case 7:
                        observableModel.IP_addValueToAttackDie();
                        break;
                }
            });
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case FPC_GAME_STARTED:
                processInspirations();
                setComponentsHandlers();
                break;
            case FPC_INSPIRATION_SELECTION_UPDATE:
                switch(observableModel.currentState()){
                    case STATE_REST_PHASE:
                        getChildren().clear();
                        getChildren().add(mainTitle);
                        for(int i = 0; i < inspirations.size(); i++){
                            if(inspirations.get(i).getId().equals("DEFAULT")){
                                if(DEF_INSPIRATIONS_COST[i] > observableModel.getInspirationPoints())
                                    inspirations.get(i).setDisable(true);
                                else
                                    inspirations.get(i).setDisable(false);
                                getChildren().add(inspirations.get(i));
                            }else if(inspirations.get(i).getId().equals("REDSHIRT")){
                                if(!observableModel.have_RedShirt(true))
                                    inspirations.get(i).setDisable(true);
                                else
                                    inspirations.get(i).setDisable(false);
                                getChildren().add(inspirations.get(i));
                            }
                        }
                        break;
                }
                break;
        }
    }
}