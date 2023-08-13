class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int row = 0;
        int col = 0;
        int direction = 0;
        while (result.size() < matrix.length * matrix[0].length) {
            result.add(matrix[row][col]);
            visited[row][col] = true;
            int nextRow = row + directions[direction][0];
            int nextCol = col + directions[direction][1];
            if (nextRow < 0 || nextRow == matrix.length || nextCol < 0 || nextCol == matrix[0].length || visited[nextRow][nextCol]) {
                direction = (direction + 1) % 4;
            }
            row += directions[direction][0];
            col += directions[direction][1];
        }
        return result;
    }
}
