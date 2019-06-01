package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import static de.logic.data.Constants.CREW_CLASS_INFO_X;
import static de.logic.data.Constants.INSIDE_PADDING;
import static de.logic.data.Constants.INTERACTION_Y;
import de.logic.data.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class JourneyEditor extends VBox implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
            
    private HBox turnContainer;
    private Label turnLabel;
    private Label turn;
    
    private HBox eventContainer;
    private Label eventLabel;
    private Label event;
    
    private HBox acceptableEventsContainer;
    private Label acceptableEventsLabel;
    private Label acceptableEvents;
    
    private HBox maxContainer;
    private Label maxLabel;
    private Label max;
    
    private HBox minContainer;
    private Label minLabel;
    private Label min;
    
    private VBox newEventContainer;
    private HBox newEventTextBtnMix;
    private Label newEventLabel;
    private TextField newEvent;
    private Button newEventBtn;
    
    private HBox btnContainer;
    private Label btnLabel;
    private Button defaultBtn;
    private Button randomBtn;
    
    public JourneyEditor(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setPadding(new Insets(INSIDE_PADDING));
        setSpacing(INSIDE_PADDING);
        
        setPrefWidth(JOURNEY_EDITOR_X);
        setPrefHeight(INTERACTION_Y);
        
        setPropertyChangeListeners();
        initJourneyEditor();
        setComponentsHandlers();
    }
    
    private void setPropertyChangeListeners(){
        observableModel.addPropertyChangeListener(FPC_JOURNEY_DISPLAY, this);
    }
    
    private void initJourneyEditor(){
        //Turn
        turnContainer = new HBox(INSIDE_PADDING);
        
        turnLabel = new Label("Turn: ");
        turn = new Label();
        
        turnContainer.getChildren().addAll(turnLabel, turn);
        
        //Event
        eventContainer = new HBox(INSIDE_PADDING);
        
        eventLabel = new Label("Event: ");
        event = new Label();
        
        eventContainer.getChildren().addAll(eventLabel, event);
        
        //Acceptable Events
        acceptableEventsContainer = new HBox(INSIDE_PADDING);
        
        acceptableEventsLabel = new Label("Acceptable events: ");
        acceptableEvents = new Label(ACCEPTABLE_EVENTS);
        
        acceptableEventsContainer.getChildren().addAll(acceptableEventsLabel, acceptableEvents);
        
        //Min
        minContainer = new HBox(INSIDE_PADDING);
        
        minLabel = new Label("# Min: ");
        min = new Label();
        
        minContainer.getChildren().addAll(minLabel, min);
        
        //Max
        maxContainer = new HBox(INSIDE_PADDING);
        
        maxLabel = new Label("# Max: ");
        max = new Label();
        maxContainer.getChildren().addAll(maxLabel, max);
        
        //New Event
        newEventContainer = new VBox(2);
        newEventTextBtnMix = new HBox(INSIDE_PADDING);
        
        newEvent = new TextField();
        newEventLabel = new Label("New \"acceptable\" event:");
        newEvent.setPromptText("Event");
        newEvent.setFocusTraversable(false);
        newEventBtn = new Button("Edit");
        
        newEventTextBtnMix.getChildren().addAll(newEvent, newEventBtn);
        newEventContainer.getChildren().addAll(newEventLabel, newEventTextBtnMix);
        
        //Button
        btnContainer = new HBox();
        
        btnLabel = new Label("Set journey to: ");
        defaultBtn = new Button("Default");
        randomBtn = new Button("Random");
        
        btnContainer.getChildren().addAll(btnLabel, defaultBtn, randomBtn);
        
        getChildren().addAll(turnContainer, eventContainer, acceptableEventsContainer, minContainer, maxContainer, newEventContainer, btnContainer);
    }
    
    private void setComponentsHandlers(){
        newEventBtn.setOnAction(e -> {
            final String eventText = newEvent.getText();
            if(!eventText.isEmpty())
                observableModel.setJourney_byChoice(eventText);
            newEvent.clear();
        });
        
        defaultBtn.setOnAction(e -> {
            if(ConfirmBox.display("Are you sure?", "Your current Journey will be lost...")){
                observableModel.setJourney_byDefault();
                newEvent.clear();
            }
        });
        
        randomBtn.setOnAction(e -> {
            if(ConfirmBox.display("Are you sure?", "Your current Journey will be lost...")){
                observableModel.setJourney_byRandom();
                newEvent.clear();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(FPC_JOURNEY_DISPLAY)){
            int activeTurn = (int) evt.getOldValue();
            
            turn.setText(Integer.toString(activeTurn));
            
            event.setText(observableModel.getJourneyTrackerTurn(activeTurn));
            
            min.setText(Integer.toString(MIN_SPAWN_ALIENS_TURN[activeTurn-1]));
            max.setText(Integer.toString(MAX_SPAWN_ALIENS_TURN[activeTurn-1]));
            newEvent.clear();
        }
    }
}
