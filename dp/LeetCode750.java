/**
给定一个只包含 0 和 1 的 m x n 整数矩阵 grid ，返回 其中 「角矩形 」的数量 。

一个「角矩形」是由四个不同的在矩阵上的 1 形成的 轴对齐 的矩形。注意只有角的位置才需要为 1。

注意：4 个 1 的位置需要是不同的。

 

示例 1：



输入：grid = [[1,0,0,1,0],[0,0,1,0,1],[0,0,0,1,0],[1,0,1,0,1]]
输出：1
解释：只有一个角矩形，角的位置为 grid[1][2], grid[1][4], grid[3][2], grid[3][4]。
示例 2：



输入：grid = [[1,1,1],[1,1,1],[1,1,1]]
输出：9
解释：这里有 4 个 2x2 的矩形，4 个 2x3 和 3x2 的矩形和 1 个 3x3 的矩形。
示例 3：



输入：grid = [[1,1,1,1]]
输出：0
解释：矩形必须有 4 个不同的角。
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] 不是 0 就是 1
网格中 1 的个数在 [1, 6000] 范围内

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/number-of-corner-rectangles
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        // dp[i][j] 表示第i列第j列同时为1的次数
        int[][] dp = new int[col][col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // 能执行到这里,表示找到了第j列为1
                for (int k = j + 1; k < col; k++) {
                    // 表示找到了第j列,第k列同时为1
                    if (grid[i][k] == 1) {
                        res += dp[j][k];
                        dp[j][k]++;
                    }
                }
            }
        }
        return res;
    }
}
