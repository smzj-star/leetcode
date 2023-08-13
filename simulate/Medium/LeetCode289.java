class Solution {
    // 八个方向依次为右、下、左、上、右下、左下、左上、右上
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    // 过去是活的,现在是死的
    private static final int LIVE_TO_DIE = -1;

    // 过去是死的,现在是活的
    private static final int DIE_TO_LIVE = 2;

    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int liveCells;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 统计每一个细胞周围的活细胞数量
                liveCells = countLiveCells(board, i, j);
                // 死细胞复活
                if (board[i][j] == 0 && liveCells == 3) {
                    board[i][j] = DIE_TO_LIVE;
                }
                // 活细胞死亡
                if (board[i][j] == 1 && (liveCells < 2 || liveCells > 3)) {
                    board[i][j] = LIVE_TO_DIE;
                }
            }
        }
        refreshBoard(board);
    }

    private void refreshBoard(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == LIVE_TO_DIE) {
                    board[i][j] = 0;
                }
                if (board[i][j] == DIE_TO_LIVE) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int countLiveCells(int[][] board, int row, int col) {
        int liveCells = 0;
        int rowIndex;
        int colIndex;
        for (int[] direction : directions) {
            rowIndex = row + direction[0];
            colIndex = col + direction[1];
            // Math.abs(board[rowIndex][colIndex]) == 1
            // -1表示LIVE_TO_DIE,过去是活的
            if (rowIndex >= 0 && rowIndex < board.length
                    && colIndex >= 0 && colIndex < board[0].length
                    && Math.abs(board[rowIndex][colIndex]) == 1) {
                liveCells++;
            }
        }
        return liveCells;
    }
}
