class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    if (board[i][j] == 'O') {
                        dfs(board, i, j);
                    }
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        if (!isValid(row, col, board)) {
            return;
        }
        board[row][col] = 'B';
        for (int[] direction : directions) {
            dfs(board, row + direction[0], col + direction[1]);
        }
    }

    private boolean isValid(int rowIndex, int colIndex, char[][] board) {
        return rowIndex >= 0 && rowIndex < board.length && colIndex >= 0 &&
                colIndex < board[0].length && board[rowIndex][colIndex] == 'O';
    }
}
