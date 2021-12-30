/**
 * Ian Falk
 * https://afteracademy.com/problems/merge-two-sorted-arrays
 */

import java.util.Arrays;

public class ten_MergeArrays {
    public static void main(String args[]) {
        int[] input1 = {3,9,10,18,23};
        int[] input2 = {5,12,15,20,21,25};
        System.out.println("Input: arr1[]= "+Arrays.toString(input1)+", arr2[]= "+Arrays.toString(input2)+"\nOutput: "+Arrays.toString(mergeArrays(input1,input2)));
        input1 = new int[]{1,2,5,9,10};
        input2 = new int[]{3,4,6,7,8,11,12};
        System.out.println("Input: arr1[]= "+Arrays.toString(input1)+", arr2[]= "+Arrays.toString(input2)+"\nOutput: "+Arrays.toString(mergeArrays(input1,input2)));
    }

    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];

        int i=0,j=0,k=0;

        while (i<arr1.length && j<arr2.length) {
            if (arr1[i] < arr2[j]) {
                merged[k] = arr1[i];
                i++;
            } else {
                merged[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < arr1.length) {
            merged[k] = arr1[i];
            i++;
            k++;
        }

        while (j < arr2.length) {
            merged[k] = arr2[j];
            j++;
            k++;
        }

        return merged;
    }
}
