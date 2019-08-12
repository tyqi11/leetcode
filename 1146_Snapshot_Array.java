/*

treemap.floorEntry(K key) 
Returns a key-value mapping associated with the greatest key less than or equal 
to the given key, or null if there is no such key.

array[index]: <0, 0>                            <3, 5>
        snap: 0         1           2           3           4   

floorEntry(2): <0, 0>
floorEntry(3): <3, 5>

*/

class SnapshotArray {
    
    TreeMap<Integer, Integer>[] A;
    int snap = 0;
    
    public SnapshotArray(int length) {
        A = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            A[i] = new TreeMap<>();
            A[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        A[index].put(snap, val);
    }

    public int snap() {
        return snap++;
    }

    public int get(int index, int snap_id) {
        return A[index].floorEntry(snap_id).getValue();
    }
}

/*
Time complexity: O()
Space complexity: O()
*/