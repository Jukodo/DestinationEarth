package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.JourneyDisplay;
import de.ui.gui.Scenes.Components.JourneyEditor;
import de.ui.gui.Scenes.Components.MenuDisplay;
import de.ui.gui.Scenes.Components.ShipDisplay;
import de.ui.gui.Scenes.Components.StateBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JourneySelection_layout extends VBox implements Constants{
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
    private JourneyDisplay journeyDisplay;
    private ShipDisplay shipDisplay;
    
    //Right Container
    private VBox rightContainer;
    private JourneyEditor journeyEditor;
    
    //Bottom Container
    private HBox buttonBar;
        private Button quitBtn;
        private Button lockInBtn;
    
    public JourneySelection_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setId("background-image");
        
        rootTopContainer = new VBox();
        
        if(SHOW_MENU){
            System.out.println("SHOWING MENU");
            menuDisplay = new MenuDisplay(observableModel);
            rootTopContainer.getChildren().add(menuDisplay);
        }
        
        stateBarContainer = new StateBar(STATE_BAR_PREGAME, SCENE_JOURNEY_SELECTION);
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
        journeyDisplay = new JourneyDisplay(observableModel, true);
        shipDisplay = new ShipDisplay(observableModel, false, SCENE_JOURNEY_SELECTION);
        
        mixContainer.getChildren().addAll(journeyDisplay, shipDisplay);
        
        leftContainer.getChildren().addAll(mixContainer);
        
        interactionContainer.setLeft(leftContainer);
        
        //Right (Class Info + Buttons)
        rightContainer = new VBox();
        rightContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, INSIDE_PADDING));
        
        journeyEditor = new JourneyEditor(observableModel);
        rightContainer.getChildren().add(journeyEditor);
        
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
