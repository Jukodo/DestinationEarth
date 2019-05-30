package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.util.HashMap;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ShipDisplay extends StackPane implements Constants{
    private ObservableModel observableModel;
    private ImageView shipImage;
    private Pane ghostContainer;
    private HashMap<Integer, Label> rooms;
    
    public ShipDisplay(ObservableModel observableModel){
        this.observableModel = observableModel;
        
        rooms = new HashMap<>();
        setPadding(new Insets(INSIDE_PADDING));
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setAlignment(Pos.CENTER);
        
        setPrefWidth(SHIP_DISPLAY_X);
        setPrefHeight(INTERACTION_Y);
        
        initShipDisplay();
    }
    
    private void initShipDisplay(){
        ghostContainer = new Pane();
        
        shipImage = new ImageView();
        shipImage.setImage(new Image("file:src\\de\\ui\\gui\\Images\\shipStructure_Unnamed.jpg"));
        shipImage.setFitWidth(305);
        shipImage.setFitHeight(506);
        
        processGhostRooms();
        
        getChildren().addAll(shipImage, ghostContainer);
        
    }
    
    private void processGhostRooms(){
    }
}
