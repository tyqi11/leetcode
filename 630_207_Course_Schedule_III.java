/*
# PriorityQueue

1. This problem is not similar to Course Schedule I & II at all.

2. We sort the array by courses' end time. So we do not worry about missing
an end day. In the PriorityQueue, the course with latest end time will be on top.

3. For each course, it we can finish it from current time, update the time
and put the course in pq. Else if taking current course enable us to finish
earlier than taking previous course, drop the previous course and add the 
current one. 

*/

class Solution {
    public int scheduleCourse(int[][] courses) {
    	// Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override 
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        }); 
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0; // current time
        for (int[] course : courses) {
            if (time + course[0] <= course[1]) { // can finish this course
                pq.offer(course[0]);
                time += course[0];
            } else if (!pq.isEmpty() && course[0] < pq.peek()) {
                time += course[0] - pq.poll(); // pq.poll() return value
                pq.offer(course[0]);
            }
        }
        return pq.size();
    }
}

/*
Time complexity: O(nlogn + nlogn), n = courses.length,
				 O(nlogn) for the array sort
				 O(nlogn) for iterating over n courses and offer or poll
Space complexity: O(n), for the queue
*/
