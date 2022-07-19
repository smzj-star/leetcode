/**
给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。

 

示例 1：

输入：intervals = [[0,30],[5,10],[15,20]]
输出：2
示例 2：

输入：intervals = [[7,10],[2,4]]
输出：1
 

提示：

1 <= intervals.length <= 104
0 <= starti < endi <= 106

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/meeting-rooms-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int count = 1;
        // 将二维数组排序
        Arrays.sort(intervals, (e1, e2) -> e1[0] == e2[0] ? (e1[1] - e2[1]) : (e1[0] - e2[0]));
        int row = intervals.length;
        int signal = intervals[0][1];
        List<Integer> list = new ArrayList<>();
        list.add(signal);
        for (int i = 1; i < row; i++) {
            Collections.sort(list);
            if (intervals[i][0] < list.get(0)) {
                count++;
            } else {
                list.remove(0);
            }
            list.add(intervals[i][1]);
        }
        return count;
    }
}
