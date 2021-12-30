/**
 * Ian Falk
 * https://afteracademy.com/problems/remove-nth-node-from-list-end
 */
public class TwentyOne_RemoveNthNode {
    public static void main(String args[]) {
        //Example 1
        Node input = new Node(2);
        input.right = new Node(6);
        input.right.right = new Node(1);
        input.right.right.right = new Node(9);
        int n=2;
        System.out.print("Input: n=2, ");
        printLinkedList(input);
        System.out.print("\nOutput: ");
        printLinkedList(removeNth(input,n));

        //Example 2
        input = new Node(1);
        input.right = new Node(2);
        input.right.right = new Node(3);
        input.right.right.right = new Node(4);
        n=4;
        System.out.print("\nInput: n=4, ");
        printLinkedList(input);
        System.out.print("\nOutput: ");
        printLinkedList(removeNth(input,n));

        //Example 3
        input = new Node(4);
        input.right = new Node(9);
        input.right.right = new Node(1);
        input.right.right.right = new Node(2);
        n=1;
        System.out.print("\nInput: n=1, ");
        printLinkedList(input);
        System.out.print("\nOutput: ");
        printLinkedList(removeNth(input,n));
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

    public static Node removeNth(Node head, int n) {
        Node temp = head;
        //Get the length of the linked list
        int length=0;
        while(temp != null) {
            length++;
            temp=temp.right;
        }

        if(length==n) {
            return head.right;
        }

        temp = head;
        for(int i=1; i<length-n; i++) {
            temp=temp.right;
        }
        temp.right = temp.right.right;
        return head;
    }
}
