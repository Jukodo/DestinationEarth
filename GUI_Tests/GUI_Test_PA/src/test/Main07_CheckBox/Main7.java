package test.Main07_CheckBox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main7 extends Application{
    
    Stage mainWindow;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        //Checkbox
        CheckBox cb1 = new CheckBox("Bacon");
        CheckBox cb2 = new CheckBox("Tuna");
        cb2.setSelected(true);
        
        //Button
        Button btn = new Button("Order");
        btn.setOnAction(e -> {
            handleOptions(cb1, cb2);
        });
        
        //Layout
        VBox layout = new VBox(10/*V Spacing*/);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(cb1, cb2, btn);
        
        Scene scene = new Scene(layout, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    //Handle CheckBox Options
    private void handleOptions(CheckBox cb1, CheckBox cb2){
        String message = "Order: ";
        
        if(cb1.isSelected()){
            message += cb1.getText() + " ";
        }
        if(cb2.isSelected()){
            message += cb2.getText() + " ";
        }
        System.out.println(message);
    }
}
