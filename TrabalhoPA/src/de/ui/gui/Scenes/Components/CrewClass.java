package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CrewClass extends VBox implements Constants{
    private ObservableModel observableModel;
    private int type;
    private ImageView typeAvatar;
    private Label typeName;
    
    public CrewClass(ObservableModel observableModel, int type) {
        this.observableModel = observableModel;
        
        this.type = type;
        initCrewMemberTypeContainer();
        
        setPrefSize(CREW_CLASS_LIST_X/CREW_CLASS_PER_LINE, INTERACTION_Y/(CREWMEMBER_TYPES.length/CREW_CLASS_PER_LINE));
    }
    
    private void initCrewMemberTypeContainer(){
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.rgb(type*20, type*15, type*10), CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        typeAvatar = new ImageView();
        typeAvatar.setImage(new Image("file:src\\de\\ui\\gui\\Images\\tempAvatar.png"));
        typeAvatar.setFitHeight(60);
        typeAvatar.setFitWidth(60);
        
        typeName = new Label(CREWMEMBER_TYPES[type-1]);
        
        getChildren().addAll(typeAvatar, typeName);
        
        setComponentsHandlers();
    }
    
    private void setComponentsHandlers(){
        setOnMousePressed(e -> {
            observableModel.changeCrewMember(type);
        });
    }
}
