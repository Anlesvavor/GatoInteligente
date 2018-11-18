package arboleos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Node<Double> tree = generatePseudoRandomTree();
        BinaryTree binaryTree = generateRandomBinaryTree();

        System.out.println("Generando arbol pseudo-aleatorio . . .\n");
        System.out.println(tree.toString());
        System.out.println("Ingrese el dato a buscar: ");
        Double d = new Double(reader.readLine());
        System.out.println(tree.breadthSearch(d).getNode().toString());
        System.out.println("Ruta seguida con busqueda en profundidad (únicamente datos):\n");
        tree.depthSearch(d).getRoute().forEach(
                doubleNode -> System.out.println(String.format("%s%s", Node.tabs(doubleNode.getLevel()), doubleNode.getData().toString())));
        System.out.println("Ruta seguida con busqueda en anchura (únicamente datos):\n");
        tree.breadthSearch(d).getRoute().forEach(
                doubleNode -> System.out.println(String.format("%s%s", Node.tabs(doubleNode.getLevel()), doubleNode.getData().toString())));
        System.out.println("Generando arbol binario aleatorio . . .\n");
        System.out.println(binaryTree.containsNode(binaryTree.getRoot().getValue()).niceShowTree());
        System.out.println("Ingrese el dato a buscar: ");
        d = new Double(reader.readLine());
        System.out.println(binaryTree.containsNode(d).niceShowTree());
        System.out.println("Ruta seguida");
        /*binaryTree.getNodeList().forEach(
                binaryNode -> System.out.println(String.format("%s%s", Node.tabs(binaryNode.getLevel()))));
        */
        binaryTree.getNodeList().forEach(
                n -> System.out.println(String.format(
                        "%s%s\n%s", Node.tabs(n.getLevel()), n.getValue(),
                        ((n.getParent() != null) && (n.getParent().getLeft() != null) && n.equals(n.getParent().getLeft())) ? "" + Node.tabs(n.getLevel() + 1) + "<-"
                                : "" + Node.tabs(n.getLevel() + 1) + "->")));
        System.out.println("Contenidos del arbol de forma ascendiente:\n");
        System.out.println(binaryTree.containsNode(binaryTree.getRoot().getValue()).showOrdered());
        System.out.println("Contenidos del arbol de forma descendiente:\n");
        System.out.println(binaryTree.containsNode(binaryTree.getRoot().getValue()).showOrderedReverse());
    }

    public static BinaryTree generateRandomBinaryTree() {
        BinaryTree tree = new BinaryTree();
        for (Integer i = 0; i < Math.ceil(Math.random() * 20) + 8; i++)
            tree.add(Math.ceil(Math.random() * 50));
        return tree;
    }

    public static Node generatePseudoRandomTree() {
        Node<Double> tree = new Node<>(Math.ceil(Math.random() * 50));
        for (Integer i = 0; i < Math.ceil(Math.random() * 5); i++)
            tree.addChild(new Node<>(Math.ceil(Math.random() * 50)));

        for (Node<Double> n : tree.getChildren()) {
            for (Integer i = 0; i < Math.ceil(Math.random() * 5); i++)
                n.addChild(new Node<>(Math.ceil(Math.random() * 50)));
        }

        for (Node<Double> n : tree.getChildren()) {
            for (Node<Double> m : n.getChildren())
                for (Integer i = 0; i < Math.ceil(Math.random() * 5); i++)
                    m.addChild(new Node<>(Math.ceil(Math.random() * 50)));
        }

        return tree;
    }

}
