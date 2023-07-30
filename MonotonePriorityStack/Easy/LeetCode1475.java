class Solution {
    public int[] finalPrices(int[] prices) {
        int len = prices.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] result = new int[len];
        System.arraycopy(prices, 0, result, 0, len);
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                result[stack.pop()] -= prices[i];
            }
            stack.push(i);
        }
        return result;
    }
}
