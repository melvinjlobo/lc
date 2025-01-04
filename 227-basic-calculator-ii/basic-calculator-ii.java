/**
 This is a common solution for all calculators (Basic Calculator 1, 2 and 3)
 */

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
                //index++; // Move past the ')'
                break;   // End recursion for parentheses
            }

            index++;
        }

        currentResult += lastNumber;
        return currentResult;

    }

}
