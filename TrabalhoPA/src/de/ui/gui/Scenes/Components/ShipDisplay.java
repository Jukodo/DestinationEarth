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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ShipDisplay extends StackPane implements Constants{
    private ObservableModel observableModel;
    private ImageView shipImage;
    private Pane ghostContainer;
    private HashMap<Integer, Label> rooms;
    private final boolean interactable;
    
    public ShipDisplay(ObservableModel observableModel, boolean interactable){
        this.observableModel = observableModel;
        this.interactable = interactable;
        
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
        
        getChildren().add(shipImage);
        
        if(interactable){
            processGhostRooms();
            getChildren().add(ghostContainer);
        }
    }
    
    private void processGhostRooms(){
        HBox room1 = new HBox();
        HBox room2 = new HBox();
        HBox room3 = new HBox();
        HBox room4 = new HBox();
        HBox room5 = new HBox();
        HBox room6 = new HBox();
        HBox room7 = new HBox();
        HBox room8 = new HBox();
        HBox room9 = new HBox();
        HBox room10 = new HBox();
        HBox room11 = new HBox();
        HBox room12 = new HBox();
        
        //Room 1
        room1.setMinSize(252, 86);
        room1.relocate(55, 19);
        room1.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), new CornerRadii(50, 100, 100, 50, 0, 0, 0, 0, true, true, true, true, true, true, true, true), Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room1);
        
        //Room 2
        room2.setMinSize(54, 132);
        room2.relocate(183, 243);
        room2.setBackground(new Background(new BackgroundFill(Color.rgb(100, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room2);
        
        //Room 3
        room3.setMinSize(70, 85);
        room3.relocate(54, 106);
        room3.setBackground(new Background(new BackgroundFill(Color.rgb(200, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room3);
        
        //Room 4
        room4.setMinSize(71, 88);
        room4.relocate(238, 107);
        room4.setBackground(new Background(new BackgroundFill(Color.rgb(0, 100, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room4);
        
        //Room 5
        room5.setMinSize(56, 135);
        room5.relocate(125, 106);
        room5.setBackground(new Background(new BackgroundFill(Color.rgb(0, 200, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room5);
        
        //Room 6
        room6.setMinSize(77, 67);
        room6.relocate(142, 382);
        room6.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 100, 0.5), new CornerRadii(13), Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room6);
        
        //Room 7
        room7.setMinSize(70, 69);
        room7.relocate(238, 279);
        room7.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 200, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room7);
        
        //Room 8
        room8.setMinSize(54, 135);
        room8.relocate(183, 106);
        room8.setBackground(new Background(new BackgroundFill(Color.rgb(100, 100, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room8);
        
        //Room 9
        room9.setMinSize(70, 83);
        room9.relocate(54, 194);
        room9.setBackground(new Background(new BackgroundFill(Color.rgb(0, 100, 100, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room9);
        
        //Room 10
        room10.setMinSize(56, 133);
        room10.relocate(126, 243);
        room10.setBackground(new Background(new BackgroundFill(Color.rgb(100, 0, 100, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room10);
        
        //Room 11
        room11.setMinSize(69, 80);
        room11.relocate(239, 197);
        room11.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room11);
        
        //Room 12
        room12.setMinSize(70, 68);
        room12.relocate(54, 280);
        room12.setBackground(new Background(new BackgroundFill(Color.rgb(0, 200, 200, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        ghostContainer.getChildren().addAll(room12);
    }
}
