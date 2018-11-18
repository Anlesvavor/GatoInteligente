package arboleos;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private BinaryNode root;
    private Integer maxLevel = 0;
    private List<BinaryNode> nodeList = new ArrayList<>();

    private BinaryNode addRecursive(BinaryNode current, Double value) {
        if (current == null) {
            return new BinaryNode(value);
        }

        if (value <= current.getValue()) {
            if (current.setLeft(addRecursive(current.getLeft(), value)).getLevel() > maxLevel) maxLevel++;

        } else if (value > current.getValue()) {
            if (current.setRight(addRecursive(current.getRight(), value)).getLevel() > maxLevel) maxLevel++;
        } else {
            // value already exists
            return current;
        }
        return current;
    }

    public void add(Double value) {
        root = addRecursive(root, value);
    }

    private BinaryNode containsNodeRecursive(BinaryNode actual, Double value) {
        nodeList.add(actual);
        if (actual == null) {
            return new BinaryNode();
        }
        if (actual.getValue().equals(value)) {
            return actual;
        }
        return value < actual.getValue() ? containsNodeRecursive(actual.getLeft(), value) :
                containsNodeRecursive(actual.getRight(), value);
    }

    public BinaryNode containsNode(Double value) {
        return containsNodeRecursive(root, value);
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }

    public List<BinaryNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<BinaryNode> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }
}
