package test.Main02_SceneSwap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main2 extends Application{

    Stage mainWindow;
    Scene sc1, sc2;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        
        Label label1 = new Label("Gucci");
        Button btn1 = new Button("Go to scene 2");
        btn1.setOnAction(e -> mainWindow.setScene(sc2));
        
        //Layout 1 - Children are stacked verticaly
        VBox layout1 = new VBox(20/*Spacing*/);
        layout1.getChildren().addAll(label1, btn1);
        sc1 = new Scene(layout1, 200, 200);
        
        //Button 2
        Button btn2 = new Button("Go to scene 1");
        btn2.setOnAction(e -> mainWindow.setScene(sc1));
        
        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(btn2);
        sc2 = new Scene(layout2, 600, 300);
        
        mainWindow.setScene(sc1);
        mainWindow.setTitle("DE");
        mainWindow.show();
    }
}
