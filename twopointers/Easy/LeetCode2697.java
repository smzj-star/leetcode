class Solution {
    public String makeSmallestPalindrome(String s) {
        char[] array = s.toCharArray();
        int len = array.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            if (array[left] > array[right]) {
                array[left] = array[right];
            } else {
                array[right] = array[left];
            }
            left++;
            right--;
        }
        return new String(array);
    }
}
