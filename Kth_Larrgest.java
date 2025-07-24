
    import java.util.*;

public class Kth_Larrgest {
    
    // Method 1: Using Min Heap (Priority Queue) - O(n log k)
    public int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        return minHeap.peek();
    }
    
    // Method 2: Using Sorting - O(n log n)
    public int findKthLargestSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    // Method 3: Using QuickSelect (Optimal) - Average O(n), Worst O(n²)
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) {
            return nums[left];
        }
        
        // Choose random pivot to avoid worst case
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        
        pivotIndex = partition(nums, left, right, pivotIndex);
        
        if (kSmallest == pivotIndex) {
            return nums[kSmallest];
        } else if (kSmallest < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, kSmallest);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, kSmallest);
        }
    }
    
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        
        // Move pivot to end
        swap(nums, pivotIndex, right);
        
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        
        // Move pivot to final position
        swap(nums, storeIndex, right);
        return storeIndex;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // Method 4: Using Max Heap - O(n + k log n)
    public int findKthLargestMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int num : nums) {
            maxHeap.offer(num);
        }
        
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        
        return maxHeap.poll();
    }
    
    // Test method
    public static void main(String[] args) {
        Kth_Larrgest solution = new Kth_Larrgest();
        
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("2nd largest: " + solution.findKthLargest(nums1, k1)); // Output: 5
        
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println("\nArray: " + Arrays.toString(nums2));
        System.out.println("4th largest: " + solution.findKthLargest(nums2, k2)); // Output: 4
        
        // Test all methods
        int[] test = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println("\nTesting all methods with array: " + Arrays.toString(test));
        System.out.println("Min Heap: " + solution.findKthLargestHeap(test.clone(), k));
        System.out.println("Sorting: " + solution.findKthLargestSort(test.clone(), k));
        System.out.println("QuickSelect: " + solution.findKthLargest(test.clone(), k));
        System.out.println("Max Heap: " + solution.findKthLargestMaxHeap(test.clone(), k));
    }
}
    
