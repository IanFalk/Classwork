/**
 * Ian Falk
 * https://afteracademy.com/problems/greatest-common-divisor
 */
public class Nine_GreatestCommonDivisor {
    public static void main(String args[]) {
        int num1=54;
        int num2=24;
        System.out.println("Input: Num1= "+num1+" Num2= "+num2+" ; Output: "+Divisor(num1,num2));
        num1=100;
        num2=50;
        System.out.println("Input: Num1= "+num1+" Num2= "+num2+" ; Output: "+Divisor(num1,num2));
        num1=21;
        num2=70;
        System.out.println("Input: Num1= "+num1+" Num2= "+num2+" ; Output: "+Divisor(num1,num2));
    }

    public static int Divisor(int num1, int num2) {
        int result=0;
        for(int i=1; i<=num1 && i <=num2; i++) {
            if(num1 % i ==0 && num2%i==0) {
                result = i;
            }
        }
        return result;
    }
}
