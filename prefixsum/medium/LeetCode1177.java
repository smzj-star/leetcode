class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        char[] array = s.toCharArray();
        int len = array.length;
        int[] pre = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            pre[i] = pre[i - 1] ^ (1 << (array[i - 1] - 'a'));
        }
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int bit = pre[query[1] + 1] ^ pre[query[0]];
            int oneCount = 0;
            while (bit != 0) {
                oneCount++;
                bit &= bit - 1;
            }
            result.add(oneCount <= (2 * query[2] + 1));
        }
        return result;
    }
}
