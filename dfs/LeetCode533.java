/**
给你一个大小为 m x n 的二维字符数组 picture ，表示一张黑白图像，数组中的 'B' 表示黑色像素，'W' 表示白色像素。另给你一个整数 target ，请你找出并返回符合规则的 黑色 孤独像素的数量。

黑色孤独像素是指位于某一特定位置 (r, c) 的字符 'B' ，其中：

行 r 和列 c 中的黑色像素恰好有 target 个。
列 c 中所有黑色像素所在的行必须和行 r 完全相同。
 

示例 1：


输入：picture = [["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","W","B","W","B","W"]], target = 3
输出：6
解释：所有绿色的 'B' 都是我们所求的像素(第 1 列和第 3 列的所有 'B' )
以行 r = 0 和列 c = 1 的 'B' 为例：
- 规则 1 ，行 r = 0 和列 c = 1 都恰好有 target = 3 个黑色像素 
- 规则 2 ，列 c = 1 的黑色像素分别位于行 0，行 1 和行 2。和行 r = 0 完全相同。
示例 2：


输入：picture = [["W","W","B"],["W","W","B"],["W","W","B"]], target = 1
输出：0
 

提示：

m == picture.length
n == picture[i].length
1 <= m, n <= 200
picture[i][j] 为 'W' 或 'B'
1 <= target <= min(m, n)

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/lonely-pixel-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int findBlackPixel(char[][] picture, int target) {
        int rows = picture.length;
        int cols = picture[0].length;
        int result = 0;
        // 针对列进行剪枝，同一列只用计算一次
        boolean[] colLop = new boolean[cols];
        // 存储每行放的B数目
        int[] rowArray = new int[rows];
        // 存储每列放的B数目
        int[] colArray = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B' && !colLop[j]) {
                    int rowCount = rowArray[i];
                    if (rowArray[i] == 0) {
                        // dfs(picture, i, j + 1, 0, 0)表示右边的B数目
                        // dfs(picture, i, j - 1, 2, 0)表示左边的B数目
                        rowCount = dfs(picture, i, j + 1, 0, 0)
                                + dfs(picture, i, j - 1, 2, 0) + 1;
                        rowArray[i] = rowCount;
                    }
                    int colCount = colArray[j];
                    if (colArray[j] == 0) {
                        // dfs(picture, i + 1, j, 1, 0)表示下边的B数目
                        // dfs(picture, i - 1, j, 3, 0)表示上边的B数目
                        colCount = dfs(picture, i + 1, j, 1, 0)
                                + dfs(picture, i - 1, j, 3, 0) + 1;
                        colArray[j] = colCount;
                    }
                    if (rowCount == target && colCount == target) {
                        if (judgeAllColSameWithRow(i, j, picture)) {
                            colLop[j] = true;
                            // 同一列只用计算一次
                            result += colCount;
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean judgeAllColSameWithRow(int row, int col, char[][] picture) {
        for (int i = 0; i < picture.length; i++) {
            if (picture[i][col] == 'B') {
                if (!Arrays.equals(picture[i], picture[row])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 统计每一个方向的B数目
     *
     * @param picture 原数组
     * @param row 行
     * @param col 列
     * @param direction 方向,0->右,1->下,2->左,3->上
     * @param bCount B数目
     * @return 该方向的B总数
     */
    private int dfs(char[][] picture, int row, int col, int direction, int bCount) {
        if (row < 0 || row == picture.length || col < 0 || col == picture[0].length) {
            return bCount;
        }
        if (picture[row][col] == 'B') {
            bCount++;
        }
        return dfs(picture, row + directions[direction][0], col + directions[direction][1], direction, bCount);
    }
}
