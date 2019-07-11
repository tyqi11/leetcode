/*
# Partition(Quick Sort)
# Heap

1. The first one come to mind is to use a PriorityQueue. We can use a minHeap of size
k to always keep k elements. Each time a new element comes, we offer it into the heap,
and when the size > k, we poll the top one, which is currently the smallest of all.
After iterating all the elements, the k largest are in the heap, and the top one is
the Kth largest.

2. An advanced way to solve the problem is to partition the array as in Quick Sort.
Compare the separate "middle" index with the target to decide which part we need to
search in the next round. This takes only O(1) space, but the time complexity depends
on the array. It might be O(n) if we finish in one found or O(n^2) if every partion
can only discard one element from the pool.

3. How to improve the solution to make it a probabilistic guaranteed O(n)? The answer
is to randomize the input to make the worst case a 1/N possibility. The code is 
shown in a block.



*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}

/*
Time complexity: O(nlogk), each offer takes O(logk) time, and there are n elements
Space complexity: O(k), for the heap
*/

/********************************************************/
// Solution 2: Quick Sort
class Solution {
    public int findKthLargest(int[] nums, int k) {
    	// shuffle(nums); ////////////////////
        k = nums.length - k; // target index in array
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = partition(nums, left, right);
            if (mid == k) {
                return nums[mid];
            } else if (mid < k) {
                left = mid + 1;
            } else { // mid > k
                right = mid - 1;
            }
        }
        return nums[left];        
    }
    
    private int partition(int[] nums, int left, int right) {
        int i = left; // left of i, excluding, < pivot
        int j = right - 1; // right of j, excluding, >= pivot
        int pivot = nums[right];
        while (i <= j) {
            if (nums[j] >= pivot) {
                j--;
            } else {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    ///////////////////////////////////////////////
    private void shuffle(int[] nums) {           //
    	Random random = new Random();            //
    	for (int i = 0; i < nums.length; i++) {  //
    		int j = random.nextInt(i + 1);       //
    		swap(nums, i, j);                    //
    	}                                        //
    }                                            //
    ///////////////////////////////////////////////
}

/*
Time complexity: O(n), best case, O(n^2) worst case
Space complexity: O(1)
*/