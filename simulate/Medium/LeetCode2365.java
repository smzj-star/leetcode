class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        long days = 0L;
        int len = tasks.length;
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            days++;
            // 如果需要休息
            // 1L默认值是为了兼容第一个任务以及没有做过的任务
            if (days < map.getOrDefault(tasks[i], 1L)) {
                // 那么跳过休息
                days = map.get(tasks[i]);
            }
            map.put(tasks[i], days + space + 1);
        }
        return days;
    }
}
