
package inventorypa.Model;


public class Outsourced extends Part {
    
    //private fields
    String companyName;
    
    //constructor
    public Outsourced (int myPartID, String myName, double myPrice,
            int myInStock, int myMin, int myMax, String myCompanyName) {
        super(myPartID, myName, myPrice, myInStock, myMin, myMax);
        companyName = myCompanyName;
    }
    
    //public methods
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String myCompanyName) {
        companyName = myCompanyName;
    }
}
