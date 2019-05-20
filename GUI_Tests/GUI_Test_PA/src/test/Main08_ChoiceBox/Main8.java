package test.Main08_ChoiceBox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main8 extends Application{

    Stage mainWindow;
    Scene scene;
    Button button;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        button = new Button("Click");
        
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        //getItems() returns a "parent" list, where is possible to add items
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Banana");
        choiceBox.getItems().addAll("Orange", "Pear");
        
        //Has to be an inserted value
        choiceBox.setValue("Apples");
        
        /*Works but activates if select an item that is already selected
        choiceBox.setOnAction(e -> {
            getChoice(choiceBox);
        });*/
        
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v/*observable*/, oldValue, newValue) -> {
            System.out.println(v.getValue());
            System.out.println(oldValue + " -> " + newValue);
        });
        
        button.setOnAction(e -> {
            getChoice(choiceBox);
        });
        
        VBox layout = new VBox(10 /*V Spacing*/);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(choiceBox, button);
        
        scene = new Scene(layout, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    private void getChoice(ChoiceBox<String> choiceBox){
        System.out.println(choiceBox.getValue());
    }
}
