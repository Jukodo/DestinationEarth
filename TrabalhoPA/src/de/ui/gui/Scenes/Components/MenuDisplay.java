package de.ui.gui.Scenes.Components;

import de.logic.data.ObservableModel;
import java.io.File;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuDisplay extends MenuBar{
    private ObservableModel observableModel;
    
    private Menu gameMenu;
    private MenuItem saveGame;
    private MenuItem loadGame;
    private MenuItem exitGame;
    
    public MenuDisplay(ObservableModel observableModel){
        this.observableModel = observableModel;
        
        initMenuDisplay();
        setComponentsHandlers();
    }
    
    private void initMenuDisplay(){
        gameMenu = new Menu("_Game");
        
        saveGame = new MenuItem("_Save Game");
        loadGame = new MenuItem("_Load Game");
        exitGame = new MenuItem("_Exit Game");
        
        
        gameMenu.getItems().addAll(saveGame, loadGame, new SeparatorMenuItem(), exitGame);
        
        getMenus().add(gameMenu);
    }
    
    private void setComponentsHandlers(){
        saveGame.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new ExtensionFilter("Binary files", "*.bin"));
            File saveFile = fileChooser.showSaveDialog(null);
            
            if(saveFile != null)
                observableModel.saveGame(saveFile);
        });
        
        loadGame.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new ExtensionFilter("Binary files", "*.bin"));
            File saveFile = fileChooser.showOpenDialog(null);
            
            if(saveFile != null)
                observableModel.loadGame(saveFile);
        });
        
        exitGame.setOnAction(e -> {
            observableModel.closeWindow();
        });
    }
}
