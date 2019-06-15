/*

1. Comparting to 118, there are two changes. First is we only need to give the last row 
as result. Second, numRows is changed to rowIndex, which starts from 0. So we do not 
need the corner case anymore.

2. When we iteratively change the answers in res, we find that from left to right, the
previous calculation will erase the answer from last row and result in wrong answer. We
can either use a temp variable to store the answer from last row (the commented code), 
or do the iteration from right to left, which is better.

3. Do not forget to add a new 1 at the end of each row.


*/

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            // int temp = 1;
            // for (int j = 1; j < i; j++) {
            //     int cur = temp + res.get(j);
            //     temp = res.get(j);
            //     res.set(j, cur);
            // }
            for (int j = i - 1; j >= 1; j--) {
                int cur = res.get(j) + res.get(j - 1);
                res.set(j, cur);
            }
            res.add(1); // add a new 1 after each row
        }
        return res;        
    }
}

/*
Time complexity: O(n^2), n = rowIndex, 1+2+3+...+(n+1) = (n+2)(n+1)/2 -> O(n^2)
Space complexity: O(n)
*/