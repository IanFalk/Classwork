/**
 * Ian Falk
 * https://afteracademy.com/problems/path-sum-in-binary-tree
 */
public class TwentyTwo_BinaryTreeSum {
    public static void main(String args[]) {
        Node root = new Node(6);
        root.left = new Node(4);
        root.right = new Node(8);
        root.left.left = new Node(10);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(2);
        root.right.left =  new Node(14);
        root.right.right = new Node(3);
        root.right.right.right = new Node(1);

        int sum=22;
        System.out.print("Input: Sum=22, ");
        printTree(root);
        System.out.println("\nOutput: "+sum(root,sum));
    }

    //Returns true when the sum=0.
    public static boolean sum(Node root,int sum) {
        if(root == null) {
            return sum==0;
        }
        //Recursively calls down each subtree subtracting the value of the root, until the sum is 0.
        return sum(root.left, sum-root.data) || sum(root.right, sum-root.data);
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
