class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>((o1, o2) -> 
                o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int serversLen = servers.length;
        for (int i = 0; i < serversLen; i++) {
            free.offer(new int[]{servers[i], i});
        }
        int time = 0;
        int tasksLen = tasks.length;
        int[] result = new int[tasksLen];
        for (int i = 0; i < tasksLen; i++) {
            time = Math.max(i, time);
            while (!busy.isEmpty() && busy.peek()[1] <= time) {
                int[] cur = busy.poll();
                free.offer(new int[]{servers[cur[0]], cur[0]});
            }
            if (free.isEmpty()) {
                time = busy.peek()[1];
                while (!busy.isEmpty() && busy.peek()[1] <= time) {
                    int[] cur = busy.poll();
                    free.offer(new int[]{servers[cur[0]], cur[0]});
                }
            }
            int[] curFree = free.poll();
            result[i] = curFree[1];
            busy.offer(new int[]{curFree[1], time + tasks[i]});
        }
        return result;
    }
}
