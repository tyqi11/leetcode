/*

1. It takes two steps to solve the problem, get positions by traversing all nodes and
return them in the required order.

2. Traversing the nodes are easy using DFS. 

3. To sort the nodes, there are two ways. The first is shown in solution 1 using a 
PriorityQueue, so that when we reach a node, we store it in order. The second is in
solution 2, we do Collections.sort() after all the nodes are reached.

*/

// Solution 1: using PriorityQueue
class Solution {
    class Point {
        int x, y, val;
        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Point> pq = new PriorityQueue(new Comparator<Point>() {
            @Override /////////////////////////////////
            public int compare(Point o1, Point o2) { //
                if (o1.x != o2.x) {                  //
                    return o1.x - o2.x;              //
                } else if (o1.y != o2.y) {           //
                    return o2.y - o1.y;              //
                } else {                             //
                    return o1.val - o2.val;          //
                }                                    //
            } /////////////////////////////////////////
        });
        dfs(root, 0, 0, pq);
        int preX = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            Point cur = pq.poll(); // pop up with cur.x in ascending order
            if (cur.x == preX) {
                List<Integer> list = res.get(res.size() - 1);
                list.add(cur.val);
            } else { // cur.x > preX
                List<Integer> list = new ArrayList<>();
                list.add(cur.val);
                res.add(list);
            }
            preX = cur.x;
        }
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y, Queue<Point> pq) {
        if (root == null) {
            return;
        }
        pq.offer(new Point(x, y, root.val)); ////////////
        dfs(root.left, x - 1, y - 1, pq);
        dfs(root.right, x + 1, y - 1, pq);
    }
}

/*
Time complexity: O(n + nlogn), n for traversing
                               log1 + log2 + ... + logn for adding in PriorityQueue
Space complexity: O(n + h), n for PriorityQueue and h for call stack.
*/

/****************************************************************/
// Solution 2: Collections.sort()
class Solution {
    class Point implements Comparable<Point>{
        int x, y, val;
        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        //////////////////////////////////////
        @Override                           //
        public int compareTo(Point that) {  //
            if (this.x != that.x) {         //
                return this.x - that.x;     //
            } else if (this.y != that.y) {  //
                return that.y - this.y;     //
            } else {                        //
                return this.val - that.val; //
            }                               //
        }/////////////////////////////////////
    }
    
    List<Point> points;
        
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        points = new ArrayList<>();
        dfs(root, 0, 0);
        Collections.sort(points); //////////////
        int preX = Integer.MIN_VALUE;
        for (Point p : points) {
            if (p.x == preX) {
                List<Integer> list = res.get(res.size() - 1);
                list.add(p.val);
            } else { // p.x > preX
                List<Integer> list = new ArrayList<>();
                list.add(p.val);
                res.add(list);
            }
            preX = p.x;
        }
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y) {
        if (root == null) {
            return;
        }
        points.add(new Point(x, y, root.val)); /////////////
        dfs(root.left, x - 1, y - 1);
        dfs(root.right, x + 1, y - 1);
    }
}