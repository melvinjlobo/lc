/**
    Solution 1:  Manacher's Algorithm
        The idea is to use the property of a palindrome. Palindromes can be expanded from the center (from one character for odd palindromes and between 2 characters for even palindromes). This of it like concentric circles going outward. So for this algorithm we expand around the center

    A palindrome can have:
        1.	Odd length (centered around a single character).
        2.	Even length (centered around a pair of characters).
        •	For each character or pair of characters as the center, expand outward while the characters match. Track the longest palindrome found.

    

    Solution 2: DP (TBD when I get to DP)

 */


class Solution {
    public String longestPalindrome(String s) {
        int start = 0, end = 0;     // pointers to keep track of the start and end of the longest palindrome

        for(int i = 0; i < s.length(); i++) {
            int oddLength = expandAroundCenter(s, i, i);       // Start at the same point for odd
            int evenLength = expandAroundCenter(s, i, i+1);      // Two consecutive characters for even since the start point is between them
            int maxPalindromeLength = Math.max(oddLength, evenLength);      // Get the max of the two

            // Now calculate how far we need to move the start and end pointers to capture the boundaries of the max palindrome. 
            if(maxPalindromeLength > end - start) { // length found > previous max length (start - end)
                start = i - (maxPalindromeLength - 1) / 2; // get the half of the length from `i` to the left
                end = i + maxPalindromeLength / 2;      // Mode end to the half of maxPalindromeLength from `i`
            }
        }

        return s.substring(start, end + 1);     // + 1 for how java substring works;

    }

    /**
        This function will just expand around the center of the source string based on where the initial `left` or `right` are.
        It returns the length of the palindrome that it found (or not found) based on the starting points `left` and `right`
     */
    public int expandAroundCenter(String s, int left, int right) {
        while((left >= 0) && (right < s.length()) && (s.charAt(left) == s.charAt(right))) {
            left--;
            right++;
        }

        return right - left - 1; 
    }
}