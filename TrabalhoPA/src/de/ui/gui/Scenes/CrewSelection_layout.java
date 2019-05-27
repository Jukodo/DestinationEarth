package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.CrewClassList;
import de.ui.gui.Scenes.Components.CrewClassInfo;
import de.ui.gui.Scenes.Components.StateBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CrewSelection_layout extends VBox implements Constants{
    private ObservableModel observableModel;
    
    //Root Container (Everywhere)
    private StateBar stateBarContainer;
    private BorderPane interactionContainer;
    
    //Top Container (Everywhere)
    private CrewBar topContainer;
    
    //Left Container (CrewSelection only)
    private VBox leftContainer;
    private CrewClassList crewContainer;
    
    //Right Container
    private VBox rightContainer;
    private CrewClassInfo infoContainer;
    
    //Bottom Container
    private HBox buttonContainer;
        private Button quitBtn;
        private Button lockInBtn;
    
    public CrewSelection_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        
        stateBarContainer = new StateBar(STATE_BAR_PREGAME, SCENE_CREWSELECTION);
        interactionContainer = new BorderPane();
        interactionContainer.setPadding(new Insets(INSIDE_PADDING));
        
        getChildren().addAll(stateBarContainer, interactionContainer);
        
        initInteractionContainer();
    }
    
    private void initInteractionContainer(){
        //Top (Crew Bar)
        topContainer = new CrewBar(observableModel);
        
        interactionContainer.setTop(topContainer);
        
        //Left (Class List)
        leftContainer = new VBox();
        leftContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, 0));
        
        crewContainer = new CrewClassList(observableModel);
        
        leftContainer.getChildren().addAll(crewContainer);
        
        interactionContainer.setLeft(leftContainer);
        
        //Right (Class Info + Buttons)
        rightContainer = new VBox();
        
        rightContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, INSIDE_PADDING));
        rightContainer.setPrefWidth(((68 * WINDOW_X) / 100));
        rightContainer.setPrefHeight(WINDOW_Y);
        
        infoContainer = new CrewClassInfo(observableModel);
        
        rightContainer.getChildren().addAll(infoContainer);
        
        interactionContainer.setRight(rightContainer);
        
        //Bottom
        
        buttonContainer = new HBox(INSIDE_PADDING);
        quitBtn = new Button("Quit");
        lockInBtn = new Button("Lock In");
        
        setButtonHandles();

        buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
        buttonContainer.getChildren().addAll(quitBtn, lockInBtn);
        
        interactionContainer.setBottom(buttonContainer);
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
