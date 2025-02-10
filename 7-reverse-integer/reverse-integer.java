/**
    This is similar to reversing a string. Just keep removing the last integer from the right and form the new integer from the left. We just have 2 special cases for overflow
    1. > Maximum value:  Integer.MAX\_VALUE = 2^3 - 1 = 2147483647 
	2. < Minimum value:  Integer.MIN\_VALUE = -2^31 = -2147483648 

    Why Compare reversed to Integer.MAX_VALUE / 10?

        The value Integer.MAX_VALUE / 10 = 214748364 represents the maximum allowable value for reversed before appending 
        another digit.
            •	If reversed > 214748364, multiplying by 10 and adding any digit will overflow.
            •	If reversed == 214748364, appending a digit larger than 7 (the last digit of 214748364__7) will overflow.

        Thus, we ensure:
            •	If reversed == Integer.MAX_VALUE / 10, the next digit must be ≤ 7.
    
    Why Compare reversed to Integer.MIN_VALUE / 10?

    Similarly, Integer.MIN_VALUE / 10 = -214748364 represents the minimum allowable value for reversed.
        •	If reversed < -214748364, multiplying by 10 and adding any digit will overflow.
        •	If reversed == -214748364, appending a digit smaller than -8 (the last digit of -214748364__8) will overflow.

    Thus, we ensure:
        •	If reversed == Integer.MIN_VALUE / 10, the next digit must be ≥ -8.
 */

class Solution {
    public int reverse(int x) {
        int reverse = 0;

        while(x != 0) {
            int popDigit = x%10;    // Get the rightmost digit
            x /= 10;                // Shrink x from the right

            // Check for overflow BEFORE we modify the reverse integer
            if((reverse > Integer.MAX_VALUE / 10) || ((reverse == Integer.MAX_VALUE) && (popDigit > 7)))
                return 0;   // Positive overflow
            
            if((reverse < Integer.MIN_VALUE / 10) || ((reverse == Integer.MIN_VALUE) &&(popDigit < -8)))
                return 0;   // Negative overflow
            
            reverse = reverse * 10 + popDigit;

        }

        return reverse;
    }
}