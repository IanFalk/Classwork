/**
 * Ian Falk
 * https://afteracademy.com/problems/merge-two-binary-tree
 */
public class TwentyFour_MergeTwoTrees {
    public static void main(String args[]) {
        Node input1 = new Node(1);
        input1.right = new Node(2);
        input1.left = new Node(6);
        input1.left.left = new Node(5);

        Node input2 = new Node(2);
        input2.left = new Node(1);
        input2.left.right = new Node(4);
        input2.right = new Node(3);
        input2.right.right = new Node(7);

        System.out.print("Input tree 1: ");
        printTree(input1);
        System.out.print("\nInput tree 2: ");
        printTree(input2);
        System.out.print("\nOutput tree: ");
        printTree(merge(input1,input2));
    }

    //Merges tree 2 into tree 1.
    public static Node merge(Node root1, Node root2) {
        if(root1==null) {
            return root2;
        }
        if(root2==null) {
            return root1;
        }
        //Combines the values of two nodes
        root1.data = root1.data + root2.data;

        root1.left = merge(root1.left,root2.left);
        root1.right = merge(root1.right,root2.right);

        return root1;
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
