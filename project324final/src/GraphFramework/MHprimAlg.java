/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

import java.util.ArrayList;
import java.util.LinkedList;

public class MHprimAlg extends MSTAlgorithm {

    // Defining the constructor 
    public MHprimAlg(Graph graph) {
        
        this.graph = graph;
        
    }

    //---------------------------------------------------------------------------------------------
    // method to diplay the MST information 
    // method that takes an `ArrayList` of `Edge` objects as its input parameter , as well as a boolean variable
    // The method doesn't return anything, 
    // but instead print the information of the `MSTResultList` 
    public void displayResultingMST(ArrayList<Edge> MSTResultList, boolean isReq1) {

        // loop to go throuh the list that save the resulting edges 
        for (int i = 0; i < MSTResultList.size(); i++) {

            System.out.println(MSTResultList.get(i).getSource().displayInfo(isReq1) + " - " + MSTResultList.get(i).getTarget().displayInfo(isReq1)
                    + " : " + MSTResultList.get(i).displayInfo());

        }
    }

    //---------------------------------------------------------------------------------------------
    class HeapNode {

        // Defining the data feilds
        Vertex vertex;
        int key;

    }

    //---------------------------------------------------------------------------------------------
    class ResultSet {

        // Defining the data feilds
        Vertex parent;
        Vertex destination;
        int weight;

    }

    //---------------------------------------------------------------------------------------------
    // method to perform the PRIM Algorithem 
    // method that takes a boolean variable as its input parameter
    // it updates the `MSTResultList` with MST and return `totalCost`
    public int prim(boolean isReq1) {

        // creating a new ArrayList to store the resulted MST
        MSTResultList = new ArrayList<GraphFramework.Edge>();
        
        // creaing boolean array to know if the vertex is in heap or not
        boolean[] inHeap = new boolean[graph.getVertices().size()];
        
        // creating an  array of object ResultSet to store the resulted vertices 
        ResultSet[] resultSet = new ResultSet[graph.getVertices().size()];
       
        // creating an array of integer to store the key for each vertex
        int[] key = new int[graph.getVertices().size()];
        
        //creating an array of objects HeapNode to represent the vertices in the heap 
        HeapNode[] heapNodes = new HeapNode[graph.getVertices().size()];
        
        //loop to go through all the vertices 
        for (int i = 0; i < graph.getVertices().size(); i++) {
            
            // creating heapNode to represent each vertex with its required default values
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = graph.createVertex(i);
            heapNodes[i].key = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
            resultSet[i].parent = null;
            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
            
        }

        // decreasing the key for the first vertex 
        heapNodes[0].key = 0;

        // creating a minHeap object with the size of the vertises
        MinHeap minHeap = new MinHeap(graph.getVertices().size());
        
        // loop to go through all the verices 
        for (int i = 0; i < graph.getVertices().size(); i++) {
            
            // adding all the vertices to the MinHeap
            minHeap.insert(heapNodes[i]);
            
        }

        //while minHeap is not empty
        while (!minHeap.isEmpty()) {
            
            //extracting the mininum HeapNode
            HeapNode extractedNode = minHeap.extractMin();

            //getting the vertex from the HeapNode
            Vertex extractedVertex = extractedNode.vertex;
            
            // marking it as unvisited
            inHeap[extractedVertex.getLabel()] = false;

            //creating a list to save the adjacent edges 
            LinkedList<Edge> list = graph.getAdjacencylist(extractedVertex.getLabel());
            
            // loop to go through the adjacent edges in the list
            for (int i = 0; i < list.size(); i++) {
                
                // saving the edge in a variable
                Edge edge = list.get(i);
                
                //checking if the target of the edge is in the heap 
                if (inHeap[edge.getTarget().getLabel()]) {

                    // saving the target m and the weight in a variable 
                    Vertex destination = edge.getTarget();
                    int newKey = edge.getWeight();
                    
                   
                    // checking if the stored key is larger than the new key 
                    if (key[destination.getLabel()] > newKey) {
                        
                        // decreasing in the minHeaph
                        decreaseKey(minHeap, newKey, destination);
                        
                        // updating the parent node for destination
                        resultSet[destination.getLabel()].parent = extractedVertex;
                        resultSet[destination.getLabel()].destination = edge.getTarget();
                        resultSet[destination.getLabel()].weight = newKey;
                        key[destination.getLabel()] = newKey;
                        
                    }
                }
            }
        }

        // creating a variable to compute the cost of MST
        int cost = 0;
        
        // loop to go through all the vertices in MST stored in resultSet
        for (int i = 1; i < graph.getVertices().size(); i++) {

            // adding the edges that construct the MST to MSTResultList
            MSTResultList.add(graph.createEdge(resultSet[i].parent, resultSet[i].destination, resultSet[i].weight / 5));

        }

        // loop to go through the edges in MSTResultList 
        for (int i = 0; i < MSTResultList.size(); i++) {

            // computing the cost of the MST
            cost += MSTResultList.get(i).getWeight();
        }

        // invoking the method that will display the MST
        displayResultingMST(MSTResultList, isReq1);
        
        
        return cost;
        
    }

    
    //---------------------------------------------------------------------------------------------
    // method to decrease a minHeap node
    public void decreaseKey(MinHeap minHeap, int newKey, Vertex vertex) {

        //getting the index of the heapNOde corresponding to the gives vertex
        int index = minHeap.indexes[vertex.getLabel()];

        //getting the heapNode
        HeapNode node = minHeap.minHeap[index];
        
        //updating its key
        node.key = newKey;
        
        // invoking bubbleUp to ensure the heapNode is in correct position 
        minHeap.bubbleUp(index);
        
    }

    
    //---------------------------------------------------------------------------------------------
    class MinHeap {

        int capacity;
        int currentSize;
        HeapNode[] minHeap;
        int[] indexes; //will be used to decrease the key

        
        //---------------------------------------------------------------------------------------------
        // Definging the constructor  
        public MinHeap(int capacity) {
            
            this.capacity = capacity;
            minHeap = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            minHeap[0] = new HeapNode();
            minHeap[0].key = Integer.MIN_VALUE;
            minHeap[0].vertex = null;
            currentSize = 0;
            
        }

      
        //---------------------------------------------------------------------------------------------
        // method to add a new heapNode to the minHeap
        public void insert(HeapNode x) {
            
            // increasing the size of the minHeap , 
            // storing the heapNode at the next available index , storing the index in the indexes arrau
            // invoking bubbleUp to ensure that the node in correct position 
            currentSize++;
            int idx = currentSize;
            minHeap[idx] = x;
            indexes[x.vertex.getLabel()] = idx;
            bubbleUp(idx);
            
        }

        
        //---------------------------------------------------------------------------------------------
        // method to make sure every node in the minHeap is placed correctly
        public void bubbleUp(int pos) {
            
            int parentIdx = pos / 2;
            int currentIdx = pos;
            
            while (currentIdx > 0 && minHeap[parentIdx].key > minHeap[currentIdx].key) {
                HeapNode currentNode = minHeap[currentIdx];
                HeapNode parentNode = minHeap[parentIdx];

                //swap a heapNode with its parent ,
                // if the parent key is larger than the heapNode 
                indexes[currentNode.vertex.getLabel()] = parentIdx;
                indexes[parentNode.vertex.getLabel()] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
                
            }
        }

        
        //---------------------------------------------------------------------------------------------
        // method get the minimum heapNode in the minHeap
        public HeapNode extractMin() {
            
            HeapNode min = minHeap[1];
            HeapNode lastNode = minHeap[currentSize];
            indexes[lastNode.vertex.getLabel()] = 1;
            minHeap[1] = lastNode;
            minHeap[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return min;
            
        }

        
        //---------------------------------------------------------------------------------------------
        // method to make sure the minHeap remains a valid heap structure
        public void sinkDown(int k) {
            
            // determining the smallest child of the heapNode at the given index
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k + 1;
            
            if (leftChildIdx < heapSize() && minHeap[smallest].key > minHeap[leftChildIdx].key) {
                smallest = leftChildIdx;
            }
            
            if (rightChildIdx < heapSize() && minHeap[smallest].key > minHeap[rightChildIdx].key) {
                smallest = rightChildIdx;
            }
            
            // swapping the heapNode at the given index with its smallest children
            // if the smallest child key is smaller than the heapNode key 
            if (smallest != k) {
                
                Edge smallestEdge = minHeap[smallest].vertex.getMinEdge();
                Edge kEdge = minHeap[k].vertex.getMinEdge();

                Vertex smallestVertex = minHeap[smallest].vertex;
                Vertex kVertex = minHeap[k].vertex;

                indexes[smallestVertex.getLabel()] = k;
                indexes[kVertex.getLabel()] = smallest;
                
                swap(k, smallest);
                
                // recursive call to ensure the minHeap remines a valid heap structure
                sinkDown(smallest);
            
            }
        }

        
        //---------------------------------------------------------------------------------------------
        // method to swap two heapNodes in the minheap
        public void swap(int a, int b) {
            
            HeapNode temp = minHeap[a];
            minHeap[a] = minHeap[b];
            minHeap[b] = temp;
            
        }

        
        //---------------------------------------------------------------------------------------------
        // method to know if the heap is empty 
        public boolean isEmpty() {
            
            return currentSize == 0;
            
        }

        
        //---------------------------------------------------------------------------------------------
        // method to know the heap size
        public int heapSize() {
            
            return currentSize;
            
        }

    }
}
