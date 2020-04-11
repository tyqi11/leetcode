/*
# Two pointers
The key is to find that the string without 'X' should be the same. 
And the "R"s in start are earlier and "L"s in start are later.
*/

class Solution {
    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) {
            return false;
        }
        if (start.equals(end)) {
            return true;
        }
        
        // the relative position of R and L will never be changed
        if (!start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }
        
        int i = 0, j = 0;
        while (i < start.length() && j < end.length()) {
            while (i < start.length() && start.charAt(i) == 'X') {
                i++;
            }
            
            while (j < end.length() && end.charAt(j) == 'X') {
                j++;
            }
            
            if (i == start.length() && j == end.length()) {
                return true;
            } else if (i == start.length() || j == end.length()) {
                return false;
            }

            // start.charAt(i) == end.charAt(j) always true
            // only "XL" to "LX", so i >= j
            if (start.charAt(i) == 'L' && i < j) {
                return false;
            }
            // only "RX" to "XR", so i <= j
            if (start.charAt(i) == 'R' && i > j) {
                return false;
            }
            
            i++;
            j++;
        }
        return true;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/