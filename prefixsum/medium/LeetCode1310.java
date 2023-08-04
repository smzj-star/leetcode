class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int len = arr.length;
        int[] pre = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            pre[i] = pre[i - 1] ^ arr[i - 1];
        }
        int[] result = new int[queries.length];
        int count = 0;
        for (int[] query : queries) {
            result[count++] = pre[query[1] + 1] ^ pre[query[0]];
        }
        return result;
    }
}
