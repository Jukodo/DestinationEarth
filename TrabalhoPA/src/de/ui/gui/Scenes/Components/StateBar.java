package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
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

public class StateBar extends HBox implements Constants{
    private HashMap<Integer, HBox> states;
    private final int barType;
    private int activeState;
    private Label tempLabel;
    
    public StateBar(int barType, int activeState){
        this.barType = barType;
        this.activeState = activeState-1;
        states = new HashMap<>();
        
        initStateContainers();
    }
    
    private void initStateContainers(){
        String[] bar_states;
        if(barType == STATE_BAR_PREGAME)
            bar_states = STATE_BAR_PREGAME_STATES;
        else{
            activeState -= 3;
            if(activeState > 1)
                --activeState;
            bar_states = STATE_BAR_INGAME_STATES;
        }
        
        for(int i = 0; i < bar_states.length; i++){
            states.put(i, new HBox());
            if(i == activeState)
                processLabel(i, bar_states, true);
            else
                processLabel(i, bar_states, false);
            getChildren().add(states.get(i));
        }
    }
    
    private void processLabel(int index, String[] bar_states, boolean active){
        tempLabel = new Label(bar_states[index]);
        tempLabel.setMinWidth(WINDOW_X / bar_states.length);
        tempLabel.setMinHeight(STATE_BAR_Y);
        tempLabel.setAlignment(Pos.CENTER);
        if(active){
            tempLabel.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
            tempLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        }else{
            tempLabel.setBackground(new Background(new BackgroundFill(NORMAL_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
            tempLabel.setTextFill(NORMAL_TEXT_COLOR);
        }
        if(index == 0)
            tempLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        else
            tempLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 1, 1, 0))));
        states.get(index).getChildren().add(tempLabel);
    }
}
