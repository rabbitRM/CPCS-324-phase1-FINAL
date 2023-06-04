package PhoneNetworkApp;

import GraphFramework.Graph;
import GraphFramework.Vertex;

public class BluePrintsGraph extends Graph {
     
     // Defining the overriden method to create Line in the application framework instead of creating an edge
     @Override
     public Line createEdge(Vertex source, Vertex target , int weight){
       
         return new Line (source , target , weight);
        
    }
     
     //-----------------------------------------------------------------------------
     // Defining the overriden method to create Office in the application framework instead of creating a vertex 
     @Override
      public Office createVertex(int lable ){
      
          return new Office(lable);
   
      }
     
}
