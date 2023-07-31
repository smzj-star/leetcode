class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int first = 0; first < len - 2; first++) {
            if (nums[first] > 0) {
                break;
            }
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int left = first + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[first] + nums[left] + nums[right];
                if (sum == 0) {
                    // 去除重复元素
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }
}
