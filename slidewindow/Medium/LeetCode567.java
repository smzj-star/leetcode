class Solution {
    public boolean checkInclusion(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        int len1 = arr1.length;
        char[] arr2 = s2.toCharArray();
        int len2 = arr2.length;
        int[] a1 = new int[26];
        if (len1 > len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            a1[arr1[i] - 'a']++;
        }
        int[] a2 = new int[26];
        for (int i = 0; i < len1; i++) {
            a2[arr2[i] - 'a']++;
        }
        if (Arrays.equals(a1, a2)) {
            return true;
        }
        for (int i = 1; i < len2 - len1 + 1; i++) {
            a2[arr2[i - 1] - 'a']--;
            a2[arr2[i + len1 - 1] - 'a']++;
            if (Arrays.equals(a1, a2)) {
                return true;
            }
        }
        return false;
    }
}
