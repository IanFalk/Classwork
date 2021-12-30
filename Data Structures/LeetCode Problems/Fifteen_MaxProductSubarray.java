import java.util.Arrays;

/**
 * Ian Falk
 * https://afteracademy.com/problems/max-product-subarray
 */
public class Fifteen_MaxProductSubarray {
    public static void main(String args[]) {
        int[] arr = new int[]{9,-6,10,3};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+maxProduct(arr));
        arr = new int[]{6,-3,-10,0,2};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+maxProduct(arr));
        arr = new int[]{-2,-3,0,-2,-40};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+maxProduct(arr));
    }

    //Modified algorithm from my submission for #14
    public static int maxProduct(int[] arr) {
        int max=0;
        for(int i=0; i<arr.length; i++) {
            int product=1;
            for(int j=i; j<arr.length; j++) {
                product = product * arr[j];
                if(product > max) {
                    max = product;
                }
            }
        }
        return max;
    }
}
