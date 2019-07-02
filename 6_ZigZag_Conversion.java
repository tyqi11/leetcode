/*

1. There are two ways to think about solving this problem: sort by rows and sort
by columns. Sorting by rows is easier to implement as we can get the result directly.
We call it a cycle between two characters at row 1. There are *size* of characters
in one cycle. For chars on the first and last row, we append one in each cycle.
For rows in between, we append one additional char, whose index is j + size - 2 * i.

2. We use a StringBuilder to get a resizable string.

*/

class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows < 1) {
            return "";
        } else if (numRows == 1 || numRows > s.length()) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        int size = 2 * (numRows - 1);
        
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length(); j += size) {
                res.append(s.charAt(j));
                int temp = j + size - 2 * i;
                if (i != 0 &&i != numRows - 1 && temp < s.length()) {
                    res.append(s.charAt(temp));
                }
            }
        }
        
        return res.toString();        
    }
}

/*
Time complexity: O(n)
Space complexity： O(n)
*/