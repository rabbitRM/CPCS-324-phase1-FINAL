package GraphFramework;

public abstract class Edge implements Comparable<Edge> {

    // Defining the data feilds
    private int weight;
    private Vertex source;
    private Vertex target;
 

    //-----------------------------------------------------------------------------
    // Defining the constructor 
    public Edge() {

    }

    public Edge(Vertex source, Vertex target, int weight) {

        this.weight = weight;
        this.source = source;
        this.target = target;

    }

    public Edge(Vertex source, int weight) {

        this.weight = weight;
        this.source = source;

    }

    
    //-----------------------------------------------------------------------------
    // Defining the setters and getters
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }
    
    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public abstract int getWeight();

    
    //-----------------------------------------------------------------------------
    // Defining the method that should be overriden in the application framework
    public abstract String displayInfo();

    
    //-----------------------------------------------------------------------------
    // Defining the method that will compare between 2 edges 
    public int compareTo(Edge other) {
        
        return Integer.compare(weight, other.weight);
        
    }

}
