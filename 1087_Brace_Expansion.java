/*

*/

class Solution {
    public String[] expand(String S) {
        List<List<Character>> list = new ArrayList<>();
        List<Character> curList = new ArrayList<>();
        
        int i = 0; // point to the current char
        while (i < S.length()) {
            curList = new ArrayList<>();
            if (S.charAt(i) == '{') {
                for (int j = i + 1; j < S.length(); j++) {
                    if (S.charAt(j) == ',') {
                        continue;
                    } else if (S.charAt(j) == '}') {
                        i = j + 1;
                        break;
                    } else {
                        curList.add(S.charAt(j));
                    }
                }
            } else { // S.charAt(i) is a letter
                curList.add(S.charAt(i++));
            }
            Collections.sort(curList);
            list.add(curList);
        } // [[a,b], [c], [d, e], [f]]
        
        Queue<String> q = new LinkedList<>();
        q.offer("");
        for (int pos = 0; pos < list.size(); pos++) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                String cur = q.poll();
                for (Character s : list.get(pos)) {
                    String newStr = cur + s;
                    q.offer(newStr);
                }
            }
        }
        
        String[] res = new String[q.size()];
        int idx = 0;
        while (idx < res.length) {
            res[idx++] = q.poll().toString();
        }
        
        return res;
    }
}

/*
Time complexity: O(n)
Space complexity: O()
*/

/*
Question: why can't I do:
	String[] res = new String[q.size()];
	list.toArray(res);
As in: https://www.tutorialspoint.com/Java-program-to-convert-a-list-to-an-array
public class ListToArray {
   public static void main(String args[]){
      ArrayList<String> list = new ArrayList<String>();
      list.add("Apple");
      list.add("Orange");
      list.add("Banana");

      System.out.println("Contents of list ::"+list);
      String[] myArray = new String[list.size()];
      list.toArray(myArray);

      for(int i=0; i<myArray.length; i++){
         System.out.println("Element at the index "+i+" is ::"+myArray[i]);
      }
   }
}

*/