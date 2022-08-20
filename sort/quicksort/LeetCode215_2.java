/**
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

 

示例 1:

输入: [3,2,1,5,6,4], k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
 

提示：

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
/**
执行用时：4 ms, 在所有 Java 提交中击败了70.17%的用户
内存消耗：48.6 MB, 在所有 Java 提交中击败了35.51%的用户
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1, k);
        return nums[nums.length - k];
    }

    public void quickSort(int[] nums, int left, int right, int k) {
        if (left < right) {
            int pivotPosition = partition(nums, left, right);
            if (pivotPosition == nums.length - k) {
                return;
            }
            if (pivotPosition > nums.length - k) {
                quickSort(nums, left, pivotPosition - 1, k);
            } else {
                quickSort(nums, pivotPosition + 1, right, k);
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        swap(nums, left, (left + right) / 2);
        int pivotNum = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivotNum) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivotNum) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivotNum;
        return left;
    }

    public void swap(int[] nums, int a, int b) {
        if (nums[a] == nums[b]) {
            return;
        }
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
