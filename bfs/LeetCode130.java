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
    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        Deque<Point> deque = new LinkedList<>();
        // 找到边界上所有的O
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    if (board[i][j] == 'O') {
                        deque.offer(new Point(i, j));
                    }
                }
            }
        }
        // BFS遍历
        while (!deque.isEmpty()) {
            Point cur = deque.poll();
            if (valid(cur.x, cur.y, row, col) && board[cur.x][cur.y] == 'O') {
                board[cur.x][cur.y] = 'B';
                for (int[] direction: directions) {
                    deque.offer(new Point(cur.x + direction[0], cur.y + direction[1]));
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private boolean valid(int rowIndex, int colIndex, int row, int col) {
        return rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col;
    }
}
