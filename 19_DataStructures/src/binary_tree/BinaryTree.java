package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree
{
    private Node root;

    public void addNode(String data) {

        Node root = this.root;

        if (root != null) {
            while (root != null) {
                if (root.getData().compareTo(data) < 0) {
                    this.root = root;
                    root = root.getLeft();
                } else {
                    this.root = root;
                    root = root.getRight();
                }
            }

//            if (this.root.getData().compareTo(data) < 0) {
//
//                this.root.setLeft();
//            } else {
//            }
//        } else {


        }
        //TODO
    }

    public List<Node> searchNodes(String data) {

        List<Node> nodesList = new ArrayList<>();
        int i;

        while (root != null) {

            i = root.getData().compareTo(data);
            if (i == 0) {
                nodesList.add(root);
                root = root.getLeft();
            } else  if (i > 0) {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }
        //TODO
        return nodesList;
    }
}