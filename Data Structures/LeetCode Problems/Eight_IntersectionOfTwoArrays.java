import java.util.ArrayList;
import java.util.Arrays;

/**
 * Ian Falk
 * https://afteracademy.com/problems/intersection-of-two-unsorted-array
 */
public class Eight_IntersectionOfTwoArrays {
    public static void main(String args[]) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {5,4,3,2,1};
        System.out.println("Input: arr1[] = "+Arrays.toString(arr1)+", arr2[] = "+Arrays.toString(arr2)+"\nOutput: "+intersection(arr1,arr2));
        arr1 = new int[]{1,1,3,4,5};
        arr2 = new int[]{1,1,2,3,4,5};
        System.out.println("Input: arr1[] = "+Arrays.toString(arr1)+", arr2[] = "+Arrays.toString(arr2)+"\nOutput: "+intersection(arr1,arr2));
    }

    public static ArrayList<Integer> intersection(int[] arr1, int[] arr2) {
        ArrayList<Integer> union = new ArrayList<Integer>();
        for(int item1 : arr1) {
            for(int item2 : arr2) {
                if(item1 == item2 && !(union.contains(item1))) {
                    union.add(item1);
                }
            }
        }
        return union;
    }
}
