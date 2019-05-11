/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorypa.View_Controller;

import inventorypa.Model.Inhouse;
import inventorypa.Model.Inventory;
import inventorypa.Model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPartScreenController implements Initializable {

    @FXML
    private RadioButton inHousePart;
    @FXML
    private RadioButton outSourcePart;
    @FXML
    private Label companyNameLabel;
    @FXML
    private TextField partID;
    @FXML
    private TextField partName;
    @FXML
    private TextField inventory;
    @FXML
    private TextField priceCost;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private TextField companyNameField;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inventory.uniquePartID += 1;
        partID.setText(Integer.toString(Inventory.uniquePartID));
    }    

    @FXML
    private void inHouse(ActionEvent event) {
        companyNameLabel.setText("Machine ID");

    }

    @FXML
    private void outSourced(ActionEvent event) {
        companyNameLabel.setText("Company Name");
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
            if(inHousePart.isSelected()) {
            Inhouse newPart = new Inhouse(
                Integer.parseInt(partID.getText()), partName.getText(),
                Double.parseDouble(priceCost.getText()), Integer.parseInt(inventory.getText()),
                Integer.parseInt(min.getText()), Integer.parseInt(max.getText()),
                Integer.parseInt(companyNameField.getText()));           
            Inventory.getAllParts().add(newPart);
            }
            else if(outSourcePart.isSelected()) {
                Outsourced newPart = new Outsourced(
                    Integer.parseInt(partID.getText()), partName.getText(),
                    Double.parseDouble(priceCost.getText()), Integer.parseInt(inventory.getText()),
                    Integer.parseInt(min.getText()), Integer.parseInt(max.getText()),
                    companyNameField.getText());           
                Inventory.getAllParts().add(newPart);
            }
            Stage stage;
            Parent root;
            stage = (Stage) inHousePart.getScene().getWindow();
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
            Inventory.uniquePartID -= 1;        
            Stage stage;
            Parent root;
            stage = (Stage) inHousePart.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
