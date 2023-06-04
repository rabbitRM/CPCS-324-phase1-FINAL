package PhoneNetworkApp;

import GraphFramework.Vertex;

public class Office extends Vertex {

    
    // Defining the constructor 
    public Office(int lable) {
     
        super(lable);
    
    }
    
    
    //-----------------------------------------------------------------------------
    // method to attach O to the label
    public String setLabelO(int label) {
        
        return "O" + label;
        
    }
    
    @Override
    // Method to display the information of the class attributes
    public String displayInfo(boolean isReq1) {
       
        // if the method was invoked in Requirment1 then display the label as normal Letter
        if (isReq1) 
        
            return "Office No." + (char) (super.getLabel() + 'A');
        
         // if the method was invoked in Requirment2 then display the label as O + Letter 
         else 
        
            return "Office No." + setLabelO(getLabel());
        
    }

}
