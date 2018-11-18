package arboleos;

import java.util.ArrayList;

public class BinaryRoute {
    private BinaryNode binaryNode;
    private ArrayList<BinaryRoute> route;

    public BinaryRoute(BinaryNode binaryNode, ArrayList<BinaryRoute> route) {
        this.binaryNode = binaryNode;
        this.route = route;
    }

    public BinaryNode getBinaryNode() {
        return binaryNode;
    }

    public void setBinaryNode(BinaryNode binaryNode) {
        this.binaryNode = binaryNode;
    }

    public ArrayList<BinaryRoute> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<BinaryRoute> route) {
        this.route = route;
    }
}
