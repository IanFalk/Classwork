/**
 * Ian Falk
 * https://afteracademy.com/problems/calculate-power-function
 */
public class Four_powerFunction {
    public static void main(String args[]) {
        int n=2,k=4;
        System.out.println("Input: "+k+"^"+n+". Output: "+calculatePower(n,k));
        n=3;
        k=-7;
        System.out.println("Input: "+k+"^"+n+". Output: "+calculatePower(n,k));
        n=4;
        k=1;
        System.out.println("Input: "+k+"^"+n+". Output: "+calculatePower(n,k));
        n=0;
        k=4;
        System.out.println("Input: "+k+"^"+n+". Output: "+calculatePower(n,k));
        n=3;
        k=-1;
        System.out.println("Input: "+k+"^"+n+". Output: "+calculatePower(n,k));
    }

    public static int calculatePower(int n, int k) {
        int result=1;
        for(int i=0; i<n; i++) {
            result = result * k;
        }
        return result;
    }
}
