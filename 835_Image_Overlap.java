/*
The key is how to mark the translation. The solution here is quite smart.
@lee215
https://leetcode.com/problems/image-overlap/discuss/130623/C%2B%2BJavaPython-Straight-Forward

For each one single way to translate, all the corresponding coordinates have the 
save difference in x and y. That is, for one translation, all the coorditates 
[x, y] in A can be changed to coordiates [x', y'] in B, using x' = x + delta_x, 
y' = y + delta_y.

So coorditates with the same delta_x and delta_y are from the same way of translation.

So, we first code the positions of all the 1s in A and B. Then, for each pair 
of one point from A and one point from B, we add the pair to the count of its translation. 
(represented using the code difference). Finally, we count which translation gets most
pairs.

*/

class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int n = A.length;
        int bias = 100; // bigger than (2n-1) is enough, here n <= 30
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();
        
        // iterate over all squares, and save 1s in the lists
        for (int i = 0; i < n * n; i++) { // O(n^2)
            int x = i / n, y = i % n;
            if (A[x][y] == 1) {
                p1.add(x * bias + y);
            }
            if (B[x][y] == 1) {
                p2.add(x * bias + y);
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : p1) {        // O(ab), a, b are the numbers of 1s in A and B
            for (int b : p2) {
                int translate = a - b;
                map.put(translate, map.getOrDefault(translate, 0) + 1);
            }
        }
        
        int res = 0; 
        for (int i : map.values()) { // value set, size < a * b
            res = Math.max(res, i);
        }
        
        return res;
    }
}



/*
Time complexity: O(n^2 + ab), worst case O(n^4), if A and B are full of 1s.
Space complexity: O(a + b), worst case O(n^2)
*/
