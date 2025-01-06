class Solution {
    Map<Character, Integer> symbols = Map.of(
            'I', 1, 
            'V', 5, 
            'X', 10, 
            'L', 50, 
            'C', 100, 
            'D', 500, 
            'M', 1000);

    public int romanToInt(String s) {
        int total = 0;

        for(int itr = 0; itr < s.length(); itr++) {
            char curr= ' ', next= ' ';
            curr = s.charAt(itr);
            System.out.println("Curr symbol - " + curr);
            if(itr + 1 < s.length()) {
                next = s.charAt(itr + 1);
                System.out.println("Next symbol - " + next);
            }
            if((next != ' ') && (symbols.get(curr) < symbols.get(next))) {
                System.out.println("Next symbol is greater than curr. Subtract...");
                total -= symbols.get(curr);
            } else {
                System.out.println("Next symbol is lesser than curr. Add...");
                total += symbols.get(curr);
            }

            System.out.println("Total in this round - " + total);
        }

        return total;
    }
}