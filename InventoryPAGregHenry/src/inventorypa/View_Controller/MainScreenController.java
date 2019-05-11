
package inventorypa.View_Controller;

import inventorypa.Model.Inventory;
import inventorypa.Model.Part;
import inventorypa.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MainScreenController implements Initializable {

    @FXML
    private Button SearchParts;
    @FXML
    private Button Exit;
    @FXML
    private TextField PartSearchField;
    @FXML
    private TableView<Part> PartsTable;
    @FXML
    private TableColumn<Part, Integer> PartID;
    @FXML
    private TableColumn<Part, String> PartName;
    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;
    @FXML
    private TableColumn<Part, Double> PartPricePerUnit;
    @FXML
    private Button SearchProducts;
    @FXML
    private TextField ProductSearchField;
    @FXML
    private TableView<Product> ProductsTable;
    @FXML
    private TableColumn<Product, Integer> ProductID;
    @FXML
    private TableColumn<Product, String> ProductName;
    @FXML
    private TableColumn<Product, Integer> ProductInventoryLevel;
    @FXML
    private TableColumn<Product, Double> ProductPricePerUnit;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        ProductsTable.setItems(Inventory.getAllProducts());
        PartsTable.setItems(Inventory.getAllParts());        
        
        ProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        ProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        PartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));        
    }    

    @FXML
    private void searchParts(ActionEvent event) {        
    String searchItem = PartSearchField.getText();
    boolean found=false;
    try{
        int itemNumber = Integer.parseInt(searchItem);
        for(Part p: Inventory.getAllParts()){
            if(p.getPartID() == itemNumber){
                found=true;
                ObservableList<Part> tempParts = FXCollections.observableArrayList();
                tempParts.add(p);                
                PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
                PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
                PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                PartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
                PartsTable.setItems(tempParts);            
            }            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");
            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(Part p: Inventory.getAllParts()){
            if(p.getName().equals(searchItem)){
                found=true;
                ObservableList<Part> tempParts = FXCollections.observableArrayList();
                tempParts.add(p);                
                PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
                PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
                PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                PartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
                PartsTable.setItems(tempParts);
            }            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");
            alert.showAndWait();
        }
    }
    }
    
    @FXML
    private void exitButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){  
            System.exit(0);
        }
    }

    @FXML
    private void addPart(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) SearchParts.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
            "addPartScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyPart(ActionEvent event) throws IOException {
        Inventory.partToModify = (PartsTable.getSelectionModel().getSelectedItem());
        Stage stage;
        Parent root;
        stage = (Stage) SearchParts.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
            "modifyPartScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deletePart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){  
            ObservableList<Part> tempParts = FXCollections.observableArrayList();        
            tempParts = PartsTable.getItems();        
            tempParts.remove(PartsTable.getSelectionModel().getSelectedItem()); 
            PartsTable.setItems(tempParts);
            Inventory.setAllParts(tempParts);
        }
    }

    @FXML
    private void searchProducts(ActionEvent event) {    
    String searchItem = ProductSearchField.getText();
    boolean found=false;
    try{
        int itemNumber = Integer.parseInt(searchItem);
        for(Product p: Inventory.getAllProducts()){
            if(p.getProductID() == itemNumber){
                found=true;
                ObservableList<Product> tempProducts = FXCollections.observableArrayList();
                tempProducts.add(p);                
                ProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
                ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
                ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                ProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
                ProductsTable.setItems(tempProducts);            
            }            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Product not found");
            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(Product p: Inventory.getAllProducts()){
            if(p.getName().equals(searchItem)){
                found=true;
                ObservableList<Product> tempProducts = FXCollections.observableArrayList();
                tempProducts.add(p);                
                ProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
                ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
                ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                ProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
                ProductsTable.setItems(tempProducts);
            }            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Product not found");
            alert.showAndWait();
        }
    }
    }    

    @FXML
    private void addProduct(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) SearchParts.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
            "addProductScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyProduct(ActionEvent event) throws IOException {
        Inventory.productToModify = (ProductsTable.getSelectionModel().getSelectedItem());
        Stage stage;
        Parent root;
        stage = (Stage) SearchParts.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
            "modifyProductScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){  
            ObservableList<Product> tempProducts = FXCollections.observableArrayList();        
            tempProducts = ProductsTable.getItems();        
            tempProducts.remove(ProductsTable.getSelectionModel().getSelectedItem()); 
            ProductsTable.setItems(tempProducts);
            Inventory.setAllProducts(tempProducts);
        }
    }
    
}
