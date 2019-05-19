package test.Main4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import test.General.ConfirmBox;

public class Main4 extends Application{
    
    Stage mainWindow;
    Button button;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        mainWindow.setOnCloseRequest(e -> {
            e.consume();//Take control of the rest (prevents internal shutdown process)
            closeProgram();
        });
        
        button = new Button("Close");
        button.setOnAction(e -> {
            closeProgram();
        });
        
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        
        Scene scene = new Scene(layout, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    private void closeProgram(){
        boolean answer = ConfirmBox.display("Leave?", "Are you sure you want to leave?");
        if(answer)
            mainWindow.close();
    }
}
