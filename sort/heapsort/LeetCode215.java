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

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return heapSort(nums, k);
    }

    public int heapSort(int[] nums, int k) {
        bulidMaxHeap(nums);
        int len = nums.length;
        for (int i = nums.length - 1; i > nums.length - k; i--) {
            swap(nums, 0, i);
            --len;
            heapAdjust(nums, 0, len);
        }
        return nums[0];
    }

    public void bulidMaxHeap(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapAdjust(nums, i, nums.length);
        }
    }

    /**
        @param nums 堆表示的数组
        @param root 子树的根节点的下标
        @param headLen 堆的长度
     */
    public void heapAdjust(int[] nums, int root, int headLen) {
        int temp = nums[root];
        for (int i = (root << 1) + 1; i < headLen; i = (i << 1) + 1) {
            if (i < headLen - 1 && nums[i] < nums[i + 1]) {
                i++;
            }
            if (temp >= nums[i]) {
                break;
            } else {
                nums[root] = nums[i];
                root = i;
            }
        }
        nums[root] = temp;
    }

    public void swap(int[] nums, int a, int b) {
        if (nums[a] != nums[b]) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
