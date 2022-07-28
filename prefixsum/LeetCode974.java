/**
给定一个整数数组 nums 和一个整数 k ，返回其中元素之和可被 k 整除的（连续、非空） 子数组 的数目。

子数组 是数组的 连续 部分。

 

示例 1：

输入：nums = [4,5,0,-2,-3,1], k = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 k = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
示例 2:

输入: nums = [5], k = 9
输出: 0
 

提示:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/subarray-sums-divisible-by-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int len = nums.length;
        int[] reminders = new int[k];
        reminders[0] = 1;
        int prefixSum = 0;
        int count = 0;
        int reminder = 0;
        for (int i = 0; i < len; i++) {
            prefixSum += nums[i];
            reminder = Math.floorMod(prefixSum, k);
            count += reminders[reminder];
            reminders[reminder]++; 
        }
        return count;
    }
}
