class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            }
        });
        int arrow = points[0][1];
        int count = 1;
        for (int[] p : points) {
            if (arrow >= p[0] && arrow <= p[1]) {
                continue;
            } else {
                arrow = p[1];
                count++;
            }
        }
        return count;            
    }
}