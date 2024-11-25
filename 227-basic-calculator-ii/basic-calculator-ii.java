class Solution {

    private int index; // Class field to track the current position in the string

    public int calculate(String s) {
        index = 0; // Initialize the index at the start of the string
        return calculateHelper(s);
    }

    private int calculateHelper(String s) {
        int currentResult = 0; // Final result
        int lastNumber = 0;    // Tracks the last number for precedence
        char operation = '+';  // Default operation
        int currentNumber = 0; // Current number being processed

        while (index < s.length()) {
            char c = s.charAt(index);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }

            if (c == '(') {
                index++; // Move past the '('
                currentNumber = calculateHelper(s); // Recursively calculate inner expression
            }

            if ((!Character.isDigit(c) && c != ' ') || index == s.length() - 1) {
                // Apply the previous operation
                if (operation == '+') {
                    currentResult += lastNumber;
                    lastNumber = currentNumber;
                } else if (operation == '-') {
                    currentResult += lastNumber;
                    lastNumber = -currentNumber;
                } else if (operation == '*') {
                    lastNumber *= currentNumber;
                } else if (operation == '/') {
                    lastNumber /= currentNumber;
                }

                // Update the operation
                operation = c;
                currentNumber = 0;
            }

            if (c == ')') {
                index++; // Move past the ')'
                break;   // End recursion for parentheses
            }

            index++;
        }

        currentResult += lastNumber;
        return currentResult;

    }

    
    public int calculate1(String s) {
        /**
        1. Remove Spaces:
        Start by removing all spaces from the input string to simplify processing. This step ensures that only relevant characters (digits and operators) are considered.

        2. Iterate Through Characters:
        Initialize num to build multi-digit numbers, res to accumulate the result, and sign to manage the current sign (+ or -).
        Traverse the string character by character:

        Digits: Construct the current number (num) as long as the characters are digits.
        Operators (+, -):
        Add the current number to the result (res) multiplied by the current sign.
        Update sign based on the operator.
        Reset num to 0 for the next number.
        Operators (*, /):
        For multiplication and division, find the next number and perform the operation with the current num.
        Adjust num to reflect the result of the multiplication or division.
        At the end of the loop, add the last num to res considering the current sign.       
         */

        //Remove all spaces
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')
                sb.append(s.charAt(i));
        }

        s = sb.toString();

       // Stack<Integer> stack = new Stack<>();
        int num = 0;
        int result = 0;
        int sign = 1;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + Character.getNumericValue(c);
            } else if( c == '+') {
                result += sign * num;
                sign = 1;
                num = 0;
            } else if (c == '-') {
                result += sign * num;
                sign = -1;
                num = 0;
            } else if ( c == '*') {
                // At this point, since it is multiplication, we need to do an eager calculation, so we look for the next number that we can calculate with using '*'
                int nextNum = 0;
                while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    i++;
                    nextNum = nextNum * 10 + Character.getNumericValue(s.charAt(i));
                }
                num *= nextNum;
            }  else if ( c == '/') {
                // At this point, since it is multiplication, we need to do an eager calculation, so we look for the next number that we can calculate with using '/'
                int nextNum = 0;
                while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    i++;
                    nextNum = nextNum * 10 + Character.getNumericValue(s.charAt(i));
                }
                num /= nextNum;
            }
        }

         // Finally, add the last accumulated number to the result
        result += sign * num;

        return result;
    }
}
