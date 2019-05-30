package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class CrewMemberTab extends HBox implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    private final int crewMemberIndex;
    
    private HBox avatarNameContainer; 
    private HBox colorContainer; 
    
    private ImageView memberAvatar;
    private Label memberName;
    
    public CrewMemberTab(ObservableModel observableModel, int crewMemberIndex) {
        this.observableModel = observableModel;
        this.crewMemberIndex = crewMemberIndex;
        
        observableModel.addPropertyChangeListener(FPC_CREW_TAB + crewMemberIndex, this);
        observableModel.addPropertyChangeListener(FPC_CLASS_SWAPED + crewMemberIndex, this);
        
        setMinSize((WINDOW_X / NUM_CREW_MEMBERS) - INSIDE_PADDING/*Padding Compensation*/ - (2*NUM_CREW_MEMBERS) + (NUM_CREW_MEMBERS - 1)/*Border Compensation*/, CREWMEMBER_BAR_Y);
        setMaxSize((WINDOW_X / NUM_CREW_MEMBERS) - INSIDE_PADDING/*Padding Compensation*/ - (2*NUM_CREW_MEMBERS) + (NUM_CREW_MEMBERS - 1)/*Border Compensation*/, CREWMEMBER_BAR_Y);
        setPadding(new Insets(0, 0, 0, 10));
        if(crewMemberIndex == 0)
            setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        else
            setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        initAvatarNameContainer();
        initColorContainer();
    }
    
    private void initAvatarNameContainer(){
        avatarNameContainer = new HBox();
        avatarNameContainer.setAlignment(Pos.CENTER_LEFT);
        
        memberAvatar = new ImageView();
        memberAvatar.setImage(new Image("file:src\\de\\ui\\gui\\Images\\tempAvatar.png"));
        memberAvatar.setFitHeight(35);
        memberAvatar.setFitWidth(35);
        
        memberName = new Label("Not selected"/*GET CREW MEMBER CLASS*/);
        memberName.setPadding(new Insets(0, 0, 0, 10));
        avatarNameContainer.getChildren().addAll(memberAvatar, memberName);
        
        getChildren().add(avatarNameContainer);
        setComponentsHandlers();
    }
    
    private void initColorContainer(){
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        
        
        colorContainer = new HBox();
        colorContainer.setMaxSize(CREWMEMBER_BAR_Y-2, CREWMEMBER_BAR_Y-2);
        colorContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        colorContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 1, 1, 2))));
        colorContainer.setAlignment(Pos.CENTER_RIGHT);
        
        HBox.setHgrow(colorContainer, Priority.ALWAYS);
        
        getChildren().addAll(spacer, colorContainer);
    }
    
    private void setComponentsHandlers(){
        setOnMouseClicked(e -> {
            observableModel.swapActiveCrewMember(crewMemberIndex);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(FPC_CREW_TAB+crewMemberIndex)){
            if(Integer.parseInt(evt.getOldValue().toString()) == ACTIVE){
                setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(Integer.parseInt(evt.getOldValue().toString()) == INACTIVE){
                setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }else if(evt.getPropertyName().equals(FPC_CLASS_SWAPED+crewMemberIndex)){
            memberName.setText(CREWMEMBER_TYPES[Integer.parseInt(evt.getOldValue().toString())-1]);
        }
    }
}
