class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[] result = new int[rows * cols];
        int count = 0;
        for (int i = 0; i < rows + cols - 1; i++) {
            if (i % 2 == 0) {
                int x = i < rows ? i : rows - 1;
                int y = i < rows ? 0 : i - rows + 1;
                while (x >= 0 && y < cols) {
                    result[count++] = mat[x][y];
                    x--;
                    y++;
                }
            } else {
                int x = i < cols ? 0 : i - cols + 1;
                int y = i < cols ? i : cols - 1;
                while (x < rows && y >= 0) {
                    result[count++] = mat[x][y];
                    x++;
                    y--;
                }
            }
        }
        return result;    
    }
}
