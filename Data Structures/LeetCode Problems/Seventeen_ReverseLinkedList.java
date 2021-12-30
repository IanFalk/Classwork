/**
 * Ian Falk
 * https://afteracademy.com/problems/reverse-linked-list-from-m-to-n
 */
public class Seventeen_ReverseLinkedList {
    public static void main(String args[]) {
        //Example 1
        Node input = new Node(2);
        input.right = new Node(4);
        input.right.right = new Node(6);
        input.right.right.right = new Node(8);
        input.right.right.right.right = new Node(10);

        System.out.print("Input: m=2,n=4 ");
        printLinkedList(input);
        reverseSection(input, 2,4);
        System.out.print("Output: ");
        printLinkedList(input);

        //Example 2
        input = new Node(10);
        input.right = new Node(20);
        input.right.right = new Node(2);
        input.right.right.right = new Node(5);
        input.right.right.right.right = new Node(9);
        input.right.right.right.right.right = new Node(8);

        System.out.print("Input: m=3,n=3 ");
        printLinkedList(input);
        reverseSection(input, 3,3);
        System.out.print("Output: ");
        printLinkedList(input);
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
        System.out.println();
    }

    public static Node reverseSection(Node head, int m, int n) {
        if(m==n) {
            return head;
        }

        Node prev = null;
        //First will be the node before m
        Node first = new Node(0);
        //Second will be the node after n
        Node second = new Node(0);

        int i=0;
        Node pointer = head;

        while(pointer != null) {
            i++;
            if(i==m-1) {
                prev = pointer;
            }
            if(i==m) {
                first.right = pointer;
            }
            if(i==n) {
                second.right = pointer.right;
                pointer.right = null;
            }

            pointer = pointer.right;
        }

        if(first.right == null) {
            return head;
        }

        //The first node to be swapped
        Node pointer1 = first.right;
        //The second node to be swapped
        Node pointer2 = pointer1.right;
        pointer1.right = second.right;

        while(pointer1 != null && pointer2 != null) {
            Node temp = pointer2.right;
            pointer2.right = pointer1;
            pointer1=pointer2;
            pointer2=temp;
        }

        if(prev != null) {
            prev.right = pointer1;
        } else {
            return pointer1;
        }

        return head;
    }
}
