class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        for (int[] tower : towers) {
            minRow = Math.min(tower[0], minRow);
            maxRow = Math.max(tower[0], maxRow);
            minCol = Math.min(tower[1], minCol);
            maxCol = Math.max(tower[1], maxCol);
        }
        int[] result = new int[2];
        int max = 0;
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                int[] location = {i, j};
                int sum = 0;
                for (int[] tow : towers) {
                    int distance = (tow[0] - location[0]) * (tow[0] - location[0]) +
                            (tow[1] - location[1]) * (tow[1] - location[1]);
                    if (distance <= radius * radius) {
                        sum += tow[2] / (1 + Math.sqrt(distance));
                    }
                }
                if (sum > max) {
                    max = sum;
                    result[0] = location[0];
                    result[1] = location[1];
                }
            }
        }
        return result;
    }
}
