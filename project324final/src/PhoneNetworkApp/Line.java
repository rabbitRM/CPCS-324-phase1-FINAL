
package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;

public class Line extends Edge{
    
    // Defining the data feilds
    private int lLength ;
    
    
    //-----------------------------------------------------------------------------
    // Defining the constructor 
    public Line( Vertex source, Vertex target , int weight) {
      
        // Calling the parent class
        super ( source , target , weight);
     
        // storing 5 times of the weight in Line length variable 
        lLength = weight *5;
   
    }
    
    // Method to display the information of the length
    @Override
    public String displayInfo(){
        return "line length: "+ lLength ;
    }

    // Method to get the weight
    @Override
    public int getWeight() {
       return lLength;
    }

}
