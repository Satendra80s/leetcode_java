class CountHillValley {
  public int countHillValley(int[] nums) {
    int ans = 0;
    int left = nums[0];

    for (int i = 1; i + 1 < nums.length; ++i)
      if (left < nums[i] && nums[i] > nums[i + 1] || // the hill
          left > nums[i] && nums[i] < nums[i + 1]) { // the valley
        ++ans;
        left = nums[i];
      }

    return ans;
  }
  public static void main(String[] args) {

    CountHillValley count =new CountHillValley();
    int[] test1 = {2, 4, 1, 1, 6, 5};
        System.out.println("Input: [2,4,1,1,6,5]");
        System.out.println("Output: " + count.countHillValley(test1));
        System.out.println("Expected: 3 (hills at index 1,4; valley at index 2)");
        System.out.println();
        
  }
}