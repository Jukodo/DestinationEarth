package test.Main14_CSS;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main14 extends Application{
    
    Stage mainWindow;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        //Name label
        Label nameLabel = new Label("Username:");
        nameLabel.setId("bold-label");
        GridPane.setConstraints(nameLabel, 0, 0);
        
        //Name input
        TextField nameInput = new TextField(/*Default text*/);
        nameInput.setPromptText("Username"); //Placeholder
        GridPane.setConstraints(nameInput, 1, 0);
        
        //Age label
        Label ageLabel = new Label("Age:");
        GridPane.setConstraints(ageLabel, 0, 1);
        
        //Age input
        TextField ageInput = new TextField(/*Default text*/);
        ageInput.setPromptText("Age"); //Placeholder
        GridPane.setConstraints(ageInput, 1, 1);
        
        //Password label
        Label pwdLabel = new Label("Password:");
        GridPane.setConstraints(pwdLabel, 0, 2);
        
        //Password input
        PasswordField pwdInput = new PasswordField(/*Default text*/);
        pwdInput.setPromptText("Password"); //Placeholder
        GridPane.setConstraints(pwdInput, 1, 2);
        
        //Login button
        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton, 1, 3);
        
        //Sign up button
        Button signupButton = new Button("Sign up");
        signupButton.getStyleClass().add("button-blue");//Add custom class name
        GridPane.setConstraints(signupButton, 0, 3);
        
        grid.getChildren().addAll(nameLabel, nameInput, ageLabel, ageInput, pwdLabel, pwdInput, loginButton, signupButton);
        
        Scene scene = new Scene(grid);
        scene.getStylesheets().add("test/General/Main14Form.css");//Set CSS
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    public boolean isInt(TextField ageInput, String msg){
        try{
            int age = Integer.parseInt(msg);
            return true;
        }catch(NumberFormatException e){
            System.out.println("\"" + msg + "\" is not a number");
            return false;
        }
    }
}
