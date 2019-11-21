class Solution {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return new String[0];
        }
        
        int n = logs.length;
        String[] res = new String[n];
        int letters = 0;
        int digits = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (Character.isDigit(logs[i].charAt(logs[i].length() - 1))) {
                res[digits--] = logs[i];
            } else {
                res[letters++] = logs[i];
            }
        }
        
        Arrays.sort(res, 0, letters, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int space1 = a.indexOf(" ");
                int space2 = b.indexOf(" ");
                String id1 = a.substring(0, space1);
                String id2 = b.substring(0, space2);
                String log1 = a.substring(space1 + 1);
                String log2 = b.substring(space2 + 1);
                if (log1.equals(log2)) {
                    return id1.compareTo(id2);
                } else {
                    return log1.compareTo(log2);
                }
            }
        });
        
        return res;
    }
}