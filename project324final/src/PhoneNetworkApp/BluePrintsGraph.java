/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhoneNetworkApp;

import GraphFramework.Graph;
import GraphFramework.Vertex;

/**
 *
 * @author cvs
 */
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
