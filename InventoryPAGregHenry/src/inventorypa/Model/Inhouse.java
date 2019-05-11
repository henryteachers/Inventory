
package inventorypa.Model;


public class Inhouse extends Part {
    
    //private fields
    int machineID;
    
    //constructor
    public Inhouse (int myPartID, String myName, double myPrice,
            int myInStock, int myMin, int myMax, int myMachineID) {
        super(myPartID, myName, myPrice, myInStock, myMin, myMax);
        machineID = myMachineID;
    }
      
    //public methods
    public int getMachineID() {
        return machineID;
    }
    
    public void setMachineID(int myMachineID) {
        machineID = myMachineID;
    }
}
