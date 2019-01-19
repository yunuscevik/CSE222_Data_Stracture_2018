
package hw7.Q3;

import hw7.BookSourceCode.AbstractGraph;
import hw7.BookSourceCode.BreadthFirstSearch;
import hw7.BookSourceCode.DepthFirstSearch;
import hw7.BookSourceCode.Edge;
import hw7.BookSourceCode.ListGraph;
import hw7.DesiredMethods.DesiredMethods;

/**
 *
 * @author Asus
 */
public class Q3_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DesiredMethods dMethod = new DesiredMethods();
        AbstractGraph graph = new ListGraph(10,false);
        graph.insert(new Edge(0,1));
        graph.insert(new Edge(1,2));
        graph.insert(new Edge(2,3));
        graph.insert(new Edge(3,4));
        graph.insert(new Edge(5,6));
        graph.insert(new Edge(6,8));
        graph.insert(new Edge(7,8));
        graph.insert(new Edge(5,9));
        graph.insert(new Edge(6,7));
        graph.insert(new Edge(3,5));


        dMethod.plot_graph(graph);
        if (dMethod.is_undirected(graph))
            System.out.println("The graph is undirected.");
        else
            System.out.println("The graph is directed.");
        if (dMethod.is_acyclic_graph(graph))
            System.out.println("The graph is acyclic.");
        else
            System.out.println("The graph is cyclic.");



        System.out.println("\n\nDepth First Search - Spanning Tree");
        DepthFirstSearch dfs = new DepthFirstSearch(graph);
        System.out.print("Parent: ");
        int dfsArr[] = dfs.getParent();
        for(int item : dfsArr)
            System.out.print(item + "\t");
        System.out.println("\n");

        System.out.print("Child: ");
        for(int i = 0; i < 10; i++)
            System.out.print(i + "\t");
        System.out.println("\n");



        System.out.println("Breadth First Search - Spanning Tree");
        System.out.print("Parent: ");
        int bfsArr[] = BreadthFirstSearch.breadthFirstSearch(graph, 3);
        for(int item : bfsArr)
            System.out.print(item + "\t");
        System.out.println("\n");

        System.out.print("Child: ");
        for(int i = 0; i < 10; i++)
            System.out.print(i + "\t");
        System.out.println("\n");
    }

}
