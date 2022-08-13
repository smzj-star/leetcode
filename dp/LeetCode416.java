/**
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 

示例 1：

输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：

输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
 

提示：

1 <= nums.length <= 200
1 <= nums[i] <= 100

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/partition-equal-subset-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[length][target + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= target; j++) {
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] == target) {
                    return true;
                }
                if (nums[i] < j && i > 0) {
                    // dp[i - 1][j]表示是否已存在子集和为j
                    // 如果背包里之前装得下10个物品，那么现在仍然可以装得下
                    // dp[i - 1][j - nums[i]]表示是否已存在子集和为j - nums[i]
                    // 如果背包里之前装得下5个物品，现在又有5个物品，那么现在就可以装得下10个物品
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
            if (dp[i][target]) {
                return true;
            }
        }
        return dp[length - 1][target];
    }
}
