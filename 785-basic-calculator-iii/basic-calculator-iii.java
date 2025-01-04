/**
 This is a common solution for Basic Calculator 2 and 3

 Logic:
  Let’s simplify this and think of it like solving a treasure map puzzle, where you follow steps and rules to find the final treasure
  The Puzzle Rules
	1.	You have a backpack (result) to collect treasures as you go.
	2.	You have a small pouch (lastNumber) to hold the last treasure you found.
	3.	You also follow instructions (like +, -, *, /) to decide how to handle the treasure when you see the next step or finish a small part of the puzzle.
    4. Some instructions (like + and -) can be done right away, but others (like * and /) need to wait because they work differently. If you add too soon, you might mess up the puzzle.

    For example "2 + 3 * 4":
        •	If you did 2 + 3 first, then multiplied the result by 4, you’d get 20.
        •	But following the rules, 3 * 4 is done first, so the correct answer is 14.

    Why Do We Delay Operations?

    If you rush and don’t wait for the next step, you might:
        •	Add when you should multiply first.
        •	Solve a mini-puzzle in the wrong order.

    By waiting for the next step, you make sure to follow the rules properly and get the right answer.

    Example: "2 + 3 * 4"
	1.	Start: result = 0, lastNumber = 0.
	2.	See 2: Put it in your pouch → lastNumber = 2.
	3.	See +: Move 2 to your backpack → result = 2, reset lastNumber = 0 (since puch is now empty).
	4.	See 3: Put it in your pouch → lastNumber = 3.
	5.	See *: Wait for the next number.
	6.	See 4: Multiply 3 * 4 → lastNumber = 12.
	7.	End: Add 12 from your pouch to your backpack → result = 14.

    Example: "2 * (3 + 5) - (6 / 2)"

    Initial Setup
        •	result = 0 (the backpack for the final result)
        •	lastNumber = 0 (the pouch for the last computed number)
        •	operation = '+' (default starting operation)
        •	index = 0 (tracks our position in the input string)
    1.	Start Parsing:
	    •	The first character is 2. This is a number, so currentNumber = 2.
	2.	Encounter *:
	    •	The * means we need to wait for the next number to multiply.
	    •	lastNumber is updated to 2.
	3.	Encounter (:
	    •	The ( signals a sub-expression. We call evaluate recursively to handle it.
	    •	Inside the Sub-Expression "3 + 5":
	    •	Parse 3, then encounter +, so add 3 to the result (backpack becomes 3).
        •	Parse 5, add it to the result (result = 3 + 5 = 8).
        •	Return 8 to the main expression.
	4.	Back to Main Expression:
	    •	Multiply 2 * 8 = 16, update lastNumber = 16.
	5.	Encounter -:
        •	Add lastNumber to the result (result = 0 + 16 = 16).
        •	Reset lastNumber = 0 and update operation = '-'.
	6.	Encounter ( Again:
        •	Another sub-expression starts. We call evaluate recursively.
        •	Inside the Sub-Expression "6 / 2":
        •	Parse 6, then encounter /, so wait for the next number.
        •	Parse 2, compute 6 / 2 = 3.
        •	Return 3 to the main expression.
	7.	Back to Main Expression:
	    •	Subtract 3 from the result (result = 16 - 3 = 13).
	8.	End of Input:
        •	Add lastNumber to the result (though it’s 0, so no change).
        •	Final result: 13.


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
                //index++; // NOTE: WE don't need to increase the index as we will run the operator if code above
                break;   // End recursion for parentheses
            }

            index++;
        }

        currentResult += lastNumber;
        return currentResult;

    }

}
