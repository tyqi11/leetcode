/*
I was firstly thinking about finding the point p2, which has the same y as p1, and point p3
which has the same x as p1, then to check if there is a p4 exists.

The solution below is smarter, by finding the diagonal points and check if the other two
exists, saving more time and space than my solutoin.
*/
class Solution {
    public int minAreaRect(int[][] points) {
        if (points == null || points.length < 4 || points[0].length != 2) {
            return 0;
        }      
        
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new HashSet<Integer>());
            }
            map.get(p[0]).add(p[1]);
        } //<x, <y's>>
        
        int min = Integer.MAX_VALUE;
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0] == p2[0] || p1[1] == p2[1]) {
                    continue;
                }
                // we want two points as a diagonal
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
            }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}


/*
Time complexity: O(n^2), n is the number of points
Space complexity: O(n)
*/