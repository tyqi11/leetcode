/*
For a valid square, between all points, there can be only two lengths: edge or diagnol,
So we calculate all lengths, and check if there are two lengths and the number is 4 and 2.
*/
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] distances = new int[]{distance(p1, p2), distance(p1, p3), distance(p1, p4), 
                                    distance(p2, p3), distance(p2, p4), distance(p3, p4)};
        int edge = distances[0];
        int diagonal = distances[0];
        for (int d : distances) {
            if (d != edge) {
                if (d > edge) {
                    diagonal = d;
                    break;
                } else {
                    edge = d;
                    break;
                }
            }
            
        }
        
        int s = 0, l = 0;
        for (int d : distances) {
            if (d == edge) {
                s++;
            } else if (d == diagonal) {
                l++;
            }
        }
        
        return s == 4 && l == 2;
        // return true;
    }
    
    private int distance(int[] p1, int[] p2) {
        int x = Math.abs(p1[0] - p2[0]);
        int y = Math.abs(p1[1] - p2[1]);
        return x * x + y * y;
    }
}



/*
Time complexity: O(6), which is the number of distances to calculate and iterate
Space complexity: O()
*/