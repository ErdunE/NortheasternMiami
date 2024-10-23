import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node/vertex in the graph.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-23
 */
public class Node {
    String value;
    // List of neighboring nodes (edges)
    List<Node> neighbors;

    /**
     * Constructs a Node with a given value.
     *
     * @param value The value of the node.
     */
    public Node(String value) {
        this.value = value;
        this.neighbors = new ArrayList<Node>();
    }

    /**
     * Returns the string representation of the node's value.
     *
     * @return The value of the node as a string.
     */
    @Override
    public String toString(){
        return value;
    }

}
