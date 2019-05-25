package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CrewMemberType extends VBox implements Constants{

    int type;
    private ImageView typeAvatar;
    private Label typeName;
    
    public CrewMemberType(int type) {
        this.type = type;
        initCrewMemberTypeContainer();
    }
    
    private void initCrewMemberTypeContainer(){
        typeAvatar = new ImageView();
        typeAvatar.setImage(new Image("file:src\\de\\ui\\gui\\Images\\tempAvatar.png"));
        typeAvatar.setFitHeight(100);
        typeAvatar.setFitWidth(100);
        
        typeName = new Label(CREWMEMBER_TYPES[type]);
        
        getChildren().addAll(typeAvatar, typeName);
    }
}
