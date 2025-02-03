/**
	1.	Handle Edge Cases:
        •	If divisor is 0, return Integer.MAX_VALUE (undefined behavior).
        •	If dividend = -2³¹ and divisor = -1, return 2³¹ - 1 (to prevent overflow).
        •	If divisor = 1, return dividend as is.
	2.	Determine the Sign:
        •	If dividend and divisor have opposite signs, the result is negative.
        •	Convert both to absolute values for easier calculations.
	3.	Bitwise Division (Repeated Doubling)
        •	Keep subtracting divisor * 2^x from dividend until dividend is less than divisor.
        •	Use bitwise shifts (<<) to efficiently find divisor * 2^x.
        •	Add 2^x to the quotient each time.
	4.	Return the Quotient
        •	If the result is negative, return -quotient.
        •	If positive, return quotient.

    NOTE: 
        5 << 1 is equivalent to 5 * 2^1 = 5*2 = 10
        In Binary: 00000101 << 1 = 00001010 (which is 10)
        Doing it again is 10 << 1 = 10* 2^1 (or 5 * 2^2) -> 10*2 (OR 5*4) = 20

        The idea is to double the divisor by power of two everytime until it is larger than the divisor. Then we pause and subtract the number that `almost fits` in the dividend and we run the same process on the remainder again.

    Example:
    Example: divide(43, 8)
	•	Normally, 43 / 8 = 5.
	•	Instead of subtracting 8 one by one, we:
	•	Find the largest multiple of 8 (power of 2) within 43.
	•	We double 8 using << until it’s too large.

    Step-by-Step Process
        1.	Start with temp = divisor = 8, multiple = 1
        2.	Double until temp is too big for dividend = 43
            temp = 8   (8 × 1)                  → multiple = 1
            temp = 16  (8 × 2)   ← temp <<= 1   → multiple = 2            
            temp = 32  (8 × 4)   ← temp <<= 1 (biggest valid)   → multiple = 4
            temp = 64  (too large, stop)
        3. Subtract temp = 32 from 43
            43 - 32 = 11
        4. Repeat for 11
            temp = 8  (8 × 1)    ← temp <<= 1 would be 16 (too large, stop) → multiple = 1
            11 - 8 = 3 (remaining)
        5. Final Quotient = (largest multiple of step 2) + (largest multiple of step 4) -> 4 + 1 = 5

 */


class Solution {
    public int divide(int dividend, int divisor) {

        //Edge case: Divide by zero
        if(divisor == 0)
            return Integer.MAX_VALUE;

        // Edge case: Overflow
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        
        // Edge case: Divide by 1 (number itself)
        if(divisor == 1)
            return dividend;
        
        // Convert to long to avoid overflow and get absolute values
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        // Check for negative values
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // result
        long result = 0;

        while( a >= b) {
            long temp = b, multiple = 1;
            while((temp << 1 ) < a) {
                temp <<= 1;
                multiple <<= 1;
            }

            a -= temp;
            result += multiple;
        }

        return (!negative) ? (int) result : (int)-result;
    }
}