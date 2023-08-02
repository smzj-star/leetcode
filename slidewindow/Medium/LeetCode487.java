class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int len = nums.length;
        int count = 0;
        int left = 0;
        int result = 0;
        for (int right = 0; right < len; right++) {
            if (nums[right] == 0) {
                count++;
            }
            while (count > 1) {
                if (nums[left++] == 0) {
                    count--;
                }
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
