package test.Main13_Menu;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main13 extends Application{
    
    Stage mainWindow;
    BorderPane layout;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        /*Menu Items Creation START*/
        //File Menu
        Menu fileMenu = new Menu("_File");
            //New File
        MenuItem newFile = new MenuItem("New...");
        newFile.setOnAction(e -> System.out.println("Create new file..."));
        fileMenu.getItems().add(newFile);
            //Open File
        MenuItem openFile = new MenuItem("Open...");
        openFile.setOnAction(e -> System.out.println("Open file..."));
        fileMenu.getItems().add(openFile);
            //Save File
        MenuItem saveFile = new MenuItem("Save...");
        saveFile.setOnAction(e -> System.out.println("Save file..."));
        fileMenu.getItems().add(saveFile);
        fileMenu.getItems().add(new SeparatorMenuItem());
            //Open Settings
        MenuItem openSettings = new MenuItem("Settings...");
        openSettings.setOnAction(e -> System.out.println("Open settings..."));
        fileMenu.getItems().add(openSettings);
        fileMenu.getItems().add(new SeparatorMenuItem());
            //Exit
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.out.println("Exit..."));
        fileMenu.getItems().add(exit);

        //Edit Menu
        Menu editMenu = new Menu("_Edit");
            //Cut Text
        MenuItem cutText = new MenuItem("Cut");
        cutText.setOnAction(e -> System.out.println("Cut text..."));
        editMenu.getItems().add(cutText);
            //Copy Text
        MenuItem copyText = new MenuItem("Copy");
        copyText.setOnAction(e -> System.out.println("Copy text..."));
        editMenu.getItems().add(copyText);
            //Paste Text
        MenuItem pasteText = new MenuItem("Paste");
        pasteText.setOnAction(e -> System.out.println("Paste text..."));
        /*Basically can't click
        pasteText.setDisable(true);*/
        editMenu.getItems().add(pasteText);
        
        //Options Menu
        Menu helpMenu = new Menu("_Help");
            //Show Lines
        CheckMenuItem showLines = new CheckMenuItem("Show Line Numbers");
        showLines.setOnAction(e -> {
            if(showLines.isSelected())
                System.out.println("Show line numbers...");
            else
                System.out.println("Dont show line numbers");
        });
        showLines.setSelected(true);
        helpMenu.getItems().add(showLines);
        
        //Difficulty Radio Menu
        Menu difficultyMenu = new Menu("_Difficulty");
        ToggleGroup difficultyToggle = new ToggleGroup();
            //Easy
        RadioMenuItem easyRadio = new RadioMenuItem("Easy");
            //Medium
        RadioMenuItem mediumRadio = new RadioMenuItem("Medium");
            //Hard
        RadioMenuItem hardRadio = new RadioMenuItem("Hard");
        
        easyRadio.setToggleGroup(difficultyToggle);
        mediumRadio.setToggleGroup(difficultyToggle);
        hardRadio.setToggleGroup(difficultyToggle);
        
        difficultyMenu.getItems().addAll(easyRadio, mediumRadio, hardRadio);
        /*Menu Items Creation END*/
        
        //Main Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu, difficultyMenu);
        
        layout = new BorderPane();
        layout.setTop(menuBar);
        
        Scene scene = new Scene(layout, 400, 300);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
}