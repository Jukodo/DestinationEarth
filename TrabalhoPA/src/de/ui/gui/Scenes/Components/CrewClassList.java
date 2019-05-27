package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.util.HashMap;
import javafx.scene.layout.GridPane;

public class CrewClassList extends GridPane implements Constants{
    private ObservableModel observableModel;
    private HashMap<Integer, CrewClass> crewMemberTypes;
    
    public CrewClassList(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        initCrewClassList();
    }
    
    private void initCrewClassList(){
        crewMemberTypes = new HashMap<>();
        
        for(int i = 0; i < CREWMEMBER_TYPES.length; i++){
            crewMemberTypes.put(i, new CrewClass(i));
            processCrewMemberTypes(i);
        }
    }
    
    private void processCrewMemberTypes(int index){
        GridPane.setConstraints(crewMemberTypes.get(index), index % CREWMEMBER_TYPES_PER_LINE
                                                          , Math.floorDiv(index, CREWMEMBER_TYPES_PER_LINE/*3 elements per line*/));
        
        crewMemberTypes.get(index).setOnMouseClicked(e -> {
            System.out.println("Clicked on " + CREWMEMBER_TYPES[index]);
        });
        
        getChildren().add(crewMemberTypes.get(index));
    }
    
    
}
