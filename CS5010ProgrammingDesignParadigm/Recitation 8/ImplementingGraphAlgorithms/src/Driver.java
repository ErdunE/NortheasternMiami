
/**
 * Driver class to run the implementing graph algorithms program.
 * This class is for create a graph, load it from graph.txt,
 * display its adjacency list, and find SCCs.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-23
 */
public class Driver {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Load graph from graph.txt
        graph.loadGraphFromFile("graph.txt");

        // Display the graph
        System.out.println("Graph adjacency list:");
        System.out.println(graph);

        // Finding and display SCCs
        System.out.println("Strongly Connected Components:");
        graph.findStronglyConnectedComponents();

    }
}