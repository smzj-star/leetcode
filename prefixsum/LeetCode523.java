/**
给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

子数组大小 至少为 2 ，且
子数组元素总和为 k 的倍数。
如果存在，返回 true ；否则，返回 false 。

如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。

 

示例 1：

输入：nums = [23,2,4,6,7], k = 6
输出：true
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
示例 2：

输入：nums = [23,2,6,4,7], k = 6
输出：true
解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
示例 3：

输入：nums = [23,2,6,4,7], k = 13
输出：false
 

提示：

1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/continuous-subarray-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
/**
【j......i】子数组的和为nums[i] - nums[j-1];

题目要求：子数组和为k的倍数

转化为：nums[i] - nums[j - 1] % k = 0;

应用同余定理：即nums[i] % k = nums[j - 1] % k;

和题目联系起来，如果【前缀和%k】再次出现时，则找到了子数组。

和java联系起来，使用键值对map记录，key记录【前缀和%k】,value记录前缀和的当前索引i【也可以理解为子数组的起始下标（j)-1】；即map.put(【前缀和%k】,i);

拓展题目：打印出其中某一子数组的起始下标和结束下标：起始下标j：map.get(【前缀和%k】) + 1，为什么是加1呢？看这里：nums[i] - nums[j - 1] % k = 0;因为这里是j - 1；结束下标：i;

如果需要求最短的连续子数组的长度，大家应该知道怎么做了吧。

回到题目：

pre代表i - 1索引前缀和 reminder余数 remainder = pre % k; 为i - 1的前缀和对k取余;

那么i索引处的reminder计算为： remainder = (pre + nums[i]) % k = pre % k + nums[i] % k = remainder + nums[i] % k

疑难点：为什么map.put(0, -1)呢 这个主要是记录刚好j为0的情形，如果j为0，那么map.value是否需要记录一个j - 1呢，自然是需要的，key自然为0咯，如【1，2，3】 6，子数组为【1，2，3】

注意：【5，0，0，0】3，这个题之所以中间两位【0，0】是满足条件的子数组，是因为取余都为2的缘故，不是因为取余为0噢
*/
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int pre = 0;
        int reminder = 0;
        map.put(0, -1);
        for (int i = 0; i < len; i++) {
            pre += nums[i];
            reminder = pre % k;
            if(map.containsKey(reminder)) {
                if (i - map.get(reminder) > 1) {
                    return true;
                }
            } else {
                map.put(reminder, i);
            }
        }
        return false;
    }
}
