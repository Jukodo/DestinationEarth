package de.ui.gui;

import de.logic.data.ObservableModel;
import de.DestinationEarth;
import de.logic.data.Constants;
import de.ui.gui.Scenes.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application implements Constants, PropertyChangeListener{
    private ObservableModel observableModel;
    
    private Stage mainWindow;
    private HashMap<Integer, Scene> scenes;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage mainWindow) {
        observableModel = new ObservableModel(new DestinationEarth(), mainWindow);
        
        observableModel.addPropertyChangeListener(FPC_CLOSE_WINDOW, this);
        observableModel.addPropertyChangeListener(FPC_SWAP_SCENE, this);
        
        this.mainWindow = mainWindow;
        mainWindow.setTitle(GAME_TITLE);
        mainWindow.setResizable(false);
        mainWindow.setWidth(WINDOW_X);
        
        if(SHOW_MENU)
            mainWindow.setHeight(WINDOW_Y_WITH_MENU);
        else
            mainWindow.setHeight(WINDOW_Y);
        
        initScenes();
        
        swapScene(SCENE_BEGINNING);
        mainWindow.show();
    }
    
    public boolean swapScene(int swapTo){
        mainWindow.setScene(scenes.get(swapTo));
        observableModel.currentState();
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
                case SCENE_CREW_SELECTION:
                    tempScene = new Scene(new CrewSelection_layout(observableModel));
                    break;
                case SCENE_CREW_PLACEMENT:
                    tempScene = new Scene(new CrewPlacement_layout(observableModel));
                    break;
                case SCENE_JOURNEY_SELECTION:
                    tempScene = new Scene(new JourneySelection_layout(observableModel));
                    break;
                case SCENE_JOURNEY_PHASE:
                    tempScene = new Scene(new JourneyPhase_layout(observableModel));
                    break;
                case SCENE_REST_PHASE:
                    tempScene = new Scene(new RestPhase_layout(observableModel));
                    break;
                case SCENE_CREW_PHASE:
                    tempScene = new Scene(new CrewPhase_layout(observableModel));
                    break;
                default:
                    continue;
            }
            if(tempScene != null){
                tempScene.getStylesheets().add("de/ui/gui/css/DestinationEarthStyle.css");
                scenes.put(i, tempScene);
                tempScene = null;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case FPC_SWAP_SCENE:
                swapScene((int) evt.getOldValue());
                break;
            case FPC_CLOSE_WINDOW:
                mainWindow.close();
                break;
        }
    }
}
