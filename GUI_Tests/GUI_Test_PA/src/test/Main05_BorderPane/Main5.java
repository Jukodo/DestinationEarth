package test.Main05_BorderPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main5 extends Application{
    
    Stage mainWindow;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        //Top menu
        HBox topMenu = new HBox();
        Button fileBtn = new Button("File");
        Button editBtn = new Button("Edit");
        Button viewBtn = new Button("View");
        topMenu.getChildren().addAll(fileBtn, editBtn, viewBtn);
        
        //Left Menu
        VBox leftMenu = new VBox();
        Button homeBtn = new Button("Home");
        Button historyBtn = new Button("History");
        Button profileBtn = new Button("Profile");
        leftMenu.getChildren().addAll(homeBtn, historyBtn, profileBtn);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);
        
        Scene scene = new Scene(borderPane, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
        
    }
}
