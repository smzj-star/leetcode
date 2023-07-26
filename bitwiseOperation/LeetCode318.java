class Solution {
    public int maxProduct(String[] words) {
        int result = 0;
        int len = words.length;
        // 记录所有单词的二进制表示,索引代表单词在数组中的索引,值为其对应的二进制值
        int[] hash = new int[len];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                hash[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((hash[i] & hash[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }
}
