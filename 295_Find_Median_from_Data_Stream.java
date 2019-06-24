/*

*/

class MedianFinder {
    private Queue<Integer> small; 
    // a max-heap, whose top is the largest of the smaller half
    private Queue<Integer> large; 
    // a min-heap, whose top is the smallest of the bigger half

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        large.add(num);
        small.add(large.poll()); 
        // add 1 and poll 1 to large, large.size() does not change
        // add 1 to small, small.size() + 1
        if (large.size() < small.size()) {
            large.add(small.poll());
        } // keep large.size() = small.size() / small.size() + 1
    }
    
    public double findMedian() {
        if (large.size() > small.size()) {
            return large.peek();
        } else { // large.size() == small.size()
            return 0.5* (large.peek() + small.peek());
        }
    }
}

/*
Time complexity: O(logn) for every addNum(), O(1) for every findMedian().
Space complexity: O(n)
*/

/************************************************************/
/*
Follow up:
1. If all integer numbers from the stream are between 0 and 100, how would you 
optimize it?

int[] nums = new int[101]; // ???

2. If 99% of all integer numbers from the stream are between 0 and 100, 
how would you optimize it?

HashMap ?

*/