/**
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

 

示例 1：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：

输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：

输入：nums = [1]
输出：[[1]]
 

提示：

1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/permutations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTracking(nums, new ArrayList<>(), result, new boolean[nums.length]);
        return result;
    }

    private void backTracking(int[] nums, List<Integer> path, List<List<Integer>> result, boolean[] visited) {
        int len = nums.length;
        if (path.size() == len) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            backTracking(nums, path, result, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
