package modelos;

import arboleos.Node;

import java.util.ArrayList;

public class SearchRouteGato {
    private NodeGato node;
    private ArrayList<NodeGato> route;

    public SearchRouteGato(NodeGato node, ArrayList<NodeGato> route) {
        this.node = node;
        this.route = route;
    }

    public SearchRouteGato() {
    }

    public NodeGato getNode() {
        return node;
    }

    public void setNode(NodeGato node) {
        this.node = node;
    }

    public ArrayList<NodeGato> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<NodeGato> route) {
        this.route = route;
    }

}