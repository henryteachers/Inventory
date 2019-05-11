
package inventorypa.Model;


public abstract class Part {
    
    //private fields
    private int partID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    //constructor for subclasses
    public Part(int partID, String name, double price,
            int inStock, int min, int max) {
        this.partID = partID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    
    public void setName(String myName) {
        name = myName;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPrice(double myPrice) {
        price = myPrice;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setInStock(int myInStock) {
        inStock = myInStock;
    }
    
    public int getInStock() {
        return inStock;
    }
    
    public void setMin(int myMin) {
        min = myMin;
    }
    
    public int getMin() {
        return min;
    }
    
    public void setMax(int myMax) {
        max = myMax;
    }
    
    public int getMax() {
        return max;
    }
       
    public void setPartID(int myPartID) {
        partID = myPartID;
    }
    
    public int getPartID() {
        return partID;
    }
}
