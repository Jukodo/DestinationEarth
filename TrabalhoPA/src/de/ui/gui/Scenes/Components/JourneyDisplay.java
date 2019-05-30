package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
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

public class JourneyDisplay extends VBox implements Constants{
    private ObservableModel observableModel;
    private String[] journeyTracker;
    private HashMap<Integer, VBox> journeyEvents;
    private VBox journeyEvent;
    
    public JourneyDisplay(ObservableModel observableModel){
        this.observableModel = observableModel;
        
        setPrefHeight(INTERACTION_Y);
        
        initJourneyEvents();
    }
    
    private void initJourneyEvents(){
        journeyEvents = new HashMap<>();
        
        for(int i = 0; i < NUM_TURNS+2; i++){
            processJourneyEvents(i);
            journeyEvents.put(i, journeyEvent);
            getChildren().add(journeyEvent);
        }
    }
    
    private void processJourneyEvents(int index){
        journeyEvent = new VBox();
        journeyEvent.setPrefSize(JOURNEY_DISPLAY_X, INTERACTION_Y/(NUM_TURNS+2));
        journeyEvent.setAlignment(Pos.CENTER);
        journeyEvent.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        if(index == 0)
            journeyEvent.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        else
            journeyEvent.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 1, 1, 1))));
        
        Label event = new Label();
        
        if(index == 0)
            event.setText("S");
        else if(index == NUM_TURNS+1)
            event.setText("E");
        else
            event.setText(index + "");
        
        journeyEvent.getChildren().add(event);
    }
    
    private void updateJourneyEvents(){
        Node label;
        journeyTracker = observableModel.getJourneyTracker();
        
        for(int i = 0; i < NUM_TURNS; i++){
            label = journeyEvents.get(i).getChildren().get(0);
            
            if(label instanceof Label && (i != 0 && i != NUM_TURNS + 1))
                ((Label) label).setText(journeyTracker[i-1]);
        }
    }
}
