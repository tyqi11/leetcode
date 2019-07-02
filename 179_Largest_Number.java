/*

1. This is the reverse order of Radix sort. We sort from the most significant digit.
So 9 > 89 > 77 > 666 > 5999 > ....

2. How to override the comparator in the Arrays.sort() API is the most criticla part.

3. Time complexity of this sorting is no longer O(nlogn) as in each comparision of
two Strings, it takes O(k), k is the average length of strings. So the sorting is
O(knlogn).

*/

class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        } else if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        
        // convert int array to String array
        String[] strs = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        // sort the array, numbers start with bigger digits comes first
        Arrays.sort(strs, new Comparator<String>() {
           @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s2.compareTo(s1); // O(k) to compare each char in String
            }
        }); // O(knlogn)
        
        // a special corner case with only 0's
        if (strs[0].charAt(0) == '0') {
            return "0";
        }
        
        // concatenate the string with StringBuilder
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }
}

/*
Time complexity: O(knlogn), nums.length: n, average length of each String is k
                            sort array: O(nlogn*k), comparing two strings takes O(k) 
                            append to StringBuilder: O(n)
Space complexity: O(n), for the strs array
*/