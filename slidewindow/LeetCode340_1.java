class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }
        char[] array = s.toCharArray();
        int len = array.length;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < len; right++) {
            int count = map.getOrDefault(array[right], 0);
            map.put(array[right], ++count);
            if (map.size() > k) {
                while (map.size() > k) {
                    int num = map.get(array[left]);
                    map.put(array[left], --num);
                    if (map.get(array[left]) == 0) {
                        map.remove(array[left]);
                    }
                    left++;
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
