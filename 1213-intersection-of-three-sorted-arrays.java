/*
make the first two the same, then compare with the third.
*/


class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new LinkedList<>();
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                if (arr1[i] < arr3[k]) {
                    i++;
                    j++;
                } else if (arr1[i] > arr3[k]) {
                    k++;
                } else {
                    res.add(arr1[i]);
                    i++;
                    j++;
                    k++;
                }
            }
        }
        
        return res;
    }
}

/*
Time complexity: O(n), n is the length of the shortest array
Space complexity: O(1)
*/