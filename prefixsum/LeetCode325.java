/**
给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

 

示例 1:

输入: nums = [1,-1,5,-2,3], k = 3
输出: 4 
解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
示例 2:

输入: nums = [-2,-1,2,1], k = 1
输出: 2 
解释: 子数组 [-1, 2] 和等于 1，且长度最长。
 

提示：

1 <= nums.length <= 2 * 105
-104 <= nums[i] <= 104
-109 <= k <= 109

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
/**
执行用时：57 ms, 在所有 Java 提交中击败了58.61%的用户
内存消耗：76.1 MB, 在所有 Java 提交中击败了32.59%的用户
*/
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        int pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0;
        for (int i = 0; i < len; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                res = Math.max(res, i - map.get(pre -k));
            } 
            map.putIfAbsent(pre, i);
        }
        return res;
    }
}
