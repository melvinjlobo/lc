class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int closeCount = 0;
        char[] src = s.toCharArray();
        for(int i = 0; i < src.length; i++) {
            char c = src[i];

            if( c == '(') {
                openCount++;
            } else {
                if(openCount > 0)
                    openCount--;
                else
                    closeCount ++;
            }

        }

        return openCount + closeCount;
    }
}