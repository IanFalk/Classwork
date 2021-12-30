import java.util.Stack;
/**
 * Ian Falk
 * https://afteracademy.com/problems/check-for-balanced-parentheses-in-an-expression
 */
public class Six_BalancedParentheses {
    public static void main(String args[]) {
        String input = "(([](){}))";
        System.out.println("Input: "+input+"; Output: "+balance(input));
        input = "([)]";
        System.out.println("Input: "+input+"; Output: "+balance(input));
        input = "()[]({})";
        System.out.println("Input: "+input+"; Output: "+balance(input));
    }

    public static int balance(String symbols) {
        Stack stack = new Stack();

        //This for loop will add all the balancing symbols to the stack.
        for(int i=0; i<symbols.length(); i++) {
            if(symbols.charAt(i) == '(' || symbols.charAt(i) == '[' || symbols.charAt(i) == '{') {
                //If it is the opening symbol ( or [ or { , push it onto the stack
                stack.push(symbols.charAt(i));
            } else if(symbols.charAt(i) == ')' || symbols.charAt(i) == ']' || symbols.charAt(i) == '}') {
                if(stack.isEmpty()) {
                    return 0;
                }

                if(symbols.charAt(i) == ')' && ((char)stack.peek() == '(')) {
                    stack.pop();
                } else if(symbols.charAt(i) == ']' && ((char)stack.peek() == '[')) {
                    stack.pop();
                } else if(symbols.charAt(i) == '}' && ((char)stack.peek() == '{')) {
                    stack.pop();
                } else {
                    return 0;
                }
            }
        }

        //Check if the stack is empty after looping through whole string
        if(!stack.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }
}
