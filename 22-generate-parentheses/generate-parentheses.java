/**
    Well-Formed Parentheses Rules
	1.	A string is well-formed if every opening parenthesis ( has a corresponding closing parenthesis ) in the correct order.
	2.	At any point in the string:
	•	The count of ) cannot exceed the count of ( (because it would mean closing a non-existent open parenthesis).

    NOTES:
    1. Keep track of the open and closed paranthesis. We use 2 conditions:
        I. If the open brackets is less than `n`, we keep adding it
        II. If the close brackets are less than the open brackets, we add it to close the gap
    2. Stop when the string has  2n  characters (because  n  pairs mean  2n  parentheses).

    Example, n = 3: Start with an empty string

                                  ""
                   /                        \
                "("                         ")"
             /       \                       (Invalid, skipped)
        "(("         "()"
       /   \        /   \
    "((("  "(()" "(())"  "()(("
   /   \    /   \   /   \    \
"((()))" "(()()" "(())()" "()(())"
                     ...

    TC: The time complexity is O(4^n / √n) because:
 */

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        helper(n, "", 0, 0, result);
        return result;
    }

    private void helper(int n, String partial, int open, int close, List<String> result) {
        // Base case: When the current string is of length 2n
        if(partial.length() == n*2) {
            result.add(partial.toString());
            return;
        }

        // Add '(' if open count is less than max
        if(open < n) {
            partial = partial + "(";
            helper(n, partial , open + 1, close, result);
            partial = partial.substring(0, partial.length() - 1);
        }

        // Add ')' if close count is less than open count
        if(close < open) {
            partial = partial + ")";
            helper(n, partial, open, close + 1, result);
            partial = partial.substring(0, partial.length() - 1);
        }
    }
}