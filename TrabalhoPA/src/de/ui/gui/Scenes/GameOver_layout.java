package de.ui.gui.Scenes;

import de.logic.data.Constants;
import static de.logic.data.Constants.GAME_TITLE;
import de.logic.data.ObservableModel;
import de.ui.gui.Scenes.Components.MenuDisplay;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameOver_layout extends BorderPane implements Constants{
    private ObservableModel observableModel;
    
    //Main Container
    private VBox mainContainer;
    
    //Menu
    private MenuDisplay menuDisplay;
    
    //Game title container
    private VBox titleContainer;
    private Label gameTitle;
    
    //Game Over container
    private VBox gameoverContainer;
    private Label gameoverLabel;
    private Button replayBtn;
    private Button exitBtn;

    public GameOver_layout(ObservableModel observableModel){
        this.observableModel = observableModel;
        
        setId("init-background-image");
        
        initializeComponents();
        setComponentsHandlers();
    }
    
    private void initializeComponents(){
        //Containers
        mainContainer = new VBox(50);
        mainContainer.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR_O, CornerRadii.EMPTY, Insets.EMPTY)));
        mainContainer.setPrefSize(BEGINNING_X, BEGINNING_Y);
        mainContainer.setAlignment(Pos.CENTER);
        
        if(SHOW_MENU){
            menuDisplay = new MenuDisplay(observableModel);
            setTop(menuDisplay);
        }
        titleContainer = new VBox();
        gameoverContainer = new VBox(INSIDE_PADDING);
        
        setLeft(mainContainer);
        
        //Title Container
        gameTitle = new Label(GAME_TITLE);
        gameTitle.setId("GameTitle");
        gameTitle.setTextFill(SELECTABLE_TEXT_COLOR);
        
        titleContainer.getChildren().add(gameTitle);
        titleContainer.setAlignment(Pos.CENTER);
        
        //GameOver container
        gameoverLabel = new Label("Game Over!");
        gameoverLabel.setId("GameTitle");
        gameoverLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        replayBtn = new Button("Replay");
        exitBtn = new Button("Exit");
        gameoverContainer.getChildren().addAll(gameoverLabel, replayBtn, exitBtn);
        gameoverContainer.setAlignment(Pos.CENTER);
        
        mainContainer.getChildren().addAll(titleContainer, gameoverContainer);
    }
    
    private void setComponentsHandlers(){
        exitBtn.setOnAction(e -> {
            observableModel.closeWindow();
        });
        
        replayBtn.setOnAction(e -> {
            observableModel.replayGame();
        });
    }
}
