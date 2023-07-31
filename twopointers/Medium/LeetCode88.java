class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);
        int pCopy1 = 0;
        int p1 = 0;
        int p2 = 0;
        while (pCopy1 < m && p2 < n) {
            nums1[p1++] = nums1Copy[pCopy1] > nums2[p2] ? nums2[p2++] : nums1Copy[pCopy1++]; 
        }
        if (pCopy1 < m) {
            System.arraycopy(nums1Copy, pCopy1, nums1, p1, m + n - p1);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1, m + n - p1);
        }
    }
}
