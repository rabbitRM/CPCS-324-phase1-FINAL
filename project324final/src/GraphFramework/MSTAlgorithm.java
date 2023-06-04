package GraphFramework;

import java.util.ArrayList;

public abstract class MSTAlgorithm {

    // Defining the data feilds
    protected ArrayList<GraphFramework.Edge> MSTResultList = new ArrayList<GraphFramework.Edge>();
    protected static Graph graph;

    //-----------------------------------------------------------------------------
    // Defining the setters and getters
    public ArrayList<Edge> getMSTResultList() {
        return MSTResultList;
    }

    public void setMSTResultList(ArrayList<Edge> MSTResultList) {
        this.MSTResultList = MSTResultList;
    }

    public static Graph getGraph() {
        return graph;
    }

    public static void setGraph(Graph graph) {
        MSTAlgorithm.graph = graph;
    }

    
    //-----------------------------------------------------------------------------
    // Defining the method that should be overriden in the prim and kruskal algorithms classes
    public abstract void displayResultingMST(ArrayList<GraphFramework.Edge> MSTResultList, boolean isReq1);

}
