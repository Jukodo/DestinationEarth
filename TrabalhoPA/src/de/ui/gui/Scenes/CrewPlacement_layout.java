package de.ui.gui.Scenes;

import de.logic.data.Constants;
import static de.logic.data.Constants.INSIDE_PADDING;
import static de.logic.data.Constants.SCENE_CREW_SELECTION;
import static de.logic.data.Constants.SHOW_MENU;
import static de.logic.data.Constants.STATE_BAR_PREGAME;
import de.logic.data.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.MenuDisplay;
import de.ui.gui.Scenes.Components.ShipDisplay;
import de.ui.gui.Scenes.Components.StateBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CrewPlacement_layout extends VBox implements Constants{
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
    private ShipDisplay shipContainer;
    
    //Bottom Container
    private HBox buttonBar;
        private Button quitBtn;
        private Button lockInBtn;
    
    public CrewPlacement_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setId("background-image");
        
        rootTopContainer = new VBox();
        
        if(SHOW_MENU){
            System.out.println("SHOWING MENU");
            menuDisplay = new MenuDisplay(observableModel);
            rootTopContainer.getChildren().add(menuDisplay);
        }
        
        stateBarContainer = new StateBar(STATE_BAR_PREGAME, SCENE_CREW_PLACEMENT);
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
        
        shipContainer = new ShipDisplay(observableModel, true, SCENE_CREW_PLACEMENT);
        
        leftContainer.getChildren().addAll(shipContainer);
        
        interactionContainer.setLeft(leftContainer);
        
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
