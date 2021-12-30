/**
 * Ian Falk
 * https://afteracademy.com/problems/merge-two-sorted-lists
 */
public class Nineteen_MergeTwoLists {
    public static void main(String args[]) {
        //Example 1
        Node input1 = new Node(10);
        input1.right = new Node(12);
        input1.right.right = new Node(13);
        input1.right.right.right = new Node(42);

        Node input2 = new Node(4);
        input2.right = new Node(11);
        input2.right.right = new Node(15);

        System.out.print("Input: ");
        printLinkedList(input1);
        System.out.print(", ");
        printLinkedList(input2);

        System.out.print("\nOutput: ");
        printLinkedList(merge(input1,input2));

        //Example 2
        input1 = new Node(2);
        input1.right = new Node(5);
        input1.right.right = new Node(9);

        input2 = new Node(12);
        input2.right = new Node(14);
        input2.right.right = new Node(17);

        System.out.print("\nInput: ");
        printLinkedList(input1);
        System.out.print(", ");
        printLinkedList(input2);

        System.out.print("\nOutput: ");
        printLinkedList(merge(input1,input2));

        //Example 3
        input1 = new Node(8);

        input2 = new Node(4);
        input2.right = new Node(12);

        System.out.print("\nInput: ");
        printLinkedList(input1);
        System.out.print(", ");
        printLinkedList(input2);

        System.out.print("\nOutput: ");
        printLinkedList(merge(input1,input2));
    }

    public static void printLinkedList(Node head) {
        while(head != null) {
            if(head.right==null) {
                System.out.print(head.data);
            } else {
                System.out.print(head.data + "->");
            }
            head = head.right;
        }
    }

    public static Node merge(Node head1, Node head2) {
        if(head1==null) {
            return head2;
        }
        if(head2==null) {
            return head1;
        }

        if(head1.data > head2.data) {
            head2.right = merge(head1, head2.right);
            return head2;
        } else {
            head1.right = merge(head1.right, head2);
            return head1;
        }


    }
}
