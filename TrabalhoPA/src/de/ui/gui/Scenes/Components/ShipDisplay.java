package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.shape.Circle;
import de.logic.data.Room;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ShipDisplay extends StackPane implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    private ImageView shipImage;
    private Pane ghostContainer;
    private VBox hoverInfo;
    private HashMap<Integer, HBox> rooms;
    private HashMap<Integer, Room> roomsObj;
    private final boolean interactable;
    private final int fromScene;
    
    public ShipDisplay(ObservableModel observableModel, boolean interactable, int fromScene){
        this.observableModel = observableModel;
        observableModel.addPropertyChangeListener(FPC_PLACED_CREWMEMBER, this);
        
        this.interactable = interactable;
        this.fromScene = fromScene;
        
        rooms = new HashMap<>();
        roomsObj = observableModel.getRooms();
        
        setPadding(new Insets(INSIDE_PADDING));
        setBackground(new Background(new BackgroundFill(NORMAL_BACKGROUND_COLOR_O, CornerRadii.EMPTY, Insets.EMPTY)));
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
        
        processGhostRooms();
        getChildren().add(ghostContainer);
        setComponentsHandlers();
        
        //Hover Info
        hoverInfo = new VBox();
        hoverInfo.relocate(107, 457);
        ghostContainer.getChildren().add(hoverInfo);
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
        //room1.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), new CornerRadii(50, 100, 100, 50, 0, 0, 0, 0, true, true, true, true, true, true, true, true), Insets.EMPTY)));
        room1.setPickOnBounds(false);
        rooms.put(1, room1);
        ghostContainer.getChildren().addAll(room1);
        
        //Room 2
        room2.setMinSize(54, 132);
        room2.relocate(183, 243);
        //room2.setBackground(new Background(new BackgroundFill(Color.rgb(100, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(2, room2);
        ghostContainer.getChildren().addAll(room2);
        
        //Room 3
        room3.setMinSize(70, 85);
        room3.relocate(54, 106);
        //room3.setBackground(new Background(new BackgroundFill(Color.rgb(200, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(3, room3);
        ghostContainer.getChildren().addAll(room3);
        
        //Room 4
        room4.setMinSize(71, 88);
        room4.relocate(238, 107);
        //room4.setBackground(new Background(new BackgroundFill(Color.rgb(0, 100, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(4, room4);
        ghostContainer.getChildren().addAll(room4);
        
        //Room 5
        room5.setMinSize(56, 135);
        room5.relocate(125, 106);
        //room5.setBackground(new Background(new BackgroundFill(Color.rgb(0, 200, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(5, room5);
        ghostContainer.getChildren().addAll(room5);
        
        //Room 6
        room6.setMinSize(77, 67);
        room6.relocate(142, 382);
        //room6.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 100, 0.5), new CornerRadii(13), Insets.EMPTY)));
        room6.setPickOnBounds(false);
        rooms.put(6, room6);
        ghostContainer.getChildren().addAll(room6);
        
        //Room 7
        room7.setMinSize(70, 69);
        room7.relocate(238, 279);
        //room7.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 200, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(7, room7);
        ghostContainer.getChildren().addAll(room7);
        
        //Room 8
        room8.setMinSize(54, 135);
        room8.relocate(183, 106);
        //room8.setBackground(new Background(new BackgroundFill(Color.rgb(100, 100, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(8, room8);
        ghostContainer.getChildren().addAll(room8);
        
        //Room 9
        room9.setMinSize(70, 83);
        room9.relocate(54, 194);
        //room9.setBackground(new Background(new BackgroundFill(Color.rgb(0, 100, 100, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(9, room9);
        ghostContainer.getChildren().addAll(room9);
        
        //Room 10
        room10.setMinSize(56, 133);
        room10.relocate(126, 243);
        //room10.setBackground(new Background(new BackgroundFill(Color.rgb(100, 0, 100, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(10, room10);
        ghostContainer.getChildren().addAll(room10);
        
        //Room 11
        room11.setMinSize(69, 80);
        room11.relocate(239, 197);
        //room11.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(11, room11);
        ghostContainer.getChildren().addAll(room11);
        
        //Room 12
        room12.setMinSize(70, 68);
        room12.relocate(54, 280);
        //room12.setBackground(new Background(new BackgroundFill(Color.rgb(0, 200, 200, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        rooms.put(12, room12);
        ghostContainer.getChildren().addAll(room12);
        
        CornerRadii room1Corner = new CornerRadii(50, 100, 100, 50, 0, 0, 0, 0, true, true, true, true, true, true, true, true);
        CornerRadii room6Corner = new CornerRadii(13);
        for(int i = 1; i <= rooms.size(); i++){
            Color backgroundColor;
            
            if(interactable)
                backgroundColor = SELECTABLE_BACKGROUND_COLOR_O;
            else
                backgroundColor = NORMAL_BACKGROUND_COLOR_O;
            
            if(i == 1)
                rooms.get(i).setBackground(new Background(new BackgroundFill(backgroundColor, room1Corner, Insets.EMPTY)));
            else if(i == 6)
                rooms.get(i).setBackground(new Background(new BackgroundFill(backgroundColor, room6Corner, Insets.EMPTY)));
            else
                rooms.get(i).setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
                
            rooms.get(i).setPadding(new Insets(2));
            rooms.get(i).setSpacing(2);
            rooms.get(i).setAlignment(Pos.BOTTOM_RIGHT);
            /*rooms.get(i).setStyle("-fx-background-color: rgba(" + SELECTED_BACKGROUND_COLOR.getRed() + 
                                                           ", " + SELECTED_BACKGROUND_COLOR.getGreen() + 
                                                           ", " + SELECTED_BACKGROUND_COLOR.getBlue() +
                                                           ", " + SELECTED_BACKGROUND_OPACITY + ")");*/
        }
    }
    
    private void setComponentsHandlers(){
        for(int i = 1; i <= rooms.size(); i++){
            final int fi = i;
            
            if(interactable){
                rooms.get(i).setOnMousePressed(e -> {
                    observableModel.placeCrewMember(fi);
                });
            }
            
            rooms.get(i).setOnMouseEntered(e -> {
                updateHoverInfo(fi);
            });
            
            rooms.get(i).setOnMouseExited(e -> {
                hoverInfo.getChildren().clear();
            });
        }
    }

    private void updateHoverInfo(int index){
        Label roomName = new Label("Room #" + index + " - " + roomsObj.get(index).getName());
        
        HBox alienInfo = new HBox(5);
        HBox trapInfo = new HBox(5);
        HBox firstMemberInfo = new HBox(5);
        HBox secondMemberInfo = new HBox(5);
        
        hoverInfo.getChildren().add(roomName);
        
        if(!roomsObj.get(index).getAliensInside().isEmpty()){
            Label alienText = new Label(roomsObj.get(index).getAliensInside().size() +"x Aliens");
            alienInfo.getChildren().addAll(new Circle(5, TOKEN_ALIEN), alienText);
            hoverInfo.getChildren().add(alienInfo);
        }
        if(roomsObj.get(index).getTrapInside() != null){
            Label trapText = new Label("1x Trap");
            trapInfo.getChildren().addAll(new Circle(5, TOKEN_TRAP), trapText);
            hoverInfo.getChildren().add(trapInfo);
        }
        for(int j = 0; j < roomsObj.get(index).getMembersInside().size(); j++){
            if(j == 0){
                Label memberText = new Label(roomsObj.get(index).getMembersInside().get(j).getName());
                firstMemberInfo.getChildren().addAll(new Circle(5, roomsObj.get(index).getMembersInside().get(j).getCustomColor()), memberText);
                hoverInfo.getChildren().add(firstMemberInfo);
            }else{
                Label memberText = new Label(roomsObj.get(index).getMembersInside().get(j).getName());
                secondMemberInfo.getChildren().addAll(new Circle(5, roomsObj.get(index).getMembersInside().get(j).getCustomColor()), memberText);
                hoverInfo.getChildren().add(secondMemberInfo);
            }
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(FPC_PLACED_CREWMEMBER)){
            for(int i = 1; i < rooms.size(); i++){
                rooms.get(i).getChildren().clear();
                
                if(!roomsObj.get(i).getAliensInside().isEmpty()){
                    rooms.get(i).getChildren().add(new Circle(5, TOKEN_ALIEN));
                }
                if(roomsObj.get(i).getTrapInside() != null){
                    rooms.get(i).getChildren().add(new Circle(5, TOKEN_TRAP));
                }
                for(int j = 0; j < roomsObj.get(i).getMembersInside().size(); j++){
                    rooms.get(i).getChildren().add(new Circle(5, roomsObj.get(i).getMembersInside().get(j).getCustomColor()));
                }
            }
        }
    }
}
