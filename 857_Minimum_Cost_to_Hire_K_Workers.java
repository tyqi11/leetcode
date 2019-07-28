/*
# PriorityQueue (Best First Search)

0. According to the rules, the wage of a worker in the group depends on 1) his 
work quality, 2) the highest wage/quality ratio in the group. 

1. So we calculate all the workers' wage/quality ratio, put them in an array and 
sort according to the ratio (works like a minHeap, with smaller ratio comes first).

2. For each k workers, we put them in another PriorityQueue (maxHeap, with highest
quality on top). We calculate the sum of workers' work quality and the money
needed is sumOfQuality * highestRatioInGroup. 

3. With one worker with higher ratio into PriorityQueue, one worker with highest
quality go out of the queue. We keep updating the result until all the workers go 
into the queue. 


*/

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double[][] workers = new double[quality.length][2]; // [ratio, quality]
        for (int i = 0; i < quality.length; i++) {
            workers[i][0] = (double) wage[i] / quality[i];
            workers[i][1] = quality[i];
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a)); // maxHeap, largest quality on top
        double res = Double.MAX_VALUE;
        double quaSum = 0;
        for (double[] worker : workers) {
            pq.offer((int) worker[1]);
            quaSum += worker[1];
            if (pq.size() > K) {
                quaSum -= pq.poll();
            }
            if (pq.size() == K) {
                res = Math.min(res, quaSum * worker[0]);
            }
        }
        return res;
    }
}

/*
Time complexity: O(nlogn + nlogk), for Arrays.sort() and n times of pq.offer()
Space complexity: O(n + k), for workers array and pq
*/