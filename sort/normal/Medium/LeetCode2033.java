class Solution {
    public int minOperations(int[][] grid, int x) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] array = new int[grid.length * grid[0].length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[j + i * cols] = grid[i][j];
            }
        }
        Arrays.sort(array);
        int len = array.length;
        int mid = array[len / 2];
        int sum = 0;
        int count;
        for (int i = 0; i < len; i++) {
            count = Math.abs(array[i] - mid);
            if (count % x != 0) {
                return -1;
            }
            sum += count / x;
        }
        return sum;
    }
}
