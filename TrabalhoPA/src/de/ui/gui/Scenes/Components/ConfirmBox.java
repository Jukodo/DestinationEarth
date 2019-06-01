package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

        
public class ConfirmBox implements Constants{
    
    private static boolean answer;
    
    private static VBox labelContainer;
    private static Label mainLabel;
    private static Label infoLabel;
    
    private static HBox buttonContainer;
    private static Button yesButton;
    private static Button noButton;
    
    public static boolean display(String mainText, String infoText){
        Stage confirmWindow = new Stage();
        
        //Cant click anything else but this window
        confirmWindow.initStyle(StageStyle.UNDECORATED);
        confirmWindow.initModality(Modality.APPLICATION_MODAL);
        confirmWindow.setMinWidth(250);
        
        //Containers
        labelContainer = new VBox(INSIDE_PADDING);
        labelContainer.setAlignment(Pos.CENTER);
        buttonContainer = new HBox(INSIDE_PADDING);
        
        //Labels
        mainLabel = new Label(mainText);
        infoLabel = new Label(infoText);
        
        labelContainer.getChildren().addAll(mainLabel, infoLabel);
        
        //Buttons
        yesButton = new Button("Yes");
        noButton = new Button("No");
        
        buttonContainer.getChildren().addAll(yesButton, noButton);
        
        yesButton.setOnAction(e -> {
            answer = true;
            confirmWindow.close();
        });
        
        noButton.setOnAction(e -> {
            answer = false;
            confirmWindow.close();
        });
        
        //Layout
        VBox layout = new VBox(INSIDE_PADDING);
        layout.setPadding(new Insets(INSIDE_PADDING));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(labelContainer, buttonContainer);
        layout.setBackground(new Background(new BackgroundFill(NORMAL_BACKGROUND_COLOR_O, new CornerRadii(5), Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        
        //Scene
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("de/ui/gui/css/DestinationEarthStyle.css");
        
        //Stage
        confirmWindow.setScene(scene);
        confirmWindow.showAndWait();
        
        return answer;
    }
}
