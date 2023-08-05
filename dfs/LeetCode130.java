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
                        dfs(deque, visited, board);
                    }
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

    private void dfs(Deque<int[]> deque, boolean[][] visited, char[][] board) {
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            for (int[] direction : directions) {
                int rowIndex = cur[0] + direction[0];
                int colIndex = cur[1] + direction[1];
                if (isValid(rowIndex, colIndex, board, visited)) {
                    visited[rowIndex][colIndex] = true;
                    deque.offer(new int[]{rowIndex, colIndex});
                    dfs(deque, visited, board);
                }
            }
        }
    }

    private boolean isValid(int rowIndex, int colIndex, char[][] board, boolean[][] visited) {
        return rowIndex >= 0 && rowIndex < board.length && colIndex >= 0 &&
                colIndex < board[0].length && board[rowIndex][colIndex] == 'O' && !visited[rowIndex][colIndex];
    }
}
