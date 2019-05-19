package test.General;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
        
public class ConfirmBox {
    
    static boolean answer;
    
    public static boolean display(String title, String msg){
        Stage confirmWindow = new Stage();
        
        //Cant click anything else this window
        confirmWindow.initModality(Modality.APPLICATION_MODAL);
        confirmWindow.setTitle(title);
        confirmWindow.setMinWidth(250);
        
        Label label = new Label();
        label.setText(msg);
        
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(e -> {
            answer = true;
            confirmWindow.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            confirmWindow.close();
        });
        
        VBox layout = new VBox(10/*Padding*/);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        
        confirmWindow.setScene(scene);
        confirmWindow.showAndWait();
        
        return answer;
    }
}
