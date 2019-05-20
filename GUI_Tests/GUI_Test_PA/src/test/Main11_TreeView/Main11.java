package test.Main11_TreeView;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main11 extends Application{
    
    Stage mainWindow;
    Scene scene;
    TreeView<String> treeView;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        TreeItem<String> root, pointA, pointB;
        
        //Root
        root = new TreeItem<>();
        root.setExpanded(false);//Starts expanded
        
        //PointA
        pointA = makeBranch("pointA", root);
        makeBranch("pointA_1", pointA);
        makeBranch("pointA_2", pointA);
        makeBranch("pointA_3", pointA);
        
        //PointB
        pointB = makeBranch("pointB", root);
        makeBranch("pointB_1", pointB);
        makeBranch("pointB_2", pointB);
        makeBranch("pointB_3", pointB);
        
        //Create Tree
        treeView = new TreeView<>(root);
        treeView.setShowRoot(false);
        
        treeView.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    if(newValue != null)
                        System.out.println(newValue.getValue());
                });
        
        StackPane layout = new StackPane();
        layout.getChildren().add(treeView);
        
        scene = new Scene(layout, 300, 250);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    private TreeItem<String> makeBranch(String name, TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
