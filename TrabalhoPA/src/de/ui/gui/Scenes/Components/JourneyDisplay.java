package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class JourneyDisplay extends VBox implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    private String[] journeyTracker;
    private HashMap<Integer, VBox> journeyEvents;
    private VBox journeyEvent;
    private final boolean interactable;
    
    public JourneyDisplay(ObservableModel observableModel, boolean interactable){
        this.observableModel = observableModel;
        this.interactable = interactable;
        
        setPrefHeight(INTERACTION_Y);
        
        setPropertyChangeListeners();
        
        initJourneyEvents();
    }
    
    private void initJourneyEvents(){
        journeyEvents = new HashMap<>();
        
        for(int i = 0; i < NUM_TURNS+2; i++){
            processJourneyEvents(i);
            journeyEvents.put(i, journeyEvent);
            getChildren().add(journeyEvent);
        }
        
        if(interactable)
            setComponentsHandlers();
    }
    
    private void processJourneyEvents(int index){
        journeyEvent = new VBox();
        journeyEvent.setPrefSize(JOURNEY_DISPLAY_X, INTERACTION_Y/(NUM_TURNS+2));
        journeyEvent.setAlignment(Pos.CENTER);
        
        if(interactable && index == 1)
            journeyEvent.setBackground(new Background(new BackgroundFill(SELECTABLE_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        else
            journeyEvent.setBackground(new Background(new BackgroundFill(NORMAL_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        
        if(index == 0)
            journeyEvent.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 1, 1))));
        else
            journeyEvent.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 1, 1))));
        
        Label event = new Label();
        
        if(index == 0)
            event.setText("S");
        else if(index == NUM_TURNS+1)
            event.setText("E");
        else
            event.setText(index + "");
        
        journeyEvent.getChildren().add(event);
    }
    
    private void setComponentsHandlers(){
        for(int i = 1; i <= NUM_TURNS; i++){
            final int fi = i;
            journeyEvents.get(i).setOnMouseClicked(e -> {
                observableModel.swapActiveJourneyTurn(fi);
            });
        }
    }
    
    private void setPropertyChangeListeners(){
        if(interactable)
            observableModel.addPropertyChangeListener(FPC_JOURNEY_DISPLAY, this);
        observableModel.addPropertyChangeListener(FPC_JOURNEY_UPDATE_EVENTS, this);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(FPC_JOURNEY_DISPLAY)){
            for(int i = 1; i <= journeyEvents.size()-2; i++){
                if((int) evt.getOldValue() == i){
                    ((VBox) journeyEvents.get(i)).setBackground(new Background(new BackgroundFill(SELECTABLE_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
                }else{
                    ((VBox) journeyEvents.get(i)).setBackground(new Background(new BackgroundFill(NORMAL_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }else if(evt.getPropertyName().equals(FPC_JOURNEY_UPDATE_EVENTS)){
            Node label;
            journeyTracker = observableModel.getJourneyTracker();

            for(int i = 1; i <= NUM_TURNS; i++){
                label = journeyEvents.get(i).getChildren().get(0);

                if(label instanceof Label)
                    ((Label) label).setText(journeyTracker[i-1]);
            }
        }
    }
}
