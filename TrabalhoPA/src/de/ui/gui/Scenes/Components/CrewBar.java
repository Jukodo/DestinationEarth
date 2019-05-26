package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.ui.gui.ObservableModel;
import java.util.HashMap;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CrewBar extends HBox implements Constants{
    private ObservableModel observableModel;
    private HashMap<Integer, HBox> crewMembers;
    private CrewMemberTab crewMemberInfo;
    
    public CrewBar(ObservableModel observableModel){
        this.observableModel = observableModel;
        crewMembers = new HashMap<>();
        initCrewMembersContainers();
    }
    
    private void initCrewMembersContainers(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            crewMembers.put(i, new HBox());
            processCrewMemberInfo(i);
            getChildren().add(crewMembers.get(i));
        }
    }
    
    private void processCrewMemberInfo(int index){
        crewMemberInfo = new CrewMemberTab(observableModel, index);
        if(index == 0)
            crewMemberInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        else
            crewMemberInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 1, 1, 0))));
        crewMembers.get(index).getChildren().add(crewMemberInfo);
    }
}
