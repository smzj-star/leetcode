class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += Math.abs(arr[i] - x);
        }
        int min = sum;
        int leftIndex = 0;
        int rightIndex = k - 1;
        for (int i = 1; i + k - 1 < len; i++) {
            sum = sum - Math.abs(arr[i - 1] - x) + Math.abs(arr[i + k - 1] - x);
            if (sum < min) {
                min = sum;
                leftIndex = i;
                rightIndex = i + k - 1;
            }
        }
        for (int i = leftIndex; i <= rightIndex; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
