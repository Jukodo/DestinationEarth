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
    private HBox mainContainer;
    
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
        
        initializeComponents();
        setComponentsHandlers();
    }
    
    private void initializeComponents(){
        if(SHOW_MENU){
            menuDisplay = new MenuDisplay(observableModel);
            setTop(menuDisplay);
        }
        
        mainContainer = new HBox();
        titleContainer = new VBox();
        gameoverContainer = new VBox(INSIDE_PADDING);
        
        setCenter(mainContainer);
        
        titleContainer.setPrefSize(WINDOW_X/2, WINDOW_Y);
        gameoverContainer.setPrefSize(WINDOW_X/2, WINDOW_Y);
        //Temp stuff DELETE LATER
            titleContainer.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
            gameoverContainer.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
        
        //Title Container
        gameTitle = new Label(GAME_TITLE);
        titleContainer.getChildren().add(gameTitle);
        titleContainer.setAlignment(Pos.CENTER);
        
        //GameOver container
        gameoverLabel = new Label("Game Over!");
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
