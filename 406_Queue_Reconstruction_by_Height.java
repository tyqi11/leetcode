/*

credit to: https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89345/Easy-concept-with-PythonC%2B%2BJava-Solution
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

1. Sort the array.:
1.1 Get the current tallest group, sort by ascending k. For these tallest people, people
before them are people with the same height of them. e.g.
[7, 0], [7, 1]
1.2 For the tallest in the rest, insert them at index k as there are k people before them.
e.g. [7, 0], [6, 1], [7, 1]

2. Follow the same rule, so
   0       1       2       3
[5, 0], [7, 0], [6, 1], [7, 1]
[5, 0], [7, 0], [5, 2], [6, 1], [7, 1]
[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]


*/

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> list = new LinkedList<>();
        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) { // same h, ascending k
                    return Integer.compare(a[1], b[1]); 
                } else {            // decreasing h
                    return Integer.compare(b[0], a[0]);
                }
            }
        }); // decreasing h, ascending k
        
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[list.size()][2]);
    }
}


/*
Time complexity: O()
Space complexity: O()
*/