class Solution {
    public int[] singleNumber(int[] nums) {
        int[] answer = new int[2];
        int temp = 0;
        for (int num : nums) {
            temp ^= num;
        }
        int group = temp & (-temp);
        for (int num : nums) {
            if ((num & group) == 0) {
                answer[0] ^= num;
            } else {
                answer[1] ^= num;
            }
        }
        return answer;
    }
}
