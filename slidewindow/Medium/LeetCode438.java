class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        char[] array = s.toCharArray();
        int len = array.length;
        char[] pArray = p.toCharArray();
        int pLen = pArray.length;
        int[] pCount = new int[26];
        List<Integer> result = new ArrayList<>();
        if (len < pLen) {
            return result;
        }
        for (int i = 0; i < pLen; i++) {
            pCount[pArray[i] - 'a']++;
        }
        int[] sCount = new int[26];
        for (int i = 0; i < pLen; i++) {
            sCount[array[i] - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            result.add(0);
        }
        for (int i = 1; i + pLen - 1 < len; i++) {
            sCount[array[i - 1] - 'a']--;
            sCount[array[i + pLen - 1] - 'a']++;
            if (Arrays.equals(sCount, pCount)) {
                result.add(i);
            }
        }
        return result;
    }
}
