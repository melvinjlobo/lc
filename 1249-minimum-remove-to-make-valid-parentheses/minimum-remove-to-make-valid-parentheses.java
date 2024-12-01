class Solution {

    /**
        The general idea is to keep a count of open paranthesis. Whenever we encounter:
        1. "(": we increment the counter
        2. ")":
            A. If the counter is zero, all "(" have been balanced. So this ")" is unbalanced. Mark for deletion
            B. If the counter is > zero, decrement it since this ")" balances an "("
        
        3. Once the loop is done, we have taken care of all ")", but if the counter is zero, we still have to take care of "("
        4. Run a loop from the end of the string and if we encounter "(", mark it for deletion and reduce the counter. 
        Why from the end?: This is because, we have taken care of all the ")". This means that all the "(" in the counter are AFTER we are done balancing them with ")"...which means they are towards the RHS...as all the ones from 0th index have been taken care of. Let's look at a few cases:
        1. **(*((**)**): Here after balancing, we see that the 3rd "(" is unbalanced...whici it towards the RHS
        2. **(**(***))**(**: Here also, we see that the extra "(" is towards teh RHS
        3. (************: Same as above

     */
    public String minRemoveToMakeValid(String s) {
        int openCounter = 0;
        char open = '(', close = ')';
        char[] arr = s.toCharArray();

        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == open)
                openCounter++;
            else if(arr[i] == close) {
                if(openCounter == 0)
                    arr[i] = '9';
                else
                    openCounter--;
            } else
                continue;
            
        }

        //Now that we have balanced all braces with ")", we check of any remaining "("
        for(int i = arr.length - 1; i >= 0 && openCounter > 0; i--) {
            if(arr[i] == open) {
                arr[i] = '9';
                openCounter--;
            }

        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != '9')
                sb.append(arr[i]);
        }

        return sb.toString();
    }


    public String minRemoveToMakeValidWithStack(String s) {
        
        Stack<Pair<Character, Integer>> paranStack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int ctr = 0; ctr < s.length(); ctr++) {
            Character currentChar = s.charAt(ctr);

            // Open case
            if (currentChar == ')') {
                if  (!paranStack.empty() && paranStack.peek().getKey().equals('(')) {
                    paranStack.pop();
                } else {
                    paranStack.push(new Pair<Character, Integer>(currentChar, ctr));
                }
            } else if (currentChar == '(') {
                paranStack.push(new Pair<Character, Integer>(currentChar, ctr));
            } else {
                continue;
            }

        }

        // Replace with dummy variable since we cannot directly delete characters from StringBuilder
        // as it cause index issues. So we replace all the index characters with <space> and then
        // build the final string with characters that are NOT space
        char[] sArray = new char[s.length()];
        sArray = s.toCharArray();
        
        Iterator<Pair<Character, Integer>> stackIter = paranStack.iterator();
        int index = 0;
        while (stackIter.hasNext()) {
            index = stackIter.next().getValue();
            sArray[index] = ' ';
        }

        for(char c: sArray) {
            if(c != ' ')
                result.append(c);
        }

        return result.toString();
    }
}