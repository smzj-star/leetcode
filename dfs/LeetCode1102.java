/**
给定一个 m x n 的整数矩阵 grid，返回从 (0,0) 开始到 (m - 1, n - 1) 在四个基本方向上移动的路径的最大 分数 。

一条路径的 分数 是该路径上的最小值。

例如，路径 8 → 4 → 5 → 9 的得分为 4 。
 

示例 1：



输入：grid = [[5,4,5],[1,2,6],[7,4,6]]
输出：4
解释：得分最高的路径用黄色突出显示。 
示例 2：



输入：grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
输出：2
示例 3：



输入：grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
输出：3
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 100
0 <= grid[i][j] <= 109

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/path-with-maximum-minimum-value
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int maximumMinimumPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxScore = Math.min(grid[0][0], grid[rows - 1][cols - 1]);
        int left = 0;
        int right = maxScore;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dfs(grid, 0, 0, mid, new boolean[rows][cols])) {
                result = Math.max(result, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private boolean dfs(int[][] grid, int row, int col, int score, boolean[][] visited) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return true;
        }
        if (row < 0 || row == grid.length || col < 0 || col == grid[0].length || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        if (grid[row][col] < score) {
            return false;
        }
        for (int[] direction : directions) {
            int rowIndex = row + direction[0];
            int colIndex = col + direction[1];
            if (dfs(grid, rowIndex, colIndex, score, visited)) {
                return true;
            }
        }
        return false;
    }
}
