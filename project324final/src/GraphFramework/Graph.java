package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public abstract class Graph {

    // Defining the data feilds
    private static int vertexNo;
    private static int edgeNo;
    private static boolean isDiagraph;
    private static ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private static LinkedList<Edge>[] adjacencylist;

    
    //-----------------------------------------------------------------------------
    // Defining the constructor
    public Graph() {

    }

    public Graph(int edgeNo, int vno, boolean isDiagraph) {
        vertexNo = vno;
        this.edgeNo = edgeNo;
        this.isDiagraph = isDiagraph;

        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertexNo; i++) {
            vertices.add(createVertex(i));
        }

        // creating the adjacency list for each
        adjacencylist = new LinkedList[vertexNo];

        for (int i = 0; i < vertexNo; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
        
    }

    
    //-----------------------------------------------------------------------------
    // Defining the setters and getters
    public static int getVertexNo() {
        return vertexNo;
    }

    public static void setVertexNo(int vertexNo) {
        Graph.vertexNo = vertexNo;
    }

    public static int getEdgeNo() {
        return edgeNo;
    }

    public static void setEdgeNo(int edgeNo) {
        Graph.edgeNo = edgeNo;
    }

    public static boolean isIsDiagraph() {
        return isDiagraph;
    }

    public static void setIsDiagraph(boolean isDiagraph) {
        Graph.isDiagraph = isDiagraph;
    }

    public static ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public static void setVertices(ArrayList<Vertex> vertices) {
        Graph.vertices = vertices;
    }

    public static LinkedList<Edge> getAdjacencylist(int index) {
        return adjacencylist[index];
    }

    public static LinkedList<Edge>[] getAdjacencylist() {
        return adjacencylist;
    }

    public static void setAdjacencylist(LinkedList<Edge>[] adjacencylist) {
        Graph.adjacencylist = adjacencylist;
    }

    
    //-----------------------------------------------------------------------------
    // methods need to be overriden 
    public abstract Vertex createVertex(int label);

    public abstract Edge createEdge(Vertex source, Vertex target, int weight);

    //-----------------------------------------------------------------------------
    // method n takes as parameters the number of vertices and the number of edges
    // It is responsible for creating a graph object with the specified parameters 
    // and randomly initializes the vertices’ labels, creating edges that connects the created vertices randomly
    // and assigning them random weights alson Makeing sure that the resulting graph is connected. 
    public void makeGraph(int vertexNum, int eno, int isdia) {

        this.isDiagraph = isdia == 0 ? false : true;
        adjacencylist = new LinkedList[vertexNum];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertexNum; i++) {
            adjacencylist[i] = new LinkedList<>();
        }

        for (int i = 0; i < vertexNum; i++) {

            vertices.add(createVertex(i));
            // incrementing the vertices number as requested ! 
            vertexNo++;
        }

        // object of Random class
        Random randm = new Random();
        // ensure that all verts are connected
        for (int i = 0; i < vertexNum - 1; i++) {
            int weight = randm.nextInt(20) + 1;//generate random edge weights between 0-20
            addEdge(vertices.get(i), vertices.get(i + 1), weight);    //connect verts
            if (!isDiagraph) {
                addEdge(vertices.get(i + 1), vertices.get(i), weight);
            }
        }

        // generate edges bewteen verts with the remaining edges
        int remEdges = eno - (vertexNum - 1);

        for (int i = 0; i < remEdges; i++) {
            int source = randm.nextInt(vertexNo);
            int target = randm.nextInt(vertexNo);
            if (target == source || areConnected(vertices.get(source), vertices.get(target))) { // to avoid self loops and duplicate edges
                i--;
                continue;
            }
            // generate random weights in range 0 to 20
            int weight = randm.nextInt(20) + 1;
            // add edge to the graph
            addEdge(vertices.get(source), vertices.get(target), weight);

        }

    }

    // method to check if two vetices are connect with an edge 
    public static boolean areConnected(Vertex v1, Vertex v2) {

        // getting the size of the adjList that store the edges to ba able to through it 
        int size = v1.getAdjLists().size();

        // loop to got through the edges of a specefic vertex 
        for (int j = 0; j < size; j++) {

            // if one if the vertices is a source and the other is a target then they are connected (:
            if ((v1.getAdjLists().get(j).getSource() == v1 && v1.getAdjLists().get(j).getTarget() == v2)
                    || (v1.getAdjLists().get(j).getSource() == v2 && v1.getAdjLists().get(j).getTarget() == v1)) {
                return true;
            }

        }

        return false;
    }

    // method that reads the edges and vertices from the text file whose name is
    // specified by the parameter filename and place the data in the Graph
    public void readGraphFromFile(String fileName) throws FileNotFoundException {

        int eno = 0, vno = 0;

        // checking if the file exist and print 
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("File Does not exist !");
        }

        // creating 2 scanner object
        // scanner opject to read the data of the each edge
        Scanner input = new Scanner(f);

        // scanner object to read the labels of each vertex 
        Scanner input2 = new Scanner(f);

        input2.nextLine();
        input2.nextLine();

        input.next();

        isDiagraph = input.nextInt() == 0 ? false : true;

        eno = input.nextInt();
        vno = input.nextInt();

        adjacencylist = new LinkedList[vno];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vno; i++) {
            adjacencylist[i] = new LinkedList<>();
        }

        // list to store the labels 
        int[] listLabels = new int[vno];

        // counter act like index for the label list
        int counter = 0;

        // loop to store the distinct labels of each edge 
        for (int i = 0; i < eno; i++) {

            // reading the label 
            char ch = input2.next().charAt(0);

            // a flag to know wether to add the label to the list or not 
            boolean canAdd = true;

            // loop to go through the list labels
            // to check if the label is already exist
            for (int j = 0; j < listLabels.length; j++) {

                if (listLabels[j] == (ch - 'A')) {

                    canAdd = false;
                    break;
                }
            }

            input2.nextLine();

            // if the flag = true , then we can add the label 
            // and increment the index to the next place for another label 
            if (canAdd) {
                listLabels[counter] = ch - 'A';
                counter++;
            }
        }

        // Adding the vertices to its list in the graph
        for (int i = 0; i < vno; i++) {

            vertices.add(createVertex(listLabels[i]));

            // incrementing the vertices number as requested ! 
            vertexNo++;
        }

        // Adding the edges 
        // creating and initializing the needed variables 
        char label1, label2;

        Vertex v1 = null, v2 = null;

        // loop to go through each edge in the file 
        for (int i = 0; i < eno; i++) {

            // reading the labels for the source and target vetices 
            label1 = input.next().charAt(0);
            label2 = input.next().charAt(0);

            // loop to go through the vetices list 
            // to get the vetices objects with same readen labels
            for (int j = 0; j < vertices.size(); j++) {

                // if the current vertixs’s labels is the same labels 
                if (vertices.get(j).getLabel() == label1 - 'A') {
                    v1 = vertices.get(j);
                } else if (vertices.get(j).getLabel() == label2 - 'A') {
                    v2 = vertices.get(j);
                }

            }

            // checking if both variables are not null , to not cause null pointer exeption !
            if (v1 != null && v2 != null) {

                // adding the new edge   
                addEdge(v1, v2, input.nextInt());

            }

        }

    }

    
    //-----------------------------------------------------------------------------
    // Method to add an edge to the graph 
    // It will get the source vertex , target vertex and the weight
    public void addEdge(Vertex source, Vertex target, int weight) {

        // creating the edge needed using the overriden method 
        Edge edge = createEdge(source, target, weight);

        // checking if the graph is directed
        if (isDiagraph) {

            // adding the edge to the list of edges in the source vertex  S --> T
            source.getAdjLists().add(edge);

            // adding the edge to the list of edges of the graph 
            adjacencylist[source.getLabel()].addFirst(edge);

            // incrementing the number of edges in the graph by 1 
            edgeNo++;

            // if the graph is not dirceted 
            // and from taget to source then both assign from source to taget
        } else {

            // creating another edge , so the edge will b from both sides S --> <-- T
            Edge edge2 = createEdge(target, source, weight);

            // adding the edge to the list of edges in the source vertex  S --> T
            source.getAdjLists().add(edge);

            // adding the edge to the list of edges in the source vertex  S <-- T
            target.getAdjLists().add(edge2);

            // adding the edge to the list of edges of the graph S --> T 
            adjacencylist[source.getLabel()].addFirst(edge);

            // adding the edge to the list of edges of the graph S <-- T 
            adjacencylist[target.getLabel()].addFirst(edge2);

            // incrementing the number of edges in the graph by 2
            edgeNo += 2;

        }
    }
    
}
