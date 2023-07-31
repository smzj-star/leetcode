class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 1;
        int count = 0;
        while (right < nums.length) {
            while (nums[right] == nums[left]) {
                len--;
                if (right == nums.length - 1) {
                    break;
                }
                right++;
            }
            nums[++count] = nums[right];
            left = right++;
        }
        return len;
    }
}
