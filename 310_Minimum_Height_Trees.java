/*
# BFS (similar to topological sort)

0. A great summary of tree in graph theory by @dietpepsi: 
https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts

1. It is not easy to find the root, while it is easy to find the leaves: nodes with
an indegree: 1. So it is like a topological sort, changing indegree = 0 to indegree = 1.

2. First, we make an adjacency list of a node and all the nodes directly connect to it.
When iterating the edges, be careful that we add twice to two nodes with one edge.

3. Then if the nodes' indegree = 1, we put it in a leaves list. Iterating over all
nodes connect to the current node, decrease their indegree by 1 by remove current node
from their "connect node set". This step is like cutting all the leaf nodes. Now, if
new leaf nodes appears, we repeat previous steps.

4. When to end the loop? When we remove nodes until there are only two or one we have
not removed yet. This time, we have Minimum Height Tree roots in the list.

5. We mostly use a Queue to implement such algorithm but as we need to return a List
here, we use a List here, add at tail and remove at head. We also use set.size() to
avoid an extra indegree array.

*/

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) { // size = indegree
                leaves.add(i);
            }
        }
        
        while (n > 2) {
            int size = leaves.size();
            n -= size;
            for (int i = 0; i < size; i++) {
                int cur = leaves.get(0);
                leaves.remove(0);
                for (int next : graph.get(cur)) {
                    graph.get(next).remove(cur);
                    if (graph.get(next).size() == 1) {
                        leaves.add(next);
                    }
                }
            }
        }
        
        return leaves;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/