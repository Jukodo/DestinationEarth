package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameStatsInfo extends VBox implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    
    //Top line
    private HBox topContainer;
    
    private Label playerNameLabel;
    private Label playerName;
    
    private Label turnLabel;
    private Label turn;
    
    private Label totalAliensLabel;
    private Label totalAliens;
    
    //Bottom line
    private HBox bottomContainer;
    
    private Label actionPointsLabel;
    private Label actionPoints;
    
    private Label inspirationPointsLabel;
    private Label inspirationPoints;
    
    private Label hullTrackerLabel;
    private Label hullTracker;
    
    private Label healthTrackerLabel;
    private Label healthTracker;
    
    public GameStatsInfo(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(NORMAL_BACKGROUND_COLOR, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setPadding(new Insets(INSIDE_PADDING));
        setSpacing(INSIDE_PADDING);
        
        setPrefWidth(JOURNEY_EDITOR_X);
        setPrefHeight(GAME_STATS_Y);
        
        setPropertyChangeListeners();
        initGameStatsInfo();
        setComponentsHandlers();
    }
    
    private void setPropertyChangeListeners(){
        observableModel.addPropertyChangeListener(FPC_GAME_STATS_UPDATE, this);
    }
    
    private void initGameStatsInfo(){
        //Top Line
        topContainer = new HBox(INSIDE_PADDING);
        
        playerNameLabel = new Label("Player: ");
        playerNameLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        playerName = new Label();
        playerName.setTextFill(SELECTABLE_TEXT_COLOR);

        turnLabel = new Label("Turn: ");
        turnLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        turn = new Label();
        turn.setTextFill(SELECTABLE_TEXT_COLOR);

        totalAliensLabel = new Label("Aliens: ");
        totalAliensLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        totalAliens = new Label();
        totalAliens.setTextFill(SELECTABLE_TEXT_COLOR);
        
        topContainer.getChildren().addAll(playerNameLabel, playerName, turnLabel, turn, totalAliensLabel, totalAliens);
        
        //Bottom line
        bottomContainer = new HBox(INSIDE_PADDING);
        
        actionPointsLabel = new Label("AP: ");
        actionPointsLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        actionPoints = new Label();
        actionPoints.setTextFill(SELECTABLE_TEXT_COLOR);

        inspirationPointsLabel = new Label("IP: ");
        inspirationPointsLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        inspirationPoints = new Label();
        inspirationPoints.setTextFill(SELECTABLE_TEXT_COLOR);

        hullTrackerLabel = new Label("Hull: ");
        hullTrackerLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        hullTracker = new Label();
        hullTracker.setTextFill(SELECTABLE_TEXT_COLOR);

        healthTrackerLabel = new Label("Health: ");
        healthTrackerLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        healthTracker = new Label();
        healthTracker.setTextFill(SELECTABLE_TEXT_COLOR);
        
        bottomContainer.getChildren().addAll(actionPointsLabel, actionPoints, inspirationPointsLabel, inspirationPoints, hullTrackerLabel, hullTracker, healthTrackerLabel, healthTracker);
        
        getChildren().addAll(topContainer, bottomContainer);
    }
    
    private void setComponentsHandlers(){
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case FPC_GAME_STATS_UPDATE:
                playerName.setText(observableModel.getPlayerName());
                
                /*if(observableModel.getCurrentTurn() == 0)
                    turn.setText("Start");
                else*/
                turn.setText(Integer.toString(observableModel.getCurrentTurn()+1));
                
                totalAliens.setText(Integer.toString(observableModel.getAliensCount()));
                
                actionPoints.setText(Integer.toString(observableModel.getActionPoints()));
                
                inspirationPoints.setText(Integer.toString(observableModel.getInspirationPoints()));
                
                hullTracker.setText(Integer.toString(observableModel.getHullTracker()));
                
                healthTracker.setText(Integer.toString(observableModel.getHealthTracker()));
                
                break;
        }
    }
}
