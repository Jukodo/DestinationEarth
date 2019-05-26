package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
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

public class CrewMemberType extends VBox implements Constants{

    int type;
    private ImageView typeAvatar;
    private Label typeName;
    
    public CrewMemberType(int type) {
        this.type = type;
        initCrewMemberTypeContainer();
    }
    
    private void initCrewMemberTypeContainer(){
        setMinSize(CREWMEMBER_TYPES_X_PER_TYPE, CREWMEMBER_TYPES_Y_PER_TYPE);
        setMaxSize(CREWMEMBER_TYPES_X_PER_TYPE, CREWMEMBER_TYPES_Y_PER_TYPE);
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        typeAvatar = new ImageView();
        typeAvatar.setImage(new Image("file:src\\de\\ui\\gui\\Images\\tempAvatar.png"));
        typeAvatar.setFitHeight(60);
        typeAvatar.setFitWidth(60);
        
        typeName = new Label(CREWMEMBER_TYPES[type]);
        
        getChildren().addAll(typeAvatar, typeName);
    }
}
