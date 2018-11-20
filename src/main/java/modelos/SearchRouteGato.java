package modelos;

import arboleos.Node;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchRouteGato implements Serializable {
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
    
    public SearchRouteGato rutaARaiz() {
        ArrayList<NodeGato> ruta = new ArrayList<>();
        NodeGato tmp = this.getNode();
        while(true){
            if (tmp.getParent() != null){
                ruta.add(0, (NodeGato) DeepCopy.copy(tmp));
                tmp = tmp.getParent();
            } else {
                break;
            }
        }
        return new SearchRouteGato(this.node, ruta);
    }

}