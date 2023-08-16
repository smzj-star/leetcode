class Solution {
    public boolean isPalindrome(String s) {
        char[] array = s.toLowerCase().toCharArray();
        int len = array.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(array[right])) {
                right--;
            }
            while (left < right && !Character.isLetterOrDigit(array[left])) {
                left++;
            }
            if (left < right) {
                if (array[left] != array[right]) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
