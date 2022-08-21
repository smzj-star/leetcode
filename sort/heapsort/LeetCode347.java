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
        int[] heap = new int[k];
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (count < k) {
                heap[count] = entry.getKey();
            }
            count++;
            if (count == k) {
                buildMinHeap(heap);
            }
            if (count > k) {
                // 调整小根堆
                if (entry.getValue() > map.get(heap[0])) {
                    heap[0] = entry.getKey();
                    heapAdjust(heap, 0, k);
                }
            }
        }
        return heap;
    }

    public void buildMinHeap(int[] heap) {
        int len = heap.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapAdjust(heap, i, len);
        }
    }

    public void heapAdjust(int[] heap, int root, int heapLen) {
        int temp = heap[root];
        for (int i = (root << 1) + 1; i < heapLen; i = (i << 1) + 1) {
            if (i < heapLen - 1 && map.get(heap[i]) > map.get(heap[i + 1])) {
                i++;
            }
            if (map.get(temp) <= map.get(heap[i])) {
                break;
            } 
            heap[root] = heap[i];
            root = i;
        }
        heap[root] = temp;
    }

    public void swap(int[] heap, int a, int b) {
        if (heap[a] != heap[b]) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
    }
}
