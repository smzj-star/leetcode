class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int len = sentence.length;
        // dp[i]表示一行以第sentence[i]开头出现的结尾单词的个数
        // 如 a bcd e
        // a bcd e a bcd e a, sentence[i]开头:a开头,出现的结尾单词:e
        // dp[0] = 2;
        int[] dp = new int[len];
        // next[i]表示一行以sentence[i]开头,下一行出现的首个字符串在字符串数组中的索引
        // a bcd e a bcd e a,
        // bcd e a bcd e a -,
        // bcd e a bcd e a -,
        // sentence[i]开头:a开头,下一行出现的首个字符串索引:即bcd在字符串数组中的索引1
        // next[0] = 1;
        // next[1] = 1;表示bcd开头,下一行还是bcd开头
        int[] next = new int[len];
        for (int i = 0; i < len; i++) {
            int start = i;
            int remainLen = cols;
            int count = 0;
            while (remainLen >= sentence[start].length()) {
                remainLen -= sentence[start].length() + 1;
                start++;
                if (start == len) {
                    start = 0;
                    count++;
                }
            }
            dp[i] = count;
            next[i] = start;
        }
        int result = 0;
        int startIndex = 0;
        for (int i = 0; i < rows; i++) {
            result += dp[startIndex];
            startIndex = next[startIndex];
        }
        return result;
    }
}
