package de.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        
        /*Modelo model = new Modelo();        
        ModeloObservavel observableModel = new ModeloObservavel(model);
        
        PaneOrganizer organizer = new PaneOrganizer(observableModel); 
                
        Scene scene = new Scene(organizer.getRoot()); 
        */
        primaryStage.setResizable(false);        
        //primaryStage.setScene(scene); 
        primaryStage.setTitle("ADWDA"); 
        primaryStage.show();
        
        //new Temporizador(observableModel);

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
