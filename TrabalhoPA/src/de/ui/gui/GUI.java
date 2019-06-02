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
    private ObservableModel observableModel = new ObservableModel(new DestinationEarth());
    
    private Stage mainWindow;
    private HashMap<Integer, Scene> scenes;
    
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
        
        swapScene(SCENE_BEGINNING);
        mainWindow.show();
    }
    
    public boolean swapScene(int swapTo){
        System.out.println("Swapped to " + swapTo + " aka: " + SCENE[swapTo]);
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
                case SCENE_ALIEN_PHASE:
                    tempScene = new Scene(new AlienPhase_layout(observableModel));
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
        System.out.println("PropertyChange Captured");
        switch(evt.getPropertyName()){
            case FPC_SWAP_SCENE:
                System.out.println("Swapping to " + (int) evt.getOldValue());
                swapScene((int) evt.getOldValue());
                break;
            case FPC_CLOSE_WINDOW:
                mainWindow.close();
                break;
        }
    }
}
