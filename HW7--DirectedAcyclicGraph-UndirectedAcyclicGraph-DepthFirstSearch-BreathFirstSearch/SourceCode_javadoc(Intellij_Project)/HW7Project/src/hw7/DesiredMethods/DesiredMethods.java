
package hw7.DesiredMethods;

import hw7.BookSourceCode.DijkstrasAlgorithm;
import hw7.BookSourceCode.Graph;
import hw7.BookSourceCode.Edge;
import hw7.BookSourceCode.ListGraph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

/**
 * Benim yazdigim metodlar
 * @author Yunus Cevik
 */
public class DesiredMethods {
    /**
     * Bir graph icinde vertexlerin undirected graph olup olmadigina bakar
     * @param graph parametre graph alir
     * @return True - False
     */
    public boolean is_undirected(Graph graph) {
        for (int i = 0; i < graph.getNumV(); i++) {
            Iterator<Edge> iter = graph.edgeIterator(i);
            while (iter.hasNext()) {
                Edge edge = iter.next();
                if (!graph.isEdge(edge.getDest(),edge.getSource()))
                    return false;
            }
        }

        return true;
    }
    /**
     * Bir graph icinde vertexlerin connected graph olup olmadigina bakar
     * @param graph parametre graph alir
     * @param v1 Source
     * @param v2 Destination
     * @return True - False
     */
    public boolean is_connected(Graph graph,int v1,int v2) {
        if (graph instanceof ListGraph) {
            if (v1 >= graph.getNumV() || v2 >= graph.getNumV())
                return false;
            Iterator<Edge> iter1 = graph.edgeIterator(v1);
            Iterator<Edge> iter2 = graph.edgeIterator(v2);
            if (!iter1.hasNext() || !iter2.hasNext())
                return false;
            LinkedList<Integer> visited = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            Edge start = iter1.next();
            stack.push(start.getSource());
            do
            {
                int source = stack.pop();
                visited.add(source);
                Iterator<Edge> iter = graph.edgeIterator(source);
                while (iter.hasNext()) {
                    Edge edge = iter.next();
                    if (edge.getSource() == v2)
                        return true;
                    if (!visited.contains(edge.getDest())) {
                        stack.push(edge.getDest());
                    }
                }

            }while (!stack.empty());

            return false;
        }
        return false;
    }
    /**
     * Bir graph icinde vertexlerin acyclic graph olup olmadigina bakar
     * @param graph parametre graph alir
     * @return True - False
     */
    public boolean is_acyclic_graph(Graph graph) {
        boolean[] visited = new boolean[graph.getNumV()];
        boolean[] recurStack = new boolean[graph.getNumV()];
        for (int i = 0; i < graph.getNumV(); i++)
            if (isCyclicHelper(graph ,i, visited, recurStack))
                return false;
        return true;
    }

    private boolean isCyclicHelper(Graph graph,int index, boolean[] visited, boolean[] recurStack){
        if (recurStack[index])
            return true;
        if (visited[index])
            return false;
        visited[index] = true;
        recurStack[index] = true;
        Iterator<Edge> it = graph.edgeIterator(index);
        while (it.hasNext()) {
            Edge node =it.next();
            if(node.getDest() < graph.getNumV() )
                if (isCyclicHelper(graph, node.getDest(), visited, recurStack))
                    return true;
        }
        recurStack[index] = false;
        return false;
    }
    /**
     * Gelen graphin list olarak ciktisini verir.
     * @param graph parametre graph alir
     */
    public void plot_graph(Graph graph) {
        System.out.println("ListGraph :");
        for (int i = 0; i < graph.getNumV(); i++) {
            Iterator<Edge> iter = graph.edgeIterator(i);
            if (iter.hasNext()) {
                Edge edge = iter.next();
                System.out.print(edge.getSource() + " -> " + edge.getDest());
            }
            while (iter.hasNext()) {
                Edge edge = iter.next();
                System.out.print(" -> " + edge.getDest());
            }
            System.out.println();
        }
    }
    /**
     * Bir graph uzerinde agirliklara gore en kisa yolu bulan metottur.
     * @param graph parametre graph alir
     * @param v1 Source
     * @param v2 Destination
     * @return True - False
     */
    public Vector<Integer> shortest_path(Graph graph,int v1,int v2) {
        boolean flag = true;
        if (v1 < 0 || v2 < 0 || v1 >= graph.getNumV() || v2 >= graph.getNumV())
            return null;
        int[] parent = new int[graph.getNumV()];
        double[] distance = new double[graph.getNumV()];
        DijkstrasAlgorithm.dijkstrasAlgorithm(graph, v1, parent, distance);
        if (distance[v2] == Double.POSITIVE_INFINITY)
            return null;
        int i = parent[v2];
        Vector<Integer> path = new Vector<>();
        path.add(v2);
        path.add(i);
        while (flag) {
            if (i == v1)
                flag=false;
            else {
                i = parent[i];
                path.add(i);
            }
        }
        return path;
    }

}
