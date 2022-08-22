/**
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

 

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
 

提示：

1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 

进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/top-k-frequent-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    Map<Integer, Integer> map = new HashMap<>();

    public int[] topKFrequent(int[] nums, int k) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int[] array = new int[map.size()];
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            array[count] = entry.getKey();
            count++;
        }
        quickSort(array, 0, array.length - 1, k);
        int[] result = new int[k];
        System.arraycopy(array, array.length - k, result, 0, k);
        return result;
    }

    public void quickSort(int[] array, int left, int right, int k) {
        if (left < right) {
            int pviotPosition = partition(array, left, right);
            if (pviotPosition == array.length - k) {
                return;
            } else if (pviotPosition > array.length - k){
                quickSort(array, left, pviotPosition - 1, k);
            } else {
                quickSort(array, pviotPosition + 1, right, k);
            }
        }
    }

    public int partition(int[] array, int left, int right) {
        swap(left, (left + right) / 2);
        int pviotNum = array[left];
        while (left < right) {
            while (left < right && map.get(array[right]) >= map.get(pviotNum)) {
                right--;
            }
            array[left] =  array[right];
            while (left < right && map.get(array[left]) <= map.get(pviotNum)) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = pviotNum;
        return left;
    }

    public void swap(int a, int b) {
        if (a != b) {
            int temp = a;
            a = b;
            b = temp;
        }
    }
}
