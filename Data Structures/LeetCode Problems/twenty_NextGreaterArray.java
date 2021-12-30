import java.util.Arrays;

/**
 * Ian Falk
 * https://afteracademy.com/problems/find-next-greater-element-in-an-array
 */
public class twenty_NextGreaterArray {
    public static void main(String args[]) {
        //Example 1
        int[] input = new int[]{1,2,3,4,5};
        System.out.println("Input: "+Arrays.toString(input));
        System.out.println("Output: "+Arrays.toString(traverse(input)));

        //Example 2
        input = new int[]{12,1,0,17,10};
        System.out.println("Input: "+Arrays.toString(input));
        System.out.println("Output: "+Arrays.toString(traverse(input)));
    }

    public static int[] traverse(int[] arr) {
        int[] result = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
            result[i] = nextGreater(arr,i);
        }
        return result;
    }

    public static int nextGreater(int[] arr, int index) {
        int val = arr[index];
        for(int i=index+1; i<arr.length; i++) {
            if(arr[i] > val) {
                return arr[i];
            }
        }
        return -1;
    }
}
