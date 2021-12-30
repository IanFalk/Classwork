/**
 * Ian Falk
 * https://afteracademy.com/problems/min-depth-of-binary-tree
 */
public class Sixteen_ArrayToBST {
    static Node root;
    public static void main(String args[]) {
        Sixteen_ArrayToBST tree = new Sixteen_ArrayToBST();
        int[] arr = new int[]{1,2,3};
        root = tree.arrayToBST(arr,0,arr.length-1);
        System.out.print("Output: ");
        printPreorder(root);

        arr = new int[]{1,2,3,4};
        root = tree.arrayToBST(arr,0,arr.length-1);
        System.out.print("\nOutput: ");
        printPreorder(root);

        arr = new int[]{1,2,3,4,5,6};
        root = tree.arrayToBST(arr,0,arr.length-1);
        System.out.print("\nOutput: ");
        printPreorder(root);
    }

    public static Node arrayToBST(int[] arr, int start, int end) {
        if(start > end) {
            return null;
        }

        int mid= (start+end)/2;
        Node node = new Node(arr[mid]);
        node.left = arrayToBST(arr, start, mid-1);
        node.right=arrayToBST(arr, mid+1,end);

        return node;
    }

    //Generic printPreorder method for BST
    public static void printPreorder(Node node)
    {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
}
