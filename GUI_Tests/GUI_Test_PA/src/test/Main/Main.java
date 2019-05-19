package test.Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application /*implements EventHandler<ActionEvent>*/{
    
    Button btn;
    Button btn2;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        mainWindow.setTitle("Destionation Earth");
        
        btn = new Button();
        btn.setText("Click");
        
        //Lambda Expression
        btn.setOnAction(e -> {
            System.out.println("Clicked");
            System.out.println("Clicked confirm");
        });
        
        /*Anonymous Inner Class
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Clicked");
                System.out.println("Clicked confirm");
            }
        });*/
        
        StackPane layout = new StackPane();
        layout.getChildren().add(btn);
        
        Scene scene = new Scene(layout, 300, 250);
        
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    /*ActionEvent
    @Override
    public void handle(ActionEvent t) {
        System.out.println("Clicked");
        System.out.println("Clicked confirm");
    }*/
}
