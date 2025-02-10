class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<String>(wordDict);
        Map<Integer, Boolean> dp = new HashMap<>();
        return recurse(s, 0, wordSet, dp);
    }

    public boolean recurse(String s, int start, Set<String> wordSet, Map<Integer, Boolean> dp) {
        if(start == s.length())
            return true;
        
        if(dp.containsKey(start))
            return dp.get(start);

        for(int end = start; end < s.length(); end++) {
            if(wordSet.contains(s.substring(start, end + 1)) && recurse(s, end + 1, wordSet, dp)) {
                dp.put(start, true);
                return true;
            }
        }

        dp.put(start, false);
        return false;
    }
}