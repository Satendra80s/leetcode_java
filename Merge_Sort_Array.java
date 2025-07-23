import java.util.Arrays;

public class Merge_Sort_Array {
    
    /**
     * Sort array using merge sort algorithm
     * Time: O(n log n), Space: O(n)
     */
    public static int[] mergeSort(int[] nums) {
        if (nums.length <= 1) {
            return nums.clone();
        }
        
        // Divide
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        
        left = mergeSort(left);
        right = mergeSort(right);
        
        // Conquer (merge)
        return merge(left, right);
    }
    
    /**
     * Merge two sorted arrays
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        
        // Compare elements and merge
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        
        // Add remaining elements
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        
        return result;
    }
    
    /**
     * In-place merge sort (more space efficient)
     */
    public static void mergeSortInPlace(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        mergeSortHelper(nums, 0, nums.length - 1);
    }
    
    private static void mergeSortHelper(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(nums, left, mid);
            mergeSortHelper(nums, mid + 1, right);
            mergeInPlace(nums, left, mid, right);
        }
    }
    
    private static void mergeInPlace(int[] nums, int left, int mid, int right) {
        // Create temp arrays
        int[] L = Arrays.copyOfRange(nums, left, mid + 1);
        int[] R = Arrays.copyOfRange(nums, mid + 1, right + 1);
        
        int i = 0, j = 0, k = left;
        
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) {
                nums[k++] = L[i++];
            } else {
                nums[k++] = R[j++];
            }
        }
        
        // Copy remaining elements
        while (i < L.length) {
            nums[k++] = L[i++];
        }
        while (j < R.length) {
            nums[k++] = R[j++];
        }
    }
    
    // Test examples
    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {5, 2, 3, 1};
        System.out.println("Original: " + Arrays.toString(arr1));
        int[] sorted1 = mergeSort(arr1);
        System.out.println("Sorted: " + Arrays.toString(sorted1));
        
        // Test case 2
        int[] arr2 = {5, 1, 1, 2, 0, 0};
        System.out.println("\nOriginal: " + Arrays.toString(arr2));
        int[] sorted2 = mergeSort(arr2);
        System.out.println("Sorted: " + Arrays.toString(sorted2));
        
        // Test in-place version
        int[] arr3 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("\nIn-place test - Original: " + Arrays.toString(arr3));
        mergeSortInPlace(arr3);
        System.out.println("In-place sorted: " + Arrays.toString(arr3));
    }
}