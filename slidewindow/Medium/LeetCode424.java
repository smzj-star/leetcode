class Solution {
    public int characterReplacement(String s, int k) {
        char[] array = s.toCharArray();
        int len = array.length;
        int left = 0;
        int[] count = new int[26];
        int maxCount = 0;
        int result = 0;
        for (int right = 0; right < len; right++) {
            count[array[right] - 'A']++;
            maxCount = Math.max(maxCount, count[array[right] - 'A']);
            if (right - left + 1 > maxCount + k) {
                count[array[left++] - 'A']--;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
