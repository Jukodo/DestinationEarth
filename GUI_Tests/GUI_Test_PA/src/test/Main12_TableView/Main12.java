package test.Main12_TableView;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main12 extends Application{
    
    Stage mainWindow;
    TableView<Product> table;
    TextField nameInput, priceInput, quantityInput;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        mainWindow.setTitle("DE");
        
        //Name Column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //Price Column
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Quantity Column
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        //Name Input
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);
        
        //Price Input
        priceInput = new TextField();
        priceInput.setPromptText("Price");
        priceInput.setMinWidth(100);
        
        //Quantity Input
        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");
        quantityInput.setMinWidth(100);
        
        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButton_Clicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButton_Clicked());
        
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        
        hBox.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton);
        
        table = new TableView<>();
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
        
        VBox layout = new VBox(/*V Spacing*/);
        layout.getChildren().addAll(table, hBox);
        
        Scene scene = new Scene(layout);
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    private void addButton_Clicked(){
        Double price;
        int quantity;
        try{
            price = Double.parseDouble(quantityInput.getText());
        }catch(NumberFormatException e){
            return;
        }
        try{
            quantity = Integer.parseInt(priceInput.getText());
        }catch(NumberFormatException e){
            return;
        }
        
        table.getItems().add(new Product(nameInput.getText(), price, quantity));
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }
    
    private void deleteButton_Clicked(){
        ObservableList<Product> allProducts, selectedProducts;
        
        allProducts = table.getItems();
        selectedProducts = table.getSelectionModel().getSelectedItems();
        
        allProducts.removeAll(selectedProducts);
        
        /*Or just
        table.getItems().removeAll(table.getSelectionModel().getSelectedItems());*/
    }
    
    //Get all products
    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        
        products.add(new Product("A", 1.02, 2));
        products.add(new Product("B", 2.10, 8));
        products.add(new Product("C", 3.32, 12));
        products.add(new Product("D", 4.20, 4));
        products.add(new Product("E", 5.50, 10));
        
        return products;
    }
}
