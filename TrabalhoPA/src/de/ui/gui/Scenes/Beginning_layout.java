package de.ui.gui.Scenes;

import de.logic.data.Constants;
import de.logic.data.ObservableModel;
import de.ui.gui.Scenes.Components.MenuDisplay;
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class Beginning_layout extends BorderPane implements Constants{
    private ObservableModel observableModel;
    
    //Main Container
    private VBox mainContainer;
    
    //Menu
    private MenuDisplay menuDisplay;
    
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
        
        setId("init-background-image");
        
        initializeComponents();
        setComponentsHandlers();
        setContainer(initialContainer);
    }
    
    private void initializeComponents(){
        //Containers
        mainContainer = new VBox(50);
        mainContainer.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR_O, CornerRadii.EMPTY, Insets.EMPTY)));
        mainContainer.setPrefSize(BEGINNING_X, BEGINNING_Y);
        mainContainer.setAlignment(Pos.CENTER);
        
        titleContainer = new VBox();
        initialContainer = new VBox(20/*V Spacing*/);
        playContainer = new VBox(20/*V Spacing*/);
        newGameContainer = new VBox(20/*V Spacing*/);
        rulesContainer = new VBox(20/*V Spacing*/);
        
        setLeft(mainContainer);
        
        //Temp stuff DELETE LATER
            
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
        
        //Menu
        if(SHOW_MENU){
            menuDisplay = new MenuDisplay(observableModel);
            setTop(menuDisplay);
        }
        
        //Title Container
        gameTitle = new Label(GAME_TITLE);
        gameTitle.setId("GameTitle");
        gameTitle.setTextFill(SELECTABLE_TEXT_COLOR);
        
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
        mainContainer.getChildren().clear();
        mainContainer.getChildren().addAll(titleContainer, swapTo);
    }
    
    private void setComponentsHandlers(){
        btn_PlayGame.setOnAction(e -> {
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
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Binary files", "*.bin"));
            File saveFile = fileChooser.showOpenDialog(null);
            
            if(saveFile != null)
                observableModel.loadGame(saveFile);
        });
        btn_GoBack_play.setOnAction(e -> {
            setContainer(initialContainer);
        });
        btn_GoBack_newGame.setOnAction(e -> {
            setContainer(playContainer);
        });
        btn_StartGame.setOnAction(e -> {
            System.out.println(tf_PlayerName.getText().isEmpty());
            if(tf_PlayerName.getText().isEmpty())
                tf_PlayerName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            else{
                observableModel.startGame(tf_PlayerName.getText());
                observableModel.swapScene(SCENE_CREW_SELECTION);
            }
        });
    }
}
