/**
给你一个大小为 m x n 的图像 picture ，图像由黑白像素组成，'B' 表示黑色像素，'W' 表示白色像素，请你统计并返回图像中 黑色 孤独像素的数量。

黑色孤独像素 的定义为：如果黑色像素 'B' 所在的同一行和同一列不存在其他黑色像素，那么这个黑色像素就是黑色孤独像素。

 

示例 1：


输入：picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
输出：3
解释：全部三个 'B' 都是黑色的孤独像素
示例 2：


输入：picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
输出：0

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/lonely-pixel-i
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int rows = picture.length;
        int cols = picture[0].length;
        int result = 0;
        boolean[] rowLop = new boolean[rows];
        boolean[] colLop = new boolean[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B' && !rowLop[i] && !colLop[j]) {
                    boolean rightFlag = dfs(picture, i, j + 1, 0);
                    boolean downFlag = dfs(picture, i + 1, j, 1);
                    boolean leftFlag = dfs(picture, i, j - 1, 2);
                    boolean upFlag = dfs(picture, i - 1, j, 3);
                    if (rightFlag && downFlag && leftFlag && upFlag) {
                        rowLop[i] = true;
                        colLop[j] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private boolean dfs(char[][] picture, int row, int col, int direction) {
        if (row < 0 || row == picture.length || col < 0 || col == picture[0].length) {
            return true;
        } 
        if (picture[row][col] == 'B') {
            return false;
        }
        if (direction == 0) {
            return dfs(picture, row, col + 1, 0);
        }
        if (direction == 1) {
            return dfs(picture, row + 1, col, 1);
        }
        if (direction == 2) {
            return dfs(picture, row, col - 1, 2);
        }
        if (direction == 3) {
            return dfs(picture, row - 1, col, 3);
        }
        return true;
    }
}
