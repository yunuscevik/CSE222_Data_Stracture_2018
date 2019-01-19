
package hw7.Q1;

import hw7.BookSourceCode.AbstractGraph;
import hw7.DesiredMethods.DesiredMethods;
import hw7.BookSourceCode.Edge;
import hw7.BookSourceCode.Graph;
import hw7.BookSourceCode.ListGraph;
import java.util.ListIterator;
import java.util.Vector;

/**
 *
 * @author Asus
 */
public class Q1_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DesiredMethods dMethod = new DesiredMethods();
        AbstractGraph graph = new ListGraph(11, true);
        graph.insert(new Edge(0,1,1));
        graph.insert(new Edge(1,2,10));
        graph.insert(new Edge(2,3,15));
        graph.insert(new Edge(2,10,20));
        graph.insert(new Edge(2,5,43));
        graph.insert(new Edge(6,8,71));
        graph.insert(new Edge(1,8,19));
        graph.insert(new Edge(3,4,5));
        graph.insert(new Edge(4,5,30));
        graph.insert(new Edge(5,10,45));
        graph.insert(new Edge(5,6,80));
        graph.insert(new Edge(5,9,56));
        graph.insert(new Edge(6,7,3));
        graph.insert(new Edge(8,9,55));
        graph.insert(new Edge(1,10,23));
        graph.insert(new Edge(3,10,21));
        graph.insert(new Edge(7,8,60));
        graph.insert(new Edge(4,10,1));
        graph.insert(new Edge(1,5,57));
        graph.insert(new Edge(9,10,100));

        
        dMethod.plot_graph(graph);
        if (dMethod.is_undirected(graph) == false)
            System.out.println("The graph is directed.");
        else
            System.out.println("The graph is undirected.");
        if (dMethod.is_acyclic_graph(graph))
            System.out.println("The graph is acyclic.");
        else
            System.out.println("The graph is cyclic.");
        printShortestPath(graph,dMethod,0,7);
        printShortestPath(graph,dMethod,2,10);
        printShortestPath(graph,dMethod,1,3);
        printShortestPath(graph,dMethod,1,15);
    }
    private static void printShortestPath(Graph graph, DesiredMethods dMethod, int v1, int v2){
        Vector<Integer> path ;
        if ((path = dMethod.shortest_path(graph,v1,v2)) != null) {
            int i = 0;
            ListIterator<Integer> iter = path.listIterator(path.size());
            System.out.print("The shortest path between " + v1 + " and " + v2 + ": ");
            while (iter.hasPrevious()) {
                System.out.print(iter.previous());
                if (i < path.size()-1)
                    System.out.print(" -> ");
                ++i;
            }
            System.out.println();
        }
        else
            System.out.println("There is no shortest path between " + v1 + " and " + v2);
    }
    
}
