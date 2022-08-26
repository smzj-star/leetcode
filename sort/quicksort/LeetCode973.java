/**
给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，并且是一个整数 k ，返回离原点 (0,0) 最近的 k 个点。

这里，平面上两点之间的距离是 欧几里德距离（ √(x1 - x2)2 + (y1 - y2)2 ）。

你可以按 任何顺序 返回答案。除了点坐标的顺序之外，答案 确保 是 唯一 的。

 

示例 1：



输入：points = [[1,3],[-2,2]], k = 1
输出：[[-2,2]]
解释： 
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
示例 2：

输入：points = [[3,3],[5,-1],[-2,4]], k = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）
 

提示：

1 <= k <= points.length <= 104
-104 < xi, yi < 104

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/k-closest-points-to-origin
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        quickSort(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private void quickSort(int[][] points, int left, int right, int k) {
        if (left < right) {
            int pviotPosition = partition(points, left, right);
            if (pviotPosition == k - 1) {
                return;
            } else if (pviotPosition > k - 1) {
                quickSort(points, left, pviotPosition - 1, k);
            } else {
                quickSort(points, pviotPosition + 1, right, k);
            }
        }
    }

    private int partition(int[][] points, int left, int right) {
        swap(points, left, (left + right) / 2);
        int[] pviotNum = points[left];
        int distance = distance(pviotNum);
        while (left < right) {
            while (left < right && distance(points[right]) > distance) {
                right--;
            }
            points[left] = points[right];
            while (left < right && distance(points[left]) <= distance) {
                left++;
            }
            points[right] = points[left];
        }
        points[left] = pviotNum;
        return left;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private void swap(int[][] points, int a, int b) {
        int[] temp = points[a];
        points[a] = points[b];
        points[b] = temp;
    }
}
