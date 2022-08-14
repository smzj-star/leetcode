/**
给你一个大小为 m x n 的矩阵 grid ，其中每个单元格都放置有一个字符：

'W' 表示一堵墙
'E' 表示一个敌人
'0'（数字 0）表示一个空位
返回你使用 一颗炸弹 可以击杀的最大敌人数目。你只能把炸弹放在一个空位里。

由于炸弹的威力不足以穿透墙体，炸弹只能击杀同一行和同一列没被墙体挡住的敌人。

 

示例 1：


输入：grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
输出：3
示例 2：


输入：grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
输出：1
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] 可以是 'W'、'E' 或 '0'

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/bomb-enemy
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        // 可以炸死的最多敌人个数
        int max = 0;
        //为了防止空位上面的敌人没有被炸死
        int[] colCount = new int[col];
        for (int i = 0; i < row; i++) {
            // 为了防止空位左边的敌人没有被炸死
            int rowCount = 0;
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    // 初始化为当前空位可以炸死的敌人个数
                    int total = rowCount + colCount[j];
                    // 遍历炸弹所在行的可以炸死的敌人个数
                    for (int k = j + 1; k < col && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            total++;
                        }
                    }
                    // 遍历炸弹所在列的可以炸死的敌人个数
                    for (int k = i + 1; k < row && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            total++;
                        }
                    }
                    max = Math.max(max, total);
                } else if (grid[i][j] == 'W') {
                    // 炸弹不能炸死墙体左边的敌人
                    rowCount = 0; 
                    // 炸弹不能炸死墙体上面的敌人
                    colCount[j] = 0; 
                } else {
                    rowCount++;
                    colCount[j]++;
                }
            }
        }
        return max;
    }
}
