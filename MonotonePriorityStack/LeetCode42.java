/**
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 

示例 1：



输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
示例 2：

输入：height = [4,2,0,3,2,5]
输出：9
 

提示：

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/trapping-rain-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int trap(int[] height) {
        int length = height.length;
        int answer = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 最短的柱子
                int minHeight = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                // 次短的柱子
                int middleHeight = Math.min(height[i], height[stack.peek()]);
                // 水高 = 次短的柱子 - 最短的柱子
                int waterHeight = middleHeight - minHeight;
                // 水宽 = 当前索引 - 左边柱子的索引 - 1
                int waterWidth = i - stack.peek() - 1;
                answer += waterHeight * waterWidth;
            }
            stack.push(i);
        }
        return answer;
    }
}
