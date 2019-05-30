package de.ui.gui.Scenes;

import de.logic.data.Constants;
import static de.logic.data.Constants.BUTTON_BAR_Y;
import static de.logic.data.Constants.INSIDE_PADDING;
import de.logic.data.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.CrewClassInfo;
import de.ui.gui.Scenes.Components.JourneyDisplay;
import de.ui.gui.Scenes.Components.ShipDisplay;
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

public class JourneySelection_layout extends VBox implements Constants{
    private ObservableModel observableModel;
    
    //Root Container (Everywhere)
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
    private CrewClassInfo classInfoContainer;
    
    //Bottom Container
    private HBox buttonBar;
        private Button quitBtn;
        private Button lockInBtn;
    
    public JourneySelection_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        
        stateBarContainer = new StateBar(STATE_BAR_PREGAME, SCENE_JOURNEYSELECTION);
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
        leftContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        
        leftContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, 0));
        
        mixContainer = new HBox();
        journeyContainer = new JourneyDisplay(observableModel, true);
        shipContainer = new ShipDisplay(observableModel, false);
        
        mixContainer.getChildren().addAll(journeyContainer, shipContainer);
        
        leftContainer.getChildren().addAll(mixContainer);
        
        interactionContainer.setLeft(leftContainer);
        
        //Right (Class Info + Buttons)
        rightContainer = new VBox();
        rightContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        
        rightContainer.setPadding(new Insets(INSIDE_PADDING, 0, 0, INSIDE_PADDING));
        
        classInfoContainer = new CrewClassInfo(observableModel);
        classInfoContainer.setPrefWidth(CREW_CLASS_INFO_X_);
        
        rightContainer.getChildren().addAll(classInfoContainer);
        
        interactionContainer.setRight(rightContainer);
        
        //Bottom
        
        buttonBar = new HBox(INSIDE_PADDING);
        buttonBar.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
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
            observableModel.swapScene(SCENE_JOURNEYPHASE);
        });
    }
}
