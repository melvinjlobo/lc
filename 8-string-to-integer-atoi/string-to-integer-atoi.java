class Solution {
    public int myAtoi(String s) {
        int length = s.length();
        int result = 0;
        int sign = 1;
        int index = 0;      ///Current pointer to where we are in the grand scheme of things
        int INT_MAX = Integer.MAX_VALUE;
        int INT_MIN = Integer.MIN_VALUE;

        //Get rid of the initial spaces
        while(index < length && s.charAt(index) == ' ') {
            index++;
        }

        //Check for signs
        if(index < length && s.charAt(index) == '+') {
            index++;
        } else if(index < length && s.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Process the digits
        while(index < length && Character.isDigit(s.charAt(index))) {
            int digit = Character.getNumericValue(s.charAt(index));

            // Boundary violations
            /**
            When converting a string to an integer, you’re building up the integer digit-by-digit, so result is multiplied by 10 on each iteration to shift it left by one decimal place. If result is close to the maximum integer value (INT_MAX), then multiplying by 10 and adding another digit might push it over INT_MAX, causing overflow.

        Let’s say result is the number we’ve built up so far, and digit is the next digit we want to add. The goal is to ensure:
        result * 10 + digit <= INT_MAX
        Rearranging this inequality, we get:
        result <= (INT_MAX - digit) / 10
        This condition is now safe to evaluate without risking overflow because we haven’t multiplied result by 10 yet.

        Example Calculation
        Suppose INT_MAX = 2147483647 and digit = 7 (the next digit we want to add):

        We want to ensure result * 10 + digit won’t exceed 2147483647.
        By checking result > (INT_MAX - digit) / 10, we avoid overflow before it happens.
        When Overflow Would Happen?
        If result exceeds (INT_MAX - digit) / 10, then multiplying result by 10 and adding digit would indeed exceed INT_MAX.

        For example:

        Let’s say result = 214748364 and digit = 8.
        Checking: 214748364 > (2147483647 - 8) / 10, which is 214748364 > 214748364, would result in true.
        So the condition triggers, and we know adding this digit would cause overflow, so we return INT_MAX (or INT_MIN if the sign is negative).

             */
             if (result > (INT_MAX - digit) / 10) {
                return sign == 1 ? INT_MAX : INT_MIN;
            }

            result = result * 10 + digit;
            index++;
        }

        return result * sign;


        //NOTE: Cannot use for loop due to various intricate use cases.. So we have to do the while loops and sequential execution as per the rules
        // for(int i = 0; i < length; i++) {
        //     char c = s.charAt(i);
        //     // Check for leading spaces and 0's
        //     if(result == 0 && (c == ' ' || c == '0')) {
        //         continue;
        //     } 
            
        //      if (result == 0 &&c == '-') {                  // Capture the sign
        //         sign = -1;
        //     } else if(!Character.isDigit(c)) {      //If ! digit
        //         break;
        //     } else {
        //         int digit = Character.getNumericValue(c);

        //         if (result > INT_MAX / 10 || 
        //             ( (result == INT_MAX / 10) && (digit > INT_MAX % 10))) {
        //             result = (sign == 1) ? INT_MAX : INT_MIN;
        //             break;
        //         } 

        //         result = result * 10 + digit;

        //     }
        // }

        //return result * sign;
    }
}