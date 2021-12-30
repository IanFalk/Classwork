/**
 * Ian Falk
 * https://afteracademy.com/problems/reverse-bits
 */
public class Two_ReverseBits {
    public static void main(String args[]) {
        int decimal = 6;
        System.out.println("Input: "+decimal+". Output: "+reverseBits(decimal));
        decimal = 13;
        System.out.println("Input: "+decimal+". Output: "+reverseBits(decimal));
        decimal = 2;
        System.out.println("Input: "+decimal+". Output: "+reverseBits(decimal));
        decimal = 130;
        System.out.println("Input: "+decimal+". Output: "+reverseBits(decimal));
        decimal = 17;
        System.out.println("Input: "+decimal+". Output: "+reverseBits(decimal));
    }

    public static int reverseBits(int decimal) {
        String bits = "";

        //This converts a decimal value to a binary value, in reverse bit order.
        while(decimal != 0) {
            if(decimal %2 ==1 ) {
                bits += "1";
            } else {
                bits += "0";
            }
            decimal = decimal/2;
        }
        //Parse int converts a string into an int, radix 2 specifies the input string is binary
        return Integer.parseInt(bits,2);
    }

}
