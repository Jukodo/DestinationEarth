package test.Main03_AlertBox_ConfirmBox;

import test.General.ConfirmBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main3 extends Application{

    Stage mainWindow;
    Button button;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        button = new Button("Click");
        button.setOnAction(e -> {
            boolean result = ConfirmBox.display("OMG", "does it work?");
            System.out.println(result);
        });
        
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        
        Scene scene = new Scene(layout, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    
}
