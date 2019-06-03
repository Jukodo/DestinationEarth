package de.ui.gui.Scenes.Components;

import de.logic.data.Constants;
import static de.logic.data.Constants.CONFIRMBOX_BACKGROUND_COLOR;
import static de.logic.data.Constants.INSIDE_PADDING;
import java.util.List;
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

        
public class LogBox implements Constants{
    
    private static boolean answer;
    
    private static VBox labelContainer;
    private static Label mainLabel;
    
    private static HBox buttonContainer;
    private static Button okButton;
    
    public static boolean display(List<String> logs){
        Stage logWindow = new Stage();
        
        //Cant click anything else but this window
        logWindow.initStyle(StageStyle.UNDECORATED);
        logWindow.initModality(Modality.APPLICATION_MODAL);
        logWindow.setMinWidth(250);
        
        //Containers
        labelContainer = new VBox(INSIDE_PADDING);
        labelContainer.setAlignment(Pos.CENTER);
        buttonContainer = new HBox(INSIDE_PADDING);
        
        //Process Logs
        String s = "";
        for(String log:logs){
            s += log + "\n";
        }
        
        //Labels
        mainLabel = new Label(s);
        mainLabel.setTextFill(SELECTABLE_TEXT_COLOR);
        
        labelContainer.getChildren().addAll(mainLabel);
        
        //Buttons
        okButton = new Button("Ok");
        
        buttonContainer.getChildren().addAll(okButton);
        
        okButton.setOnAction(e -> {
            logWindow.close();
        });
        
        //Layout
        VBox layout = new VBox(INSIDE_PADDING);
        layout.setPadding(new Insets(INSIDE_PADDING));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(labelContainer, buttonContainer);
        layout.setBackground(new Background(new BackgroundFill(CONFIRMBOX_BACKGROUND_COLOR, new CornerRadii(5), Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        
        //Scene
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("de/ui/gui/css/DestinationEarthStyle.css");
        
        //Stage
        logWindow.setScene(scene);
        logWindow.showAndWait();
        
        return answer;
    }
}
