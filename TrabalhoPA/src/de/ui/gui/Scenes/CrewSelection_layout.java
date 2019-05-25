package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.ui.gui.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.CrewMemberType;
import de.ui.gui.Scenes.Components.StateBar;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;

public class CrewSelection_layout extends BorderPane implements Constants{
    private ObservableModel observableModel;
    
    //Top Container
    private VBox topContainer;
    private StateBar stateBarContainer;
    private CrewBar crewBarContainer;
    
    //Left Container
    private GridPane leftContainer;
    private HashMap<Integer, CrewMemberType> crewMemberTypes;
    
    public CrewSelection_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        crewMemberTypes = new HashMap<>();
        initComponents();
    }
    
    private void initComponents(){
        this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        //Top
        topContainer = new VBox();
        System.out.println("CrewPhase_layout initComponents");
        stateBarContainer = new StateBar(STATE_BAR_PREGAME, SCENE_CREWSELECTION);
        crewBarContainer = new CrewBar(observableModel);
        crewBarContainer.setPadding(new Insets(INSIDE_PADDING));
        topContainer.getChildren().addAll(stateBarContainer, crewBarContainer);
        setTop(topContainer);
        
        //Left
        leftContainer = new GridPane();
        for(int i = 0; i < CREWMEMBER_TYPES.length; i++){
            crewMemberTypes.put(i, new CrewMemberType(i));
            processCrewMemberTypes(i);
        }
        leftContainer.setPadding(new Insets(0, INSIDE_PADDING, INSIDE_PADDING, INSIDE_PADDING));
        setLeft(leftContainer);
    }
    
    private void processCrewMemberTypes(int index){
        GridPane.setConstraints(crewMemberTypes.get(index), index % CREWMEMBER_TYPES_PER_LINE
                                                          , Math.floorDiv(index, CREWMEMBER_TYPES_PER_LINE/*3 elements per line*/));
        crewMemberTypes.get(index).setPrefWidth((CREWMEMBER_TYPES_MAX_X / CREWMEMBER_TYPES_PER_LINE));
        crewMemberTypes.get(index).setAlignment(Pos.CENTER);
        crewMemberTypes.get(index).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        crewMemberTypes.get(index).setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        crewMemberTypes.get(index).setOnMouseClicked(e -> {
            System.out.println("Clicked on " + CREWMEMBER_TYPES[index]);
        });
        leftContainer.getChildren().add(crewMemberTypes.get(index));
    }
}
