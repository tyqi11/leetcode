/*
# Brutal force using PriorityQueue
# Bucket sort
*/

class Solution {
    class Pair implements Comparable<Pair> {
        int dist;
        int workerIdx;
        int bikeIdx;
        
        Pair(int d, int w, int b) {
            dist = d;
            workerIdx = w;
            bikeIdx = b;
        }
        
        @Override
        public int compareTo(Pair p2) {            
            if (this.dist != p2.dist) {
                return Integer.compare(this.dist, p2.dist);
            } else if (this.workerIdx != p2.workerIdx) {
                return Integer.compare(this.workerIdx, p2.workerIdx);
            } else {
                return Integer.compare(this.bikeIdx, p2.bikeIdx);
            }            
        }
    }
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                pq.offer(new Pair(dist, i, j));
            }
        } // O(mnlog(mn))
        
        Set<Integer> assignedBikes = new HashSet<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        int assigned = 0;
        while (assigned < n) {
            Pair cur = pq.poll();
            int w = cur.workerIdx, b = cur.bikeIdx;
            if (res[w] == -1 && assignedBikes.add(b)) {
                res[w] = b;
                assigned++;
            }
        } // O(mn)
        
        return res;
    }
}


/*
Time complexity: O(mnlog(mn)), n is number of workers, m is number of bikes
Space complexity: O(mn)
*/

/**************************************************************/
// Solution 2: bucket sort
// 0 <= workers[i][j], bikes[i][j] < 1000, so maximum length differenct is 2000
// 1 <= workers.length <= bikes.length <= 1000, so maximum 10^6 pairs
// that's why we use distance as buckets
// @mazytes: https://leetcode.com/problems/campus-bikes/discuss/308738/C%2B%2B-bucket-sort-O(M*N)-time-and-space-solution

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // the canvas is from [0, 1000] for x and y, so max distance is 2000
        List<int[]>[] buckets = new ArrayList[2001];
         
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if (buckets[dist] == null) {
                    buckets[dist] = new ArrayList<int[]>();
                }
                buckets[dist].add(new int[]{i, j});
            }
        } // for each distance, naturally ordered as workerIdx first, bikeIdx second
        
        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        Set<Integer> assignedBikes = new HashSet<>();
        int assigned = 0;
        
        for (int d = 0; d < buckets.length && assigned < workers.length; d++) {
            if (buckets[d] != null) {
                for (int[] pair : buckets[d]) {
                    int w = pair[0], b = pair[1];
                    if (res[w] == -1 && assignedBikes.add(b)) {
                        res[w] = b;
                        assigned++;
                    }
                }
            }
        }
        
        return res;        
    }
}

/*
Time complexity: O(mn), n is number of workers, m is number of bikes
Space complexity: O(mn)
*/