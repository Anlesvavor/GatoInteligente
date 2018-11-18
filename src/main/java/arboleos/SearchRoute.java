package arboleos;

import java.util.ArrayList;

public class SearchRoute<T> {
    private Node<T> node;
    private ArrayList<Node<T>> route;

    public SearchRoute(Node<T> node, ArrayList<Node<T>> route) {
        this.node = node;
        this.route = route;
    }

    public SearchRoute() {
    }

    public Node<T> getNode() {
        return node;
    }

    public void setNode(Node<T> node) {
        this.node = node;
    }

    public ArrayList<Node<T>> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Node<T>> route) {
        this.route = route;
    }
}