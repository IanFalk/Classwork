/**
 * Ian Falk
 * https://afteracademy.com/problems/square-root-of-integer
 */
public class Three_sqrtOfInteger {
    public static void main(String args[]) {
        int num = 4;
        System.out.println("Input: "+num+". Output: "+squareRoot(num));
        num = 11;
        System.out.println("Input: "+num+". Output: "+squareRoot(num));
        num = 121;
        System.out.println("Input: "+num+". Output: "+squareRoot(num));
        num = 6;
        System.out.println("Input: "+num+". Output: "+squareRoot(num));
        num = 143;
        System.out.println("Input: "+num+". Output: "+squareRoot(num));
    }

    public static int squareRoot(int num) {
        return (int)Math.sqrt(num);
    }
}
