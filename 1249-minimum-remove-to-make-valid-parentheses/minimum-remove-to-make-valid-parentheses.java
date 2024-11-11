class Solution {
    public String minRemoveToMakeValid(String s) {
        
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