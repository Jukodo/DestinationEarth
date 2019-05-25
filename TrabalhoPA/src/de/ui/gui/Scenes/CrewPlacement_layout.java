package de.ui.gui.Scenes;

import de.logic.data.Constants;
import static de.logic.data.Constants.INSIDE_PADDING;
import static de.logic.data.Constants.STATE_BAR_PREGAME;
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

public class CrewPlacement_layout extends BorderPane implements Constants{
    private ObservableModel observableModel;
    
    //Top Container
    private VBox topContainer;
    private StateBar stateBarContainer;
    private CrewBar crewBarContainer;
    
    public CrewPlacement_layout(ObservableModel observableModel) {
        this.observableModel = observableModel;
        initComponents();
    }
    
    private void initComponents(){
        this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        //Top
        topContainer = new VBox();
        System.out.println("CrewPhase_layout initComponents");
        stateBarContainer = new StateBar(STATE_BAR_PREGAME, SCENE_CREWPLACEMENT);
        crewBarContainer = new CrewBar(observableModel);
        crewBarContainer.setPadding(new Insets(INSIDE_PADDING));
        topContainer.getChildren().addAll(stateBarContainer, crewBarContainer);
        setTop(topContainer);
    }
}
