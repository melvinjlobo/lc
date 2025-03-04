/**
 This is a common solution for all calculators (Basic Calculator 1, 2 and 3)
 */

class Solution {

    // private int index; // Class field to track the current position in the string

    // public int calculate(String s) {
    //     index = 0; // Initialize the index at the start of the string
    //     return calculateHelper(s);
    // }

    // private int calculateHelper(String s) {
    //     int currentResult = 0; // Final result
    //     int lastNumber = 0;    // Tracks the last number for precedence
    //     char operation = '+';  // Default operation
    //     int currentNumber = 0; // Current number being processed

    //     while (index < s.length()) {
    //         char c = s.charAt(index);

    //         if (Character.isDigit(c)) {
    //             currentNumber = currentNumber * 10 + (c - '0');
    //         }

    //         if (c == '(') {
    //             index++; // Move past the '('
    //             currentNumber = calculateHelper(s); // Recursively calculate inner expression
    //         }

    //         if ((!Character.isDigit(c) && c != ' ') || index == s.length() - 1) {
    //             // Apply the previous operation
    //             if (operation == '+') {
    //                 currentResult += lastNumber;
    //                 lastNumber = currentNumber;
    //             } else if (operation == '-') {
    //                 currentResult += lastNumber;
    //                 lastNumber = -currentNumber;
    //             } else if (operation == '*') {
    //                 lastNumber *= currentNumber;
    //             } else if (operation == '/') {
    //                 lastNumber /= currentNumber;
    //             }

    //             // Update the operation
    //             operation = c;
    //             currentNumber = 0;
    //         }

    //         if (c == ')') {
    //             //index++; // NOTE: WE don't need to increase the index as we will run the operator if code above
    //             break;   // End recursion for parentheses
    //         }

    //         index++;
    //     }

    //     currentResult += lastNumber;
    //     return currentResult;

    // }

    //3: ln = 3, r = 0, op = +
    //+: ln = 0, r = 3, op = +
    //2: ln = 2, r = 3, op = +
    //*: ln = 0, r = 5, op = *
    //2: ln = 2, 

    // WIth Stack
    // public int calculate(String s) {
    //     int currentNumber = 0;
    //     char operand = '+';
    //     int result = 0;
    //     Stack<Integer> tempStack = new Stack<>();

    //     for (int i = 0; i < s.length(); i++) {
    //         char c = s.charAt(i);

    //         if(Character.isDigit(c)) {
    //             currentNumber = currentNumber * 10 + Character.getNumericValue(c);
    //         } 
            
    //         if (!Character.isWhitespace(c) || i == s.length() - 1) {
    //             if(operand == '+') {
    //                 tempStack.push(currentNumber);
    //                 System.out.println("In +, pushing + " + currentNumber);
    //             }
    //             else if( operand == '-') {
    //                 tempStack.push(-currentNumber);
    //                 System.out.println("In +, pushing - " + currentNumber);
    //             }
    //             else if( operand == '*') {
    //                 System.out.println("In *, stack top - " + tempStack.peek());
    //                 tempStack.push(tempStack.pop() * currentNumber);
    //                 System.out.println("In *, pushing  " + tempStack.peek());
    //             }
    //             else if(operand == '/') {
    //                 System.out.println("In /, stack top - " + tempStack.peek());
    //                 tempStack.push(tempStack.pop() / currentNumber);
    //                 System.out.println("In /, pushing  " + tempStack.peek());
    //             }
                
    //             operand = c;
    //             currentNumber = 0;
    //         }
    //     }

    //     // Now run through the stack to calculate the final result
    //     while(!tempStack.isEmpty()) {
    //         result += tempStack.pop();
    //     }

    //     return result;
    // }

    //Without stack
    // Since we will not run a loop on the stack later, we need some way of keeping a running result
    // The other use case is immediately multiplying or dividing greedily once we encounter those operations
    // For this, we use a lastNumber variable, that stores the currentNumber after + and - calculations, 
    public int calculate(String s) {
        int currentNumber = 0;
        char operand = '+';
        int result = 0;
        int lastNumber = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + Character.getNumericValue(c);
            } 
            
            if (((!Character.isDigit(c)) && !Character.isWhitespace(c)) || i == s.length() - 1) {
                if(operand == '+') {
                    result += lastNumber;           // Equivalent to computing result on the fly
                    lastNumber = currentNumber;     // Equivalent to storing stack top that we might need for * and /
                }
                else if( operand == '-') {
                    result += lastNumber;           // Equivalent to computing result on the fly
                    lastNumber = -currentNumber;     // Equivalent to storing stack top that we might need for * and /
                }
                else if( operand == '*') {
                    //ref: tempStack.push(tempStack.pop() * currentNumber);
                    lastNumber = lastNumber * currentNumber;
                }
                else if(operand == '/') {
                    // ref: tempStack.push(tempStack.pop() / currentNumber);
                    lastNumber = lastNumber / currentNumber;
                }
                
                operand = c;
                currentNumber = 0;      // reset the current number when we perform an operation since we want to acquire the next number
            }
        }

        return result += lastNumber;   // Equivalent to running the loop on the stack
    }
}
