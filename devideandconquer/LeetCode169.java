/**
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1：

输入：nums = [3,2,3]
输出：3
示例 2：

输入：nums = [2,2,1,1,1,2,2]
输出：2
 

提示：
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
 

进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/majority-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int majorityElement(int[] nums) {
        return devideAndConquer(nums, 0, nums.length - 1);
    }

    private int devideAndConquer(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        int leftMost = devideAndConquer(nums, left, mid);
        int rightMost = devideAndConquer(nums, mid + 1, right);
        if (leftMost == rightMost) {
            return leftMost;
        }
        int leftMostAppearCount = computeAppearCount(nums, left, right, leftMost);
        int rightMostAppearCount = computeAppearCount(nums, left, right, rightMost);
        return leftMostAppearCount >= rightMostAppearCount ? leftMost : rightMost;
    }

    private int computeAppearCount(int[] nums, int left, int right, int target) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == target) {
                count++;
            }
        }
        return count;
    }
}
