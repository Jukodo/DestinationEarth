package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.util.List;
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
import javafx.scene.paint.Color;
//TODOOOOOOOOOOO
public class CrewClassInfo extends VBox implements Constants{
    private ObservableModel observableModel;
    
    private Label title;
    
    private Label movementLabel;
    private HBox movement;
    
    private Label attackLabel;
    private HBox attack;
    
    private Label specialsLabel;
    private List<String> specials;
    
    public CrewClassInfo(ObservableModel observableModel) {
        this.observableModel = observableModel;
        
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setPadding(new Insets(INSIDE_PADDING));
        setSpacing(INSIDE_PADDING);
        
        setPrefWidth(CREW_CLASS_INFO_X);
        setPrefHeight(INTERACTION_Y);
        
        initCrewClassInfo();
    }
    
    private void initCrewClassInfo(){
        title = new Label("Red Shirt");
        movementLabel = new Label("Movement");
        attackLabel = new Label("Attack");
        specialsLabel = new Label("Specials");
        
        getChildren().addAll(title, movementLabel, attackLabel, specialsLabel);
    }
}
