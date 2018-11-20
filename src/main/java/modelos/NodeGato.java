package modelos;

import arboleos.SearchMethods;
import arboleos.SearchRoute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NodeGato implements SearchMethodsGato, Serializable {

    private Jugada data;
    private List<NodeGato> children = new ArrayList<>();
    private NodeGato parent = null;
    private Integer level = 0;

    public NodeGato(Jugada data) {
        this.data = data;
    }

    public NodeGato(NodeGato another) {
        this.data = another.data;
        this.children = another.children;
        this.parent = another.parent;
        this.level = another.level;
    }

    public NodeGato() {
        this.level = -1;
        this.data = null;
        this.children = null;
        this.parent = null;
    }

    public NodeGato addChild(NodeGato child) {
        child.setParent(this);
        this.children.add(child);
        child.setLevel(child.parent.level + 1);
        return child;
    }

    public void addChildren(List<NodeGato> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public NodeGato getNChild(Integer n) {
        return this.getChildren().get(n);
    }

    public SearchRouteGato depthSearch(Jugada data) {
        NodeGato copy = new NodeGato(this);
        //ArrayList<Node> l = new ArrayList<>(copy.getChildren());
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (l.equals(data)) return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (n.getData().equals(data)) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(0, n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(new NodeGato(), new ArrayList<>());
    }


    public SearchRouteGato breadthSearch(Jugada data) {
        NodeGato copy = new NodeGato(this);
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (l.equals(data)) return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (n.getData().equals(data)) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(l.size(), n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(new NodeGato(), new ArrayList<>());
    }

    @Override
    public SearchRouteGato depthGanadorSearch() {
        return null;
    }

    @Override
    public SearchRouteGato breadthGanadorSearch() {
        return null;
    }


    public SearchRouteGato depthGanadorSearch(EnumEstado estado) {
        NodeGato copy = new NodeGato(this);
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (!l.get(0).getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (l.get(0).getData().getSimbolo().equals(estado)))
                    return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (!n.getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (n.getData().getSimbolo().equals(estado))) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(0, n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(this, new ArrayList<>());
    }

    public SearchRouteGato depthCompetitivoSearch(EnumEstado estado) {
        NodeGato copy = new NodeGato(this);
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (!l.get(0).getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (l.get(0).getData().getSimbolo().equals(estado) || l.get(0).getData().getGanador().equals(EnumGanador.EMPATE)))
                    return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (!n.getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (n.getData().getSimbolo().equals(estado) || n.getData().getGanador().equals(EnumGanador.EMPATE))) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(0, n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(this, new ArrayList<>());
    }

    public SearchRouteGato breadthGanadorSearch(EnumEstado estado) {
        NodeGato copy = new NodeGato(this);
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (l.get(0).getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (l.get(0).getData().getSimbolo().equals(estado)))
                    return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (!n.getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (n.getData().getSimbolo().equals(estado))) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(l.size(), n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(new NodeGato(), new ArrayList<>());
    }

    public SearchRouteGato breadthCompetitivoSearch(EnumEstado estado) {
        NodeGato copy = new NodeGato(this);
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (l.get(0).getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (l.get(0).getData().getSimbolo().equals(estado) || l.get(0).getData().getGanador().equals(EnumGanador.EMPATE)))
                    return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (!n.getData().getGanador().equals(EnumGanador.POR_DEFINIR)
                        && (n.getData().getSimbolo().equals(estado) || n.getData().getGanador().equals(EnumGanador.EMPATE))) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(l.size(), n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(new NodeGato(), new ArrayList<>());
    }

    public SearchRouteGato breadthRejaSearch(Reja reja) {
        NodeGato copy = new NodeGato(this);
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (l.get(0).getData().getReja().equals(reja))
                    return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (reja.equals(n.getData().getReja())) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(l.size(), n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(new NodeGato(), new ArrayList<>());
    }

    public SearchRouteGato depthRejaSearch(Reja reja) {
        NodeGato copy = new NodeGato(this);
        ArrayList<NodeGato> l = new ArrayList<>();
        ArrayList<NodeGato> v = new ArrayList<>();
        l.add(0, copy);
        NodeGato n;
        do {
            if (l.isEmpty()) {
                v.add(l.get(0));
                if (!l.get(0).getData().getReja().equals(reja))
                    return new SearchRouteGato(l.get(0), v);
            } else {
                n = l.remove(0);
                v.add(n);
                if (!n.getData().getReja().equals(reja)) {
                    return new SearchRouteGato(n, v);
                } else {
                    if (!n.getChildren().isEmpty()) l.addAll(0, n.getChildren());
                }
            }
        } while (!l.isEmpty());
        return new SearchRouteGato(new NodeGato(), new ArrayList<>());
    }

    public SearchRouteGato mejorMovimientos(){
        return null;
    }

    public List<NodeGato> getChildren() {
        return children;
    }

    public Jugada getData() {
        return data;
    }

    public void setData(Jugada data) {
        this.data = data;
    }

    public NodeGato getParent() {
        return parent;
    }

    private void setParent(NodeGato parent) {
        this.parent = parent;
    }

    public Integer getLevel() {
        return level;
    }

    private void setLevel(Integer level) {
        this.level = level;
    }

    public static String tabs(Integer n) {
        String tabs = "";
        for (Integer i = 0; i < n; i++)
            tabs = String.format("%s\t", tabs);
        return tabs;
    }

    //@Override
    public String toStringNice() {
        return "NodeGato{" +
                " level=" + level +
                " data=" + data +
                " children=\n" + children +
                '}';

    }

    /*
    tan bonita y no pude usarte </3
        ()->{
                    String tabs = "";
                    for (Integer i = 0; i < level; i++)
                        tabs = String.format("%s\t", tabs)
                    return tabs;



                }
     */
    //My proudest toString()'s Override
    @Override
    public String toString() {
        return String.format("%sNode{level=%s, data=%s%s}\n",
                tabs(level),
                level.toString(),
                data.toString(),
                children.isEmpty() ? "" : String.format(", children=\n%s", children.toString()));
    }
}
