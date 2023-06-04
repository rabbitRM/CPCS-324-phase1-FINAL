/* Title : Implementing the Min-heap based Prim and kruskal algorithms to get the minimum spanning tree 
  
   Name                     ID
   Nujud Abdullah Almaleki  2105148 
   Rama Ahmad Alsefri  	    2105895
   Areej Omar Baeshen  	    2105759
   Furat Jamel Alfarsi 	    2009624

  Control structure : the program will depend on the use of array, arraylist, linked List, map and min heap
  Input requirements : The user will decide to get MST from a graph its information is written in a file 
                       OR enter the number of locations , routes , if the graph is directed or not 
                       and let the program randomly generate a graph 
  Output results : printing all the paths with their cost, additionally showing the optimal cost 

  Cites : prim --> https://gist.github.com/thmain/a3fcfe0933c55e3402cb7709f2d043ed#file-primusingminheap-java
          kruskal --> https://www.youtube.com/watch?v=ID00PMy0-vE , https://www.algotree.org/algorithms/disjoint_set/ ,
                      https://www.youtube.com/watch?v=KxLtIrCyXwE 

 */


package PhoneNetworkApp;

import GraphFramework.KruskalAlg;
import GraphFramework.MHprimAlg;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhoneNWDesignApp {

    // Defining the data feilds
    static BluePrintsGraph PhLNetwork;

    public static void main(String[] args) throws FileNotFoundException {

        // scanner opject to read from the user
        Scanner input = new Scanner(System.in);

        // creating the object that will contain the graph
        PhLNetwork = new BluePrintsGraph();
        
        // creating boolean variables to decides the format of the printing
        // printTime variable to know if we need to print the time required 
        // isReq1 variable to know if we need to print the labels as letter or as numbers
        boolean printTime = true , isReq1 = false  ;
        
        // long variables to store the strating time and ending time 
        //of processing Kruskal and Prim algorithms
        long startTime, endTime;

        // double variable to store differenece of time 
        //in milliseconed between startTime and endTime variables
        double result;
        
        System.out.println("\t -------Comparing Different Algorithms That produce MST-------");
        System.out.println("\t1-Min Heap based Prim Algorithm and 2- Kruskal's Algorithm ");
       
        System.out.println("");
        
        // letting the user pick a choice
        System.out.print(">> Enter your choice (1 or 2): ");
        
        int choice = input.nextInt();
        
        System.out.println("");
        
         // if he pick 1 --> the graph will be read from a file 
        if (choice == 1) {
            
            // invoking the method that will read a graph from a specified file 
            PhLNetwork.readGraphFromFile("input.txt");
            
            // no time required
            printTime = false ;
            
            // labels will be letters
            isReq1 = true ;
            
        // if he pick 2 --> the graph will be random    
        } else {
            
            // getting the number of Offices
            System.out.print("Enter the number of Offices : ");
            int officeNO = input.nextInt();
            
            // getting the number of Lines 
            System.out.print("Enter the number of Lines : ");
            int lineNO = input.nextInt();
            
            // getting to know if if directed or not 
            System.out.print("Enter 0 if the graph is undirected or 1 if directed : ");
            int isdiagraph = input.nextInt();
            
            // invoking the method that will create a random graph 
            PhLNetwork.makeGraph(officeNO, lineNO, isdiagraph);
            
        }

        // creating a MHprimAlg object , and send the graph to it 
        MHprimAlg prim = new MHprimAlg(PhLNetwork);
        
        // creating a KruskalAlg object , and send the graph to it 
        KruskalAlg kruskal = new KruskalAlg(PhLNetwork);
        
        // computing the starting time for prim
        startTime = System.currentTimeMillis();
        
        System.out.println("The phone network (minimum spanning tree) generated by min-heap based Prim algorithm"
                + "is as follows:\n");
        
        // getting the optimal cost after invoking the prim algorithm
        int cost = prim.prim(isReq1);
        
        // printing the message
        System.out.println("Total cost: " + cost + "\n");
        
        // computing the ending time for prim  
        endTime = System.currentTimeMillis();
       
        // computing the time it took the prim to process
        result = (double) (endTime - startTime)/1000;
        
        // checking if it is required to print the time or not
        if(printTime)
        System.out.println("It took me " + result + " ms\n");

        // computing the starting time for kruskal
        startTime = System.currentTimeMillis();
        
        System.out.println("The phone network (minimum spanning tree) generated by Kruskal algorithm is as follows:\n");
        
        // getting the optimal cost after invoking the kruskal algorithm
        cost = kruskal.kruskal(isReq1);
        
        // printing the message
        System.out.println("Total cost: " + cost + "\n");
        
        // computing the ending time for kruskal  
        endTime = System.currentTimeMillis();
        
        // computing the time it took kruskal to process
        result = (double) (endTime - startTime) / 1000 ;
        
        // checking if it is required to print the time or not
        if(printTime)
        System.out.println("It took me " + result + " ms\n");

    }

}
