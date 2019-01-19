
package hw7.Q2;

import hw7.BookSourceCode.AbstractGraph;
import hw7.BookSourceCode.Edge;
import hw7.BookSourceCode.Graph;
import hw7.BookSourceCode.ListGraph;
import hw7.DesiredMethods.DesiredMethods;

/**
 *
 * @author Asus
 */
public class Q2_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DesiredMethods dMethod = new DesiredMethods();
        AbstractGraph graph = new ListGraph(16, false);
        graph.insert(new Edge(0,1));
        graph.insert(new Edge(1,2));
        graph.insert(new Edge(2,3));
        graph.insert(new Edge(3,4));
        graph.insert(new Edge(5,6));
        graph.insert(new Edge(6,8));
        graph.insert(new Edge(7,8));
        graph.insert(new Edge(5,9));
        graph.insert(new Edge(15,9));
        graph.insert(new Edge(6,7));
        graph.insert(new Edge(13,12));
        graph.insert(new Edge(12,11));
        graph.insert(new Edge(11,10));
        graph.insert(new Edge(14,13));
        graph.insert(new Edge(15,13));


        dMethod.plot_graph(graph);
        if (dMethod.is_undirected(graph))
            System.out.println("The graph is undirected.");
        else
            System.out.println("The graph is directed.");
        if (dMethod.is_acyclic_graph(graph))
            System.out.println("The graph is cyclic.");
        else
            System.out.println("The graph is acyclic.");

        printIsConnected(graph, dMethod, 1, 8);
        printIsConnected(graph, dMethod, 5, 14);
        printIsConnected(graph, dMethod, 1, 18);
    }
    private static void printIsConnected(Graph graph,DesiredMethods dMethod, int v1, int v2){
        if (dMethod.is_connected(graph,v1,v2))
            System.out.println(""+ v1 + " and " + v2 + " is  connected.");
        else
            System.out.println(""+ v1 + " and " + v2 + " is not connected.");

    }
}
