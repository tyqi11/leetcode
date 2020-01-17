/*

*/

class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E);
        int right = Math.min(C, G);       
        int up = Math.min(D, H);
        int down = Math.max(B, F);
        
        int overlap = 0;
        if (left < right && up > down) {
            overlap = (right - left) * (up - down);
        }
        
        return (G - E) * (H - F) + (C - A) * (D - B) - overlap;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/

