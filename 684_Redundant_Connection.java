/*
# Union Find

1. A tree is a group of connected components. All components share a same root. 
Once we add a new element, its root is itself. After it is added, its root becomes
that same root. If we connect two existing elements, these two elements share 
a same root. Then we know this edge is redundant.

2. Length of parent array. A tree with N nodes has N - 1 edges. With 1 redundant,
there will be N edges (same as edges.length). But nodes are number from 1 while 
places in the array are numbered from 0. To avoid +1/-1, we give N + 1 places in
parent array and leave the parent[0] never used.

*/

class Solution {
    int[] parent;
    
    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length + 1]; 
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            if (find(u) == find(v)) {
                return edge;
            } else {
                parent[find(u)] = find(v);
            }
        }
        
        return new int[2];        
    }
    
    private int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        
        return parent[p];
    }
}


/*
Time complexity: O()
Space complexity: O()
*/