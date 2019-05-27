package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class JourneyBar extends VBox implements Constants{
    private ObservableModel observableModel;
    private String[] journeyTracker;
    private HashMap<Integer, VBox> journeyEvents;
    
    public JourneyBar(ObservableModel observableModel){
        this.observableModel = observableModel;
        
        initJourneyEvents();
    }
    
    private void initJourneyEvents(){
        journeyTracker = observableModel.getJourneyTracker();
        VBox journeyEvent;
        
        for(int i = 0; i < NUM_TURNS; i++){
            journeyEvent = new VBox();
            journeyEvent.getChildren().add(new Label(journeyTracker[i]));
            journeyEvents.put(i, journeyEvent);
        }
    }
}
