package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.ui.gui.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.CrewMemberType;
import de.ui.gui.Scenes.Components.CrewMemberTypeInfo;
import de.ui.gui.Scenes.Components.StateBar;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CrewSelection_layout extends VBox implements Constants{
    private ObservableModel observableModel;
    
    //Root Container
    private StateBar stateBarContainer;
    private BorderPane interactionContainer;
    
    //Top Container
    private CrewBar topContainer;
    
    //Left Container
    private GridPane leftContainer;
    private HashMap<Integer, CrewMemberType> crewMemberTypes;
    
    //Right Container
    private VBox rightContainer;
    private CrewMemberTypeInfo infoContainer;
    private HBox buttonContainer;
        private Button quitBtn;
        private Button lockInBtn;
    
    public CrewSelection_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        crewMemberTypes = new HashMap<>();
        
        setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        
        stateBarContainer = new StateBar(STATE_BAR_PREGAME, SCENE_CREWSELECTION);
        interactionContainer = new BorderPane();
        interactionContainer.setPadding(new Insets(INSIDE_PADDING));
        
        getChildren().addAll(stateBarContainer, interactionContainer);
        
        initInteractionContainer();
    }
    
    private void initInteractionContainer(){
        //Top
        topContainer = new CrewBar(observableModel);
        
        interactionContainer.setTop(topContainer);
        
        //Left
        leftContainer = new GridPane();
        
        leftContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, 0));
        leftContainer.setMinWidth(CREWMEMBER_TYPES_X);
        
        for(int i = 0; i < CREWMEMBER_TYPES.length; i++){
            crewMemberTypes.put(i, new CrewMemberType(i));
            processCrewMemberTypes(i);
        }
        
        interactionContainer.setLeft(leftContainer);
        
        //Right
        rightContainer = new VBox();
        
        rightContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, INSIDE_PADDING));
        rightContainer.setAlignment(Pos.TOP_RIGHT);
        rightContainer.setPrefWidth(((68 * WINDOW_X) / 100));
        rightContainer.setPrefHeight(WINDOW_Y);
        
        infoContainer = new CrewMemberTypeInfo(observableModel);
        buttonContainer = new HBox();
        quitBtn = new Button("Quit");
        lockInBtn = new Button("Lock In");
        
        setButtonHandles();

        buttonContainer.setSpacing(INSIDE_PADDING);
        buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
        buttonContainer.getChildren().addAll(quitBtn, lockInBtn);
        
        Pane spacer = new Pane();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        
        rightContainer.getChildren().addAll(infoContainer, spacer, buttonContainer);
        
        interactionContainer.setRight(rightContainer);
    }
    
    private void processCrewMemberTypes(int index){
        GridPane.setConstraints(crewMemberTypes.get(index), index % CREWMEMBER_TYPES_PER_LINE
                                                          , Math.floorDiv(index, CREWMEMBER_TYPES_PER_LINE/*3 elements per line*/));
        
        crewMemberTypes.get(index).setOnMouseClicked(e -> {
            System.out.println("Clicked on " + CREWMEMBER_TYPES[index]);
        });
        
        leftContainer.getChildren().add(crewMemberTypes.get(index));
    }
    
    private void setButtonHandles(){
        quitBtn.setOnAction(e -> {
            observableModel.closeWindow();
        });
        
        lockInBtn.setOnAction(e -> {
            observableModel.swapScene(SCENE_CREWPLACEMENT);
        });
    }
}
