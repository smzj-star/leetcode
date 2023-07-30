class Solution {
    private int cols;

    public int numSubmat(int[][] mat) {
        int rows = mat.length;
        cols = mat[0].length;
        int[] dp = new int[cols];
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    dp[j]++;
                } else {
                    dp[j] = 0;
                }
            }
            result += calcByRow(dp);
        }
        return result;
    }

    private int calcByRow(int[] dp) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int sum = 0;
        for (int i = 0; i < cols; i++) {
            while (stack.peek() != -1 && dp[i] <= dp[stack.peek()]) {
                int top = stack.pop();
                sum += dp[top] * (top - stack.peek()) * (i - top);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int top = stack.pop();
            sum += dp[top] * (top - stack.peek()) * (cols - top);
        }
        return sum;
    }
}
