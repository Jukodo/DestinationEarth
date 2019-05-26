package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.ui.gui.ObservableModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Beginning_layout extends HBox implements Constants{
    private ObservableModel observableModel;
    
    //Game title container
    private VBox titleContainer;
    private Label gameTitle;
    
    //Initial Container
    private VBox initialContainer;
    private Button btn_PlayGame;
    private Button btn_ShowRules;
    private Button btn_QuitGame;
    
    //Play Game Container
    private VBox playContainer;
    private Button btn_NewGame;
    private Button btn_LoadGame;
    private Button btn_GoBack_play;
    
    //New Game Container
    private VBox newGameContainer;
    private TextField tf_PlayerName;
    private Button btn_StartGame;
    private Button btn_GoBack_newGame;
    
    //Rules Container
    private VBox rulesContainer;
    
    //Load Game Container

    public Beginning_layout(ObservableModel observableModel){
        this.observableModel = observableModel;
        
        initializeComponents();
        setComponentsHandlers();
        setContainer(initialContainer);
    }
    
    private void initializeComponents(){
        //Containers
        titleContainer = new VBox();
        initialContainer = new VBox(20/*V Spacing*/);
        playContainer = new VBox(20/*V Spacing*/);
        newGameContainer = new VBox(20/*V Spacing*/);
        rulesContainer = new VBox(20/*V Spacing*/);
        
        titleContainer.setMinSize(WINDOW_X/2, WINDOW_Y);
        initialContainer.setMinSize(WINDOW_X/2, WINDOW_Y);
        playContainer.setMinSize(WINDOW_X/2, WINDOW_Y);
        newGameContainer.setMinSize(WINDOW_X/2, WINDOW_Y);
        rulesContainer.setMinSize(WINDOW_X/2, WINDOW_Y);
        
        //Temp stuff DELETE LATER
            titleContainer.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
            initialContainer.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
            playContainer.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
            newGameContainer.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
            rulesContainer.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
            
        //Components
        btn_PlayGame = new Button("Play");
        btn_ShowRules = new Button("Rules");
        btn_QuitGame = new Button("Quit");
        btn_NewGame = new Button("New Game");
        btn_LoadGame = new Button("Load Game");
        btn_GoBack_play = new Button("Go Back");
        btn_GoBack_newGame = new Button("Go Back");
        tf_PlayerName = new TextField(); 
        tf_PlayerName.setPromptText("Player name");
        btn_StartGame = new Button("Start Game");
        
        //Title Container
        gameTitle = new Label(GAME_TITLE);
        titleContainer.getChildren().add(gameTitle);
        titleContainer.setAlignment(Pos.CENTER);
            
        //Initial Phase
        initialContainer.getChildren().addAll(btn_PlayGame, btn_ShowRules, btn_QuitGame);
        initialContainer.setAlignment(Pos.CENTER);
        
        //Play Phase
        playContainer.getChildren().addAll(btn_NewGame, btn_LoadGame, btn_GoBack_play);
        playContainer.setAlignment(Pos.CENTER);
        
        //NewGame Phase
        newGameContainer.getChildren().addAll(tf_PlayerName, btn_StartGame, btn_GoBack_newGame);
        newGameContainer.setAlignment(Pos.CENTER);
        
        //Rules Phase
        
        //getChildren().remove(initialContainer);
    }
    
    private void setContainer(Region swapTo){
        getChildren().clear();
        getChildren().addAll(titleContainer, swapTo);
    }
    
    private void setComponentsHandlers(){
        btn_PlayGame.setOnAction(e -> {
            System.out.println("btn_PlayGame clicked");
            setContainer(playContainer);
        });
        btn_ShowRules.setOnAction(e -> {
            
        });
        btn_QuitGame.setOnAction(e -> {
            observableModel.closeWindow();
        });
        btn_NewGame.setOnAction(e -> {
            setContainer(newGameContainer);
        });
        btn_LoadGame.setOnAction(e -> {
            observableModel.swapScene(SCENE_BEGINNING);
        });
        btn_GoBack_play.setOnAction(e -> {
            setContainer(initialContainer);
        });
        btn_GoBack_newGame.setOnAction(e -> {
            setContainer(playContainer);
        });
        btn_StartGame.setOnAction(e -> {
            System.out.println("Start Game clicked");
            System.out.println(tf_PlayerName.getText().isEmpty());
            if(tf_PlayerName.getText().isEmpty())
                tf_PlayerName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            else{
                observableModel.startGame(tf_PlayerName.getText());
                observableModel.swapScene(SCENE_CREWSELECTION);
            }
        });
    }
}
