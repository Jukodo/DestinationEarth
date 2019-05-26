package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.ui.gui.ObservableModel;
import de.ui.gui.Scenes.Components.CrewBar;
import de.ui.gui.Scenes.Components.StateBar;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CrewPhase_layout extends BorderPane implements Constants{
    private ObservableModel observableModel;
    
    //Top Container
    private VBox topContainer;
    private StateBar stateBarContainer;
    private CrewBar crewBarContainer;
    
    public CrewPhase_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        initComponents();
    }
    
    private void initComponents(){
        this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        //Top
        topContainer = new VBox();
        stateBarContainer = new StateBar(STATE_BAR_INGAME, SCENE_CREWPHASE);
        crewBarContainer = new CrewBar(observableModel);
        crewBarContainer.setPadding(new Insets(INSIDE_PADDING));
        topContainer.getChildren().addAll(stateBarContainer, crewBarContainer);
        setTop(topContainer);
    }
}