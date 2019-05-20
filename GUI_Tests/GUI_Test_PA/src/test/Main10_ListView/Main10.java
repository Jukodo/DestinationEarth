package test.Main10_ListView;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main10 extends Application{
    
    Stage mainWindow;
    Scene scene;
    Button button;
    ListView<String> listView;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        button = new Button("Submit");
        
        listView = new ListView<>();
        listView.getItems().addAll(
                "Iron Man",
                "Titanic",
                "Mad Max"
        );
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        button.setOnAction(e -> buttonClicked());
        
        VBox layout = new VBox(10 /*V Spacing*/);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(listView, button);
        
        scene = new Scene(layout, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    //Print listView items
    public void buttonClicked(){
        String message = "Items: ";
        //"Parent" of most Lists
        ObservableList<String> items;
        
        items = listView.getSelectionModel().getSelectedItems();
        
        for(String i:items){
            message += "\n" + i;
        }
        System.out.println(message);
    }
}
