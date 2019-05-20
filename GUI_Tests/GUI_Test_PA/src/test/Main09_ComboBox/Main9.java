package test.Main09_ComboBox;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main9 extends Application{
    
    Stage mainWindow;
    Scene scene;
    Button button;
    ComboBox<String> comboBox;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        button = new Button("Submit");
        
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Good Shit",
                "Bad Shit",
                "Average Shit"
        );
        
        comboBox.setEditable(true);
        
        button.setOnAction(e -> printShit(comboBox));
        comboBox.setOnAction(e -> {
            System.out.println(" -> " + comboBox.getValue());
        });
        
        comboBox.setPromptText("What?");
        
        VBox layout = new VBox(10 /*V Spacing*/);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(comboBox, button);
        
        scene = new Scene(layout, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    private void printShit(ComboBox<String> comboBox){
        System.out.println(comboBox.getValue());
    }
}
