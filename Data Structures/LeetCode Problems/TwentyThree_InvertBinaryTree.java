/**
 * Ian Falk
 * https://afteracademy.com/problems/invert-binary-tree
 */
public class TwentyThree_InvertBinaryTree {
    public static void main(String args[]) {
        Node root = new Node(5);
        root.left = new Node(2);
        root.right = new Node(8);
        root.left.left = new Node(1);
        root.left.right = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        System.out.print("Input: ");
        printTree(root);
        root=invert(root);
        System.out.print("\nOutput: ");
        printTree(root);
    }

    public static Node invert(Node root) {
        if(root == null) {
            return root;
        }
        swap(root);

        //Recursively go down each subtree
        invert(root.left);
        invert(root.right);

        return root;
    }

    //Swaps the left and right childs of a given parent
    public static void swap(Node root) {
        if(root == null) {
            return;
        }
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    //Prints the tree with preorder traversal
    public static void printTree(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data + " ");
        printTree(root.left);
        printTree(root.right);
    }
}
