package arboleos;


public class BinaryNode<T> {
    /*
        public class BinaryTree {
            BinaryNode root;
        }
    */
    private Double value;
    private BinaryNode left;
    private BinaryNode right;
    private Integer level = 0;
    private BinaryNode parent;

    BinaryNode(Double value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }

    BinaryNode(Double value, BinaryNode parent) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.parent = parent;
    }

    BinaryNode() {
        this.value = null;
        this.right = null;
        this.left = null;
        this.level = -1;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public BinaryNode setLeft(BinaryNode left) {
        this.left = left;
        this.left.parent = this;
        this.left.level = this.level + 1;
        return this.left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public BinaryNode setRight(BinaryNode right) {
        this.right = right;
        this.right.parent = this;
        this.right.level = this.level + 1;
        return this.right;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BinaryNode getParent() {
        return parent;
    }

    public void setParent(BinaryNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        String p = parent == null ? "null" : String.valueOf(parent.getValue());
        return "{" +
                "value=" + value +
                ", parent=" + p +
                ", level=" + level +
                ", left=" + left +
                ", right=" + right +
                "}";
    }

    public String showTree() {
        return String.format("{%s, %s %s}", value.toString(), left == null ? "null" : left.showTree()
                , right == null ? "null" : right.showTree());
    }

    public String niceShowTree() {
        return String.format("%sNode{level=%s, value=%s%s%s}",
                Node.tabs(level),
                level.toString(), value.toString(),
                left == null ? "" : String.format(" , \n" + Node.tabs(level + 1) + "left=%s", left.niceShowTree()),
                right == null ? "" : String.format(", \n" + Node.tabs(level + 1) + "rigth=%s", right.niceShowTree()));

    }

    public String showOrdered() {
        return String.format("%s %s %s", left == null ? "" : left.showOrdered()
                , value.toString(), right == null ? "" : right.showOrdered());
    }

    public String showOrderedReverse() {
        return String.format("%s %s %s", right == null ? "" : right.showOrderedReverse()
                , value.toString(), left == null ? "" : left.showOrderedReverse());
    }

/*
    public ArrayList<T> route (BinaryNode node){
        List<BinaryNode> l = new ArrayList<>();
        if(node.getParent()!=null){
            l.add(route(node.getParent()));
        } else {
            return this;
        }
        return this;
    }
*/
}
