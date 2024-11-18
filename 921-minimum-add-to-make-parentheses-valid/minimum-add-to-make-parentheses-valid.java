/**

To solve this problem:

1. Use a counter to track unbalanced parentheses:
    A.Open Count (openCount): Keeps track of unmatched opening parentheses '('.
    B. Close Count (closeCount): Keeps track of unmatched closing parentheses ')'.
2. Traverse the string:
    A. If you encounter '(', increment openCount.
    B. If you encounter ')':
        I. If openCount > 0, it means there's a matching opening parenthesis, so 
        decrement openCount.
        II. Otherwise, increment closeCount because this ')' is unmatched.
3. At the end of the traversal:
    openCount will represent unmatched '('.
    closeCount will represent unmatched ')'.
    Hence, The result is openCount + closeCount.
 */

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