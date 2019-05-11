
package inventorypa.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static int uniquePartID;
    public static int uniqueProductID;
    public static Part partToModify;
    public static Product productToModify;
        
    //constructor
    public Inventory() {  
    }
    
    //public methods
    public static ObservableList<Product> getAllProducts() {
        return products;
    }
    
    public static void setAllProducts(ObservableList<Product> productsList) {
        products = productsList;
    }
    
    public static void setAllParts(ObservableList<Part> partsList) {
        allParts = partsList;
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public void addProduct(Product product) {
        products.add(product);
    }
    
    public boolean removeProduct(int location) {
        if (location < products.size()) {
            products.remove(location);
            return true;
        }
        else return false;
    }
    
    public Product lookupProduct(int location) {
        return products.get(location);
    }
    
    public void updateProduct(int location) {
        
    }
    
    public void addPart(Part part) {
        allParts.add(part);
    }
    
    public boolean deletePart(Part part) {
        boolean found = false;
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i) == part) {
            allParts.remove(i);
            found = true;
            break;
            }
        }
        return found;
    }
    
    public Part lookupPart(int location) {
        return allParts.get(location);
    }
    
    public void updatePart(int location) {
        
    }
    
 
}
