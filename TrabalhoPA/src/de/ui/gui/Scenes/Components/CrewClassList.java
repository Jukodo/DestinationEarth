package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CrewClassList extends GridPane implements Constants{
    private ObservableModel observableModel;
    private HashMap<Integer, CrewClass> crewMemberTypes;
    
    public CrewClassList(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        setPrefHeight(INTERACTION_Y);
        setPrefWidth(CREW_CLASS_LIST_X);
        
        initCrewClassList();
    }
    
    private void initCrewClassList(){
        crewMemberTypes = new HashMap<>();
        
        for(int i = 1; i <= CREWMEMBER_TYPES.length; i++){
            crewMemberTypes.put(i, new CrewClass(observableModel, i));
            processCrewMemberTypes(i);
        }
    }
    
    private void processCrewMemberTypes(int index){
        GridPane.setConstraints(crewMemberTypes.get(index), (index-1) % CREW_CLASS_PER_LINE
                                                          , Math.floorDiv((index-1), CREW_CLASS_PER_LINE/*3 elements per line*/));
        
        getChildren().add(crewMemberTypes.get(index));
    }
    
    
}
