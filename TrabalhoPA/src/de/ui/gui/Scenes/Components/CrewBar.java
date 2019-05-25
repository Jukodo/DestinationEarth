package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import static de.logic.data.Constants.WINDOW_X;
import de.ui.gui.ObservableModel;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
    private Label something;//ToDo
    
    public CrewBar(ObservableModel observableModel){
        this.observableModel = observableModel;
        crewMembers = new HashMap<>();
        initCrewMembersContainers();
    }
    
    private void initCrewMembersContainers(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            System.out.println("CrewBar added 1 member");
            crewMembers.put(i, new HBox());
            processCrewMemberInfo(i);
            getChildren().add(crewMembers.get(i));
        }
    }
    
    private void processCrewMemberInfo(int index){
        something = new Label("Crew Member " + (index+1));
        something.setMinWidth((WINDOW_X / NUM_CREW_MEMBERS) - INSIDE_PADDING/*Padding Compensation*/ - (2*NUM_CREW_MEMBERS)/*Border Compensation*/);
        something.setMinHeight(CREWMEMBER_BAR_Y);
        something.setAlignment(Pos.CENTER);
        something.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        something.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        crewMembers.get(index).getChildren().add(something);
    }
}
