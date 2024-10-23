import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Represents a given directed graph, include methods to add/store node and edges
 * to string method, dfs, reverse graph method.
 * convert to a string, perform DFS, reverse the graph, and find SCCs.
 * This class also provides functionality to load a graph from graph.txt
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-23
 */
public class Graph {
    private List<Node> nodes;

    /**
     * Constructs an empty graph.
     */
    public Graph() {
        this.nodes = new ArrayList<Node>();
    }

    /**
     * Adds a node to the graph.
     *
     * @param value The value of the node to add.
     */
    public void addNode(String value) {
        // Add a new node with the given value
        nodes.add(new Node(value));
    }

    /**
     * Adds a directed edge between two nodes.
     *
     * @param fromValue The value of the source node.
     * @param toValue   The value of the destination node.
     */
    public void addEdge(String fromValue, String toValue) {
        // Get the source node
        Node fromNode = getNode(fromValue);
        // Get the destination node
        Node toNode =  getNode(toValue);
        // If both nodes exist
        if (fromNode != null && toNode != null) {
            // Add the destination node to the source node's neighbors list
            fromNode.neighbors.add(toNode);
        }
    }

    /**
     * Helper function to get a node by its value.
     *
     * @param value The value of the node to retrieve.
     * @return The node with the specified value, or null if not found.
     */
    public Node getNode(String value){
        // Iterate through the list of nodes
        for(Node node : nodes){
            // If find the value matches
            if(node.value.equals(value)){
                // Return the node
                return node;
            }
        }
        // Return null if the node is not found
        return null;
    }

    /**
     * Returns a string representation of the graph's adjacency list.
     *
     * @return The adjacency list as a string.
     */
    @Override
    public String toString() {
        // Create a StringBuilder to construct the adjacency list
        StringBuilder sb = new StringBuilder();
        // Iterate through each node
        for (Node node : nodes) {
            // Append the node value and the arrow
            sb.append(node.value).append(" -> ");
            // Iterate through each neighbor of the node
            for (Node neighbor : node.neighbors) {
                // Append the neighbor value
                sb.append(neighbor.value).append(" ");
            }
            sb.append("\n");
        }
        // Return the constructed string
        return sb.toString();
    }

    /**
     * Runs DFS from the given node.
     *
     * @param node    The start node for DFS.
     * @param visited A set to keep track of visited nodes.
     * @param stack   A stack to store nodes in order of completion time.
     */
    private void dfs(Node node, Set<Node> visited, Stack<Node> stack) {
        // Mark the current node as visited
        visited.add(node);
        // Iterate through each neighbor of the node
        for (Node neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                // if the neighbor has not been visited, recursively visit the neighbor
                dfs(neighbor, visited, stack);
            }
        }
        // After visiting all its neighbors push the node into the stack
        stack.push(node);
    }

    /**
     * Reverses the graph.
     *
     * @return A new graph that is the reverse of the current graph.
     */
    public Graph graphReverse() {
        Graph reversedGraph = new Graph();
        for (Node node : nodes) {
            // Add the node to the reversed graph
            reversedGraph.addNode(node.value);
        }
        for (Node node : nodes) {
            for (Node neighbor : node.neighbors) {
                // Add the reversed edge to the new graph
                reversedGraph.addEdge(neighbor.value, node.value);
            }
        }
        // Return the reversed graph
        return reversedGraph;
    }

    /**
     * Finds and prints all SCCs of the graph.
     */
    public void findStronglyConnectedComponents() {
        // Store node order stacks based on completed time
        Stack<Node> stack = new Stack<>();
        // Set to keep track of visited nodes
        Set<Node> visited = new HashSet<>();

        // Step 1: Fill the stack based on finish times of DFS
        for (Node node : nodes) {
            if (!visited.contains(node)) {
                // If the node has not been visited perform DFS on the node
                dfs(node, visited, stack);
            }
        }

        // Step 2: Reverse the graph
        // Get the reversed graph
        Graph reversedGraph = graphReverse();

        // Step 3: Process all nodes in order defined by the stack
        // Clear the visited set
        visited.clear();
        while (!stack.isEmpty()) {
            // Get the next node from the stack
            Node node = reversedGraph.getNode(stack.pop().value);
            if (!visited.contains(node)) {
                // Stack to store the current SCC
                Stack<Node> componentStack = new Stack<>();
                // Perform DFS on the node to find all nodes in the SCC
                reversedGraph.dfs(node, visited, componentStack);
                System.out.print("SCC: ");
                // While there are nodes in the component stack
                while (!componentStack.isEmpty()) {
                    System.out.print(componentStack.pop().value + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Loads the graph from graph.txt.
     *
     * @param filename The name of the file containing the graph data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void loadGraphFromFile(String filename) {
        // Open the graph.txt for reading
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Store each line
            String line;
            // Determine if in the node section
            boolean isNodeSection = true;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("# ")) continue;
                // If the line idicates the edges switch to the edges section
                if (line.equals("#EDGES")) {
                    isNodeSection = false;
                    continue;
                }
                // If in the node section, add the node to the graph
                if (isNodeSection) {
                    addNode(line);
                } else { // Or in the edges section, split the line into parts
                    String[] parts = line.split(" ");
                    // If only have two parts, add an edge between the two nodes
                    if (parts.length == 2) {
                        addEdge(parts[0], parts[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
