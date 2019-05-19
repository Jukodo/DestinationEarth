package test.General;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
        
public class AlertBox {
    
    public static void display(String title, String msg){
        Stage alertWindow = new Stage();
        
        //Cant click anything else this window
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(250);
        
        Label label = new Label();
        label.setText(msg);
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> alertWindow.close());
        
        VBox layout = new VBox(10/*Padding*/);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
}
