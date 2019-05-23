package de.ui.gui;

import de.DestinationEarth;
import de.logic.data.Constants;
import de.ui.gui.Scenes.Beginning_layout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application implements Constants, PropertyChangeListener{
    ObservableModel observableModel = new ObservableModel(new DestinationEarth());
    
    Stage mainWindow;
    HashMap<Integer, Scene> scenes;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage mainWindow) {
        observableModel.addPropertyChangeListener(FPC_CLOSE_WINDOW, this);
        observableModel.addPropertyChangeListener(FPC_SWAP_SCENE, this);
        
        this.mainWindow = mainWindow;
        mainWindow.setTitle(GAME_TITLE);
        mainWindow.setResizable(false);
        mainWindow.setWidth(WINDOW_X);
        mainWindow.setHeight(WINDOW_Y);
        
        initScenes();
        
        setScene(SCENE_BEGINNING);
        mainWindow.show();
    }
    
    public boolean setScene(int swapTo){
        System.out.println("Swapped to " + swapTo + " aka: " + scenes.get(swapTo).getClass().toString());
        mainWindow.setScene(scenes.get(swapTo));
        return true;
    }
    
    private void initScenes(){
        scenes = new HashMap<>();
        
        Scene tempScene = null;
        
        for(int i = 0; i < NUM_SCENES; i++){
            switch(i){
                case SCENE_BEGINNING:
                    tempScene = new Scene(new Beginning_layout(observableModel));
                    break;
                case SCENE_CREWSELECTION:
                    break;
                case SCENE_CREWPLACEMENT:
                    break;
                case SCENE_JOURNEYSELECTION:
                    break;
                case SCENE_JOURNEYPHASE:
                    break;
                case SCENE_RESTPHASE:
                    break;
                case SCENE_CREWPHASE:
                    break;
                case SCENE_ALIENPHASE:
                    break;
                default:
                    continue;
            }
            if(tempScene != null){
                scenes.put(i, tempScene);
                tempScene = null;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("PropertyChange Captured");
        switch(evt.getPropertyName()){
            case FPC_SWAP_SCENE:
                System.out.println("FPC_SWAP_SCENE fired!");
                break;
            case FPC_CLOSE_WINDOW:
                System.out.println("FPC_CLOSE_WINDOW fired!");
                break;
        }
    }
}
