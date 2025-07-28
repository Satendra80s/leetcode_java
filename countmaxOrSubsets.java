import java.util.Arrays;

public class countmaxOrSubsets {
    public int countMaxOrSubsets(int[] nums) {
    final int ors = Arrays.stream(nums).reduce((a, b) -> a | b).getAsInt();
    dfs(nums, 0, 0, ors);
    return ans;
  }

  private int ans = 0;

  private void dfs(int[] nums, int i, int path, final int ors) {
    if (i == nums.length) {
      if (path == ors)
        ++ans;
      return;
    }

    dfs(nums, i + 1, path, ors);
    dfs(nums, i + 1, path | nums[i], ors);
  }
  public static void main(String[] args) {
    countmaxOrSubsets Sol = new countmaxOrSubsets();
    int[] nums = {3, 1, 2, 5};
        System.out.println("Count of maximum bitwise OR subsets: " + Sol.countMaxOrSubsets(nums));
  }
}
