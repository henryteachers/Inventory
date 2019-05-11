package inventorypa;

import inventorypa.Model.Inhouse;
import inventorypa.Model.Inventory;
import inventorypa.Model.Outsourced;
import inventorypa.Model.Part;
import inventorypa.Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class InventoryPAGregHenry extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/mainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        
        Part part1 = new Inhouse(1, "Part 1", 9.99, 3, 2, 8, 15);
        Part part2 = new Inhouse(2, "Part 2", 11.99, 7, 2, 18, 31);
        Part part3 = new Outsourced(3, "Part 3", 39.99, 6, 2, 8, "Company 3");
        
        Product product1 = new Product(1, "Product 1", 9.99, 3, 2, 8);
        Product product2 = new Product(2, "Product 2", 17.99, 14, 2, 30);
        Product product3 = new Product(3, "Product 3", 109.99, 5, 2, 8);

        Inventory.getAllParts().add(part1);
        Inventory.getAllParts().add(part2);
        Inventory.getAllParts().add(part3);

        Inventory.getAllProducts().add(product1);
        Inventory.getAllProducts().add(product2);
        Inventory.getAllProducts().add(product3);
        
        product1.addAssociatedPart(part1);
        product2.addAssociatedPart(part2);
        product3.addAssociatedPart(part3);
        
        Inventory.uniquePartID = Inventory.getAllParts().size();
        Inventory.uniqueProductID = Inventory.getAllProducts().size();

        launch(args);
    }
    
}
