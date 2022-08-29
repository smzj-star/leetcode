/**
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 

示例 1:



输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10
示例 2：



输入： heights = [2,4]
输出： 4
 

提示：

1 <= heights.length <=105
0 <= heights[i] <= 104

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/largest-rectangle-in-histogram
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        int[] newHeights = new int[heights.length + 2];
        int length = newHeights.length;
        newHeights[0] = 0;
        newHeights[length - 1] = 0;
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < length; i++) {
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int currentHeight = newHeights[stack.pop()];
                int width = i - stack.peek() - 1;
                result = Math.max(result, currentHeight * width);
            }
            stack.push(i);
        }
        return result;
    }
}
