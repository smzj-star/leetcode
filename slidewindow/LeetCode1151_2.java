/**
执行用时：2 ms, 在所有 Java 提交中击败了97.11%的用户
内存消耗：50.7 MB, 在所有 Java 提交中击败了81.35%的用户
通过测试用例：36 / 36
*/
class Solution {
    public int minSwaps(int[] data) {
        int len = data.length;
        int oneCount = 0;
        for (int num : data) {
            oneCount += num;
        }
        if (oneCount == 1) {
            return 0;
        }
        int temp = 0;
        for (int i = 0; i < oneCount; i++) {
            temp += data[i];
        }
        int zeroCount = oneCount - temp;
        int result = zeroCount;
        for (int i = oneCount; i < len; i++) {
            zeroCount += data[i - oneCount] - data[i];
            result = Math.min(result, zeroCount);
        }
        return result;
    }
}
