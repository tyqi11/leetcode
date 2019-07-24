/*
# Union Find

1. There are 3 cases when we know that there must be a redundant connection.
We save the potential results and return after we more consideration.
case 1.1				case 1.2 				 case 1.3
		root <——、      	       root              	       root
      /     \    \     	     /     \e1                 e1/      \
     v       v    \last     v       v                   v        v
    a         b    \       a         b <——、 e2     	   a          b
               \    \                 \    \       	    ^          \
                v  /                   v  /              \          v
                 c                      c              e2 `———————— c

2. For each edge, first we check if v has a parent. If it has a parent, it might
be case 1.2 or case 1.3. We may need to remove e1 or e2, but we cannot decide
yet. So we remember these two edges with *e1* and *e2*. And we do not add this edge
to the root now.

3. If v does not have a parent, we set its father as u. And then find the roots
of v and u. If rootV = rootU, there is a circle. It might be 1)last in case 1.1,
2)e2 in case 1.2, 3)e1 or e2 in case 1.3. The newly added edge is highly possible
to be an answer, so we remember it with *last*.

4. Else if rootV != rootU, as the edge goes from u to v. We set the root of 
rootV to be rootU. Thus, root[v] is the same as root[u]. 

5. After iterating over all edges, we can think about the which one to return.
Step 2 tells us that *e1* and *e2* indicates case 1.2 or case 1.3. And remember,
we skipped this *e2* and never put into the tree.
Step 3 tells us that *last* indicates a circle.
5.1 If *e1*, *e2* are null, as there must be one redundant, it must be *last*.
	If they are not null, 5.2 *last* are null. It means that if we ignore the 
							  missing *e2*, the tree is good. So we return *e2*.
					      5.3 *last* not null, it means skipping this edge, we 
					          will still meet a circl.e Then it should be *e1*
					          int case 1.2.

credit to a great Chinese explanation by @rampaging9
https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
*/

class Solution {
    int[] root;
    int[] parent;
        
    public int[] findRedundantDirectedConnection(int[][] edges) {
        root = new int[edges.length + 1];
        parent = new int[edges.length + 1];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
        int[] e1 = null;
        int[] e2 = null;
        int[] last = null;
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            if (parent[v] != 0) {            // case 1.1
                e1 = new int[]{parent[v], v};
                e2 = edge;
            } else {
                parent[v] = u;
                int rootU = find(u);
                int rootV = find(v);
                if (rootU == rootV) {        // case 1.2
                    last = edge;
                } else {
                    root[rootV] = rootU;
                }
            }
        }
        
        if (e1 == null && e2 == null) {
            return last;
        } else {
            if (last == null) {
                return e2;
            } else {
                return e1;
            }
        } 
        
        
    }
    
    private int find(int p) {
        if (p != root[p]) {
            root[p] = find(root[p]);
        }
        
        return root[p];
    }
}

/*
Time complexity: O(E), number of edges
Space complexity: O(E)
*/