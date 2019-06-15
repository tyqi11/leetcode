/*
# Boyer-Moore Voting Algorithm

1. Boyer-Moore Voting Algorithm is the easiest way to implement, but hardest way
to understand. To know more about the algorithm, check Wikipedia. Once you know
the algorithm, it is very easy to implement.

Think about it this way: M appears >n/2 times and makes the counter increase. 
Others(O) appear <n/2 times and makes the counter decrease. O can counterbalance
the appearance of M at first, but finally M wins. So at last, the element that
cannot be counterbalanced, is the majority.


2. There are also other way, but more complicated and time-comsuming or space-
consuming. Like: 
2.1 Using a HashMap to record the appearances of each element 
[time complexity: O(n), space conplexity: O(n)];
2.2 Divide and Conquer using binary search, find the majority element in each 
half [time complexity: O(nlogn), space complexity: O(logn) for the stack];
2.3 Sort and return the middle element [time complexity: O(nlogn) for sort, 
space complexity: O(1) if we are allowed to do in-place sort, and O(n) if we 
need to copy the array and sort].

*/

class Solution {
    public int majorityElement(int[] nums) {
        int maj = nums[0];
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (counter == 0) {
                maj = nums[i];
            }
            counter += (nums[i] == maj) ? 1 : -1;
        }
        return maj;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/