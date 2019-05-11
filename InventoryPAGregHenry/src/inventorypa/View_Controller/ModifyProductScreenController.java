
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

public class ModifyProductScreenController implements Initializable {

    @FXML
    private TextField productID;
    @FXML
    private TextField productName;
    @FXML
    private TextField inventory;
    @FXML
    private TextField price;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private Button SearchButton;
    @FXML
    private TextField SearchField;
    @FXML
    private TableView<Part> AddTable;
    @FXML
    private TableColumn<?, ?> AddPartID;
    @FXML
    private TableColumn<?, ?> AddPartName;
    @FXML
    private TableColumn<?, ?> AddInv;
    @FXML
    private TableColumn<?, ?> AddPrice;
    @FXML
    private TableView<Part> DeleteTable;
    @FXML
    private TableColumn<?, ?> DeletePartID;
    @FXML
    private TableColumn<?, ?> DeletePartName;
    @FXML
    private TableColumn<?, ?> DeleteInv;
    @FXML
    private TableColumn<?, ?> DeletePrice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productID.setText(Integer.toString(Inventory.productToModify.getProductID()));
        productName.setText(Inventory.productToModify.getName());
        inventory.setText(Integer.toString(Inventory.productToModify.getInStock()));
        price.setText(Double.toString(Inventory.productToModify.getPrice()));
        max.setText(Integer.toString(Inventory.productToModify.getMax()));
        min.setText(Integer.toString(Inventory.productToModify.getMin())); 
        
        AddTable.setItems(Inventory.productToModify.getAssociatedParts());
        AddPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        AddPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        AddPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        DeleteTable.setItems(Inventory.getAllParts());
        DeletePartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        DeletePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DeleteInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        DeletePrice.setCellValueFactory(new PropertyValueFactory<>("price"));        
    }    

    @FXML
    private void save(ActionEvent event) throws IOException {
        if(Integer.parseInt(min.getText()) >= Integer.parseInt(max.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Make sure the minimum is less than the maximum.");
            alert.showAndWait();
        }
        else if(Integer.parseInt(inventory.getText()) < Integer.parseInt(min.getText()) ||
                    Integer.parseInt(inventory.getText()) > Integer.parseInt(max.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Make sure the inventory is greater than the minimum "
                    + "and less than the maximum.");
            alert.showAndWait();
        }
        else {
            Inventory.getAllProducts().remove(Inventory.productToModify);
            Product newProduct = new Product(
                Integer.parseInt(productID.getText()), productName.getText(),
                Double.parseDouble(price.getText()), Integer.parseInt(inventory.getText()),
                Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));
            newProduct.setAssociatedParts(Inventory.productToModify.getAssociatedParts());
            Inventory.getAllProducts().add(newProduct);
            Stage stage;
            Parent root;
            stage = (Stage) SearchButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){  
            Stage stage;        
            Parent root;
            stage = (Stage) SearchButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    }

    @FXML
    private void searchProduct(ActionEvent event) {
        String searchItem = SearchField.getText();
        boolean found=false;
        try{
            int itemNumber = Integer.parseInt(searchItem);
            for(Part p: Inventory.getAllParts()){
                if(p.getPartID() == itemNumber){
                    found=true;
                    ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();
                    tempAssociatedParts.add(p);                
                    DeletePartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
                    DeletePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    DeleteInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                    DeletePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                    DeleteTable.setItems(tempAssociatedParts);          
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
                    ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();
                    tempAssociatedParts.add(p);                
                    DeletePartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
                    DeletePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    DeleteInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                    DeletePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                    DeleteTable.setItems(tempAssociatedParts);             
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
    private void addPart(ActionEvent event) {
        Part addThePart = (DeleteTable.getSelectionModel().getSelectedItem());
        Inventory.productToModify.addAssociatedPart(addThePart);
        AddTable.setItems(Inventory.productToModify.getAssociatedParts());
        AddPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        AddPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        AddPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
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
            tempParts = AddTable.getItems();        
            tempParts.remove(AddTable.getSelectionModel().getSelectedItem()); 
            AddTable.setItems(tempParts);
        }
    }
    
}
