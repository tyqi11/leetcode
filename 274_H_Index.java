/*
# Bucket sort [O(n), O(n)]
# Sort [O(nlogn, O(1)]

1. (Sort) According to the definition, we naturally think about sorting the citations
array, and compare the number of papers with number of least citations. Take
[0, 1, 3, 5, 6] as an example. First, we have 5 papers >= 0 citations. 
H-index: 0. Next, we have 4 papers >= 1 citations, H-index: 1. Next, 
we have 3 papers >= 3 citations, H-index: 3. Then we have 2 papers 
>= 5 citations, H-index: 2. We find that the biggest is the one when
citation >= papers. Then the H-index is the minimum of two: papers. 

2. (Bucket sort) With an array of n numbers, largest H-index is n. So we create
an array to track how many papers are cited i times. Then, like the previous 
method, we compare the total number of papers and least citation. Take [0, 1, 3, 5, 6]
as an example. The count array is [1, 1, 0, 1, 0, 1, 1]. To get the largest,
we go from right to left. First we have 1 paper with citation >= 6. Next, we have 
2 papers with citation >= 5. Next, we have 2 papers with citation 4. Then we have
3 papers with citation >= 3. Then we have 3 papers with citation >= 2. The 
biggest is the first when papers >= citations. H-index is the mimimum of the two:
citations.

*/

// Solution 1
class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            if (n - i <= citations[i]) {
                return n - i;
            }
        }
        return citations[0];
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(1)
*/

/******************************************************/

// Solution 2
class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] > n) {
                count[n]++;
            } else {
                count[citations[i]]++;
            }
        }
        int h = 0;
        for (int i = n; i >=0; i--) {
            h += count[i];
            if (h >= i) {
                return i;
            }
        }
        return 0;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/