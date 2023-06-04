package GraphFramework;

import java.util.LinkedList;

public abstract class Vertex {

    // Defining the data feilds
    private int label;
    private boolean isVisited;
    private LinkedList<Edge> adjLists = new LinkedList<>();

    
    //-----------------------------------------------------------------------------
    // Defining the constructor
    public Vertex() {

    }

    public Vertex(int label) {
        
        this.label = label;
        isVisited = false;

    }

  
    //-----------------------------------------------------------------------------
    // Defining the setters and getters
    public void setLabel(int label) {
        this.label = label;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setAdjLists(LinkedList<Edge> adjLists) {
        this.adjLists = adjLists;
    }

    public int getLabel() {
        return label;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public LinkedList<Edge> getAdjLists() {
        return adjLists;
    }

    //-----------------------------------------------------------------------------
    // Method to get the edge that have the minimum weight 
    public Edge getMinEdge() {
        
        // creating and initializing the variables needed
        int minCost = Integer.MAX_VALUE;
        Edge minEdge = null;
        
        // loop to go through the list of edges 
        for (int i = 0; i < adjLists.size(); i++) {
            
            // checking if the weight of the the current edge is less than the minCost
            if (adjLists.get(i).getWeight() < minCost) {
                
                // assign the weight to the minimum cost
                minCost = adjLists.get(i).getWeight();
                
                // assign the current edge to minimum edge
                minEdge = adjLists.get(i);
            }
        }
        
        return minEdge;
    }

    //-----------------------------------------------------------------------------
    // Defining the method that should be overriden in the application framework
    public abstract String displayInfo(boolean isReq1);

}
