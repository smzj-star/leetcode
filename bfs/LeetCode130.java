/**
给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 

示例 1：


输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
示例 2：

输入：board = [["X"]]
输出：[["X"]]
 

提示：

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] 为 'X' 或 'O'

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/surrounded-regions
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Deque<int[]> deque = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    if (board[i][j] == 'O') {
                        deque.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
        }
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            for (int[] direction : directions) {
                int rowIndex = cur[0] + direction[0];
                int colIndex = cur[1] + direction[1];
                if (isValid(rowIndex, colIndex, board, visited)) {
                    visited[rowIndex][colIndex] = true;
                    deque.offer(new int[]{rowIndex, colIndex});
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private boolean isValid(int rowIndex, int colIndex, char[][] board, boolean[][] visited) {
        return rowIndex >= 0 && rowIndex < board.length && colIndex >= 0 &&
                colIndex < board[0].length && board[rowIndex][colIndex] == 'O' && !visited[rowIndex][colIndex];
    }
}
