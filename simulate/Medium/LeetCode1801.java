class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buyPq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        PriorityQueue<int[]> sellPq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int[] order : orders) {
            int orderType = order[2];
            int price = order[0];
            int amount = order[1];
            if (orderType == 0) {
                dealBuyOrder(buyPq, sellPq, price, amount);
            } else {
                dealSellOrder(buyPq, sellPq, price, amount);
            }
        }
        double count = 0.0;
        while (!sellPq.isEmpty()) {
            count += sellPq.poll()[1];
        }
        while (!buyPq.isEmpty()) {
            count += buyPq.poll()[1];
        }
        return (int) (count % 1000000007);
    }

    private void dealSellOrder(PriorityQueue<int[]> buyPq, PriorityQueue<int[]> sellPq, int price, int amount) {
        while (!buyPq.isEmpty() && amount > 0) {
            int[] buyHigh = buyPq.peek();
            if (buyHigh[0] >= price) {
                if (amount >= buyHigh[1]) {
                    amount -= buyHigh[1];
                    buyPq.poll();
                } else {
                    buyHigh[1] -= amount;
                    amount = 0;
                }
            } else {
                break;
            }
        }
        if (amount > 0) {
            sellPq.offer(new int[]{price, amount});
        }
    }

    private void dealBuyOrder(PriorityQueue<int[]> buyPq, PriorityQueue<int[]> sellPq, int price, int amount) {
        while (!sellPq.isEmpty() && amount > 0) {
            int[] sellLow = sellPq.peek();
            if (sellLow[0] <= price) {
                if (amount >= sellLow[1]) {
                    amount -= sellLow[1];
                    sellPq.poll();
                } else {
                    sellLow[1] -= amount;
                    amount = 0;
                }
            } else {
                break;
            }
        }
        if (amount > 0) {
            buyPq.offer(new int[]{price, amount});
        }
    }
}
