class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        char start = word.charAt(0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == start) {
                    if (dfs(board, i, j, word, 0, new boolean[rows][cols])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row == board.length || col < 0 || col == board[0].length || board[row][col] != word.charAt(index) || visited[row][col]) {
            return false;
        }
        index++;
        visited[row][col] = true;
        if (dfs(board, row, col + 1, word, index, visited) || dfs(board, row + 1, col, word, index, visited) || dfs(board, row, col - 1, word, index, visited) || dfs(board, row - 1, col, word, index, visited)) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }
}
