import java.util.Stack;

/**
 * Ian Falk
 * https://afteracademy.com/problems/check-if-a-singly-linked-list-is-palindrome
 */
public class Eighteen_LinkedListPalindrome {
    public static void main(String args[]) {
        //Example 1
        Node input = new Node(4);
        input.right = new Node(5);
        input.right.right = new Node(4);

        System.out.print("Input: ");
        printLinkedList(input);
        System.out.println("Is palindrome? "+isPalindrome(input));

        //Example 2
        input = new Node(8);

        System.out.print("Input: ");
        printLinkedList(input);
        System.out.println("Is palindrome? "+isPalindrome(input));

        //Example 3
        input = new Node(2);
        input.right = new Node(5);
        input.right.right = new Node(5);
        input.right.right.right = new Node(2);

        System.out.print("Input: ");
        printLinkedList(input);
        System.out.println("Is palindrome? "+isPalindrome(input));

        //Example 4
        input = new Node(2);
        input.right = new Node(1);

        System.out.print("Input: ");
        printLinkedList(input);
        System.out.println("Is palindrome? "+isPalindrome(input));
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

    public static boolean isPalindrome(Node head) {
        Node store = head;
        Stack<Integer> stack = new Stack<Integer>();
        boolean cond = false;

        //Put all nodes of linked list into a stack
        while(store != null) {
            stack.push(store.data);
            store = store.right;
        }

        while(head != null) {
            if(head.data == stack.peek()) {
                cond=true;
            } else {
                cond=false;
            }
            head = head.right;
            stack.pop();
        }
        return cond;
    }
}
