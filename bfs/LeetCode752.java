/**
执行用时：65 ms, 在所有 Java 提交中击败了77.55%的用户
内存消耗：44.4 MB, 在所有 Java 提交中击败了76.95%的用户
通过测试用例：48 / 48
*/
class Solution {
    public int openLock(String[] deadends, String target) {
       if (target.equals("0000")) {
           return 0;
       } 
       Set<String> deadendSets = new HashSet<>();
       for (String deadend : deadends) {
           deadendSets.add(deadend);
       }
       return deadendSets.contains("0000") ? -1 : bfs(deadendSets, target);
    }

    private int bfs(Set<String> deadendSets, String target) {
        Deque<String> deque = new LinkedList<>();
        deque.offer("0000");
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        int step = 0;
        while (!deque.isEmpty()) {
            step++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String cur = deque.poll();
                List<String> nextStatuses = getNextStatus(cur);
                for (String nextStatus : nextStatuses) {
                    if (!visited.contains(nextStatus) && !deadendSets.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        deque.offer(nextStatus);
                        visited.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> getNextStatus(String cur) {
        List<String> nextStatuses = new ArrayList<>();
        char[] array = cur.toCharArray();
        for (int i = 0; i < 4; i++) {
            char changingNum = array[i];
            array[i] = addChangingNum(changingNum);
            nextStatuses.add(new String(array));
            array[i] = minusChangingNum(changingNum);
            nextStatuses.add(new String(array));
            array[i] = changingNum;
        }
        return nextStatuses;
    }

    private char addChangingNum(char changingNum) {
        return changingNum == '9' ? '0' : (char) (changingNum + 1);
    }

    private char minusChangingNum(char changingNum) {
        return changingNum == '0' ? '9' : (char) (changingNum - 1);
    }
}
