package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.GameStatsInfo;
import de.ui.gui.Scenes.Components.InspirationSelection;
import de.ui.gui.Scenes.Components.JourneyDisplay;
import de.ui.gui.Scenes.Components.MenuDisplay;
import de.ui.gui.Scenes.Components.ShipDisplay;
import de.ui.gui.Scenes.Components.StateBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RestPhase_layout extends VBox implements Constants{
    private ObservableModel observableModel;
    
    //Root Container (Everywhere)
    private VBox rootTopContainer;
    private MenuDisplay menuDisplay;
    private StateBar stateBarContainer;
    private BorderPane interactionContainer;
    
    //Top Container (Everywhere)
    private CrewBar topContainer;
    
    //Left Container
    private VBox leftContainer;
    private HBox mixContainer;
    private JourneyDisplay journeyContainer;
    private ShipDisplay shipContainer;
    
    //Right Container
    private VBox rightContainer;
    private GameStatsInfo gameStatsInfo;
    private InspirationSelection inspitationSelection;
    
    //Bottom Container
    private HBox buttonBar;
        private Button quitBtn;
        private Button lockInBtn;
    
    public RestPhase_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setId("background-image");
        
        rootTopContainer = new VBox();
        
        if(SHOW_MENU){
            menuDisplay = new MenuDisplay(observableModel);
            rootTopContainer.getChildren().add(menuDisplay);
        }
        
        stateBarContainer = new StateBar(STATE_BAR_INGAME, SCENE_REST_PHASE);
        rootTopContainer.getChildren().add(stateBarContainer);
        interactionContainer = new BorderPane();
        interactionContainer.setPadding(new Insets(INSIDE_PADDING));
        
        getChildren().addAll(rootTopContainer, interactionContainer);
        
        initInteractionContainer();
    }
    
    private void initInteractionContainer(){
        //Top (Crew Bar)
        topContainer = new CrewBar(observableModel);
        
        interactionContainer.setTop(topContainer);
        
        //Left (Class List)
        leftContainer = new VBox();
        leftContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, 0));
        
        mixContainer = new HBox();
        journeyContainer = new JourneyDisplay(observableModel, false);
        shipContainer = new ShipDisplay(observableModel, false, SCENE_REST_PHASE);
        
        mixContainer.getChildren().addAll(journeyContainer, shipContainer);
        
        leftContainer.getChildren().addAll(mixContainer);
        
        interactionContainer.setLeft(leftContainer);
        
        //Right (Class Info + Buttons)
        rightContainer = new VBox(INSIDE_PADDING);
        rightContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, INSIDE_PADDING));
        
        gameStatsInfo = new GameStatsInfo(observableModel);
        inspitationSelection = new InspirationSelection(observableModel);
        
        rightContainer.getChildren().addAll(gameStatsInfo, inspitationSelection);
        
        interactionContainer.setRight(rightContainer);
        
        //Bottom
        
        buttonBar = new HBox(INSIDE_PADDING);
        quitBtn = new Button("Quit");
        lockInBtn = new Button("Lock In");
        
        setButtonHandles();

        buttonBar.setPrefHeight(BUTTON_BAR_Y);
        buttonBar.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBar.getChildren().addAll(quitBtn, lockInBtn);
        
        interactionContainer.setBottom(buttonBar);
    }
    
    
    private void setButtonHandles(){
        quitBtn.setOnAction(e -> {
            observableModel.closeWindow();
        });
        
        lockInBtn.setOnAction(e -> {
            observableModel.lockIn();
        });
    }
}
