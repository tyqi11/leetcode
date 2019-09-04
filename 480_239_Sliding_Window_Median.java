/*

1. This looks so similar to 295. Find Median from Data Stream. So the first
method we think of is similar to that. See Solution 1. However, we implemented
pq.remove() method which takes O(k) time complexity.

*/

// Solution 1: two PriorityQueues
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int idx = 0;
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for(int i = 0; i < nums.length; i++) {
            if(left.size() < right.size()) { // left size + 1
                right.add(nums[i]);
                left.add(right.remove());
            } else { // right size + 1
                left.add(nums[i]);
                right.add(left.remove());
            }


            if(left.size() + right.size() == k) {
                if(left.size() == right.size()) {
                    result[idx] = 0.5 * left.peek() + 0.5 * right.peek();
                } else {
                    result[idx] = right.peek();
                }
                
                if(!right.remove(nums[idx])) {
                    left.remove(nums[idx]);
                }
                idx++;
            }
        }
        return result;
    }
}

/*
Time complexity: O(nk), O(k) for pq.remove()
Space complexity: O(k)
*/

/***************************************************************/
// Solution 2: TreeSet
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int idx = 0;
        TreeSet<Integer> left = getSet(nums);
        TreeSet<Integer> right = getSet(nums);
        
        for(int i = 0; i < nums.length; i++) {
        	// left.size() == right.size() or left.size() == right.size() + 1
        	// when equal, add to left
        	// when odd, res = nums[left.last()]
            if(left.size() <= right.size()) {
                right.add(i);
                int m = right.first();
                right.remove(m);
                left.add(m);
            } else {
                left.add(i);
                int m = left.last();
                left.remove(m);
                right.add(m);
            }

            // another way
            // right.size() == left.size() or right.size() == left.size() + 1
        	// when equal, add to right
        	// when odd, res = nums[right.first()]
        	// also use pollLast()/pollFirst() to be more concise
        	/*
			if(left.size() < right.size()) {
                right.add(i);
                Integer m = right.pollFirst();
                left.add(m);
            } else {
                left.add(i);
                Integer m = left.pollLast();
                right.add(m);
            }
        	*/

            if(left.size() + right.size() == k) {
                if(left.size() == right.size()) {
                    result[idx] = 0.5 * nums[left.last()] + 0.5 * nums[right.first()];
                    			// avoid 0.5 * (nums[] + nums[]) to avoid overflow
                } else {
                    result[idx] = nums[left.last()];
                }                    

                if(!left.remove(idx)) {
                    right.remove(idx);
                }
                    
                idx++;
            }
        }
        return result;
}

    private static TreeSet<Integer> getSet(int[] nums) {
        return new TreeSet<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return nums[a] == nums[b] ? a - b : nums[a] < nums[b] ? -1 : 1;
            }
        });
    }
}

/*
Time complexity: O(nlogk), O(logk) for add() and remove() in a TreeSet
Space complexity: O(k)
*/

