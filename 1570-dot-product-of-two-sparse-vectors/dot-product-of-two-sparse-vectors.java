class SparseVector {
    public List<Pair<Integer, Integer>> pairs;

    SparseVector(int[] nums) {
        pairs = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0)
                pairs.add(new Pair<Integer, Integer>(i, nums[i]));
        }
        //System.out.println("Sparse array - " + pairs.toString());
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0, p = 0, q = 0;

        while( p < this.pairs.size() && q < vec.pairs.size()) {
            Pair<Integer, Integer> pPair = this.pairs.get(p);
            Pair<Integer, Integer> qPair = vec.pairs.get(q);

           // System.out.println("pKey = " + pPair.getKey() + ", qKey = " + qPair.getKey());

            //IMP NOTE: Integer cannot be compared using `==`. Since it is an object, we have to use compareTo()            
            if(pPair.getKey() .compareTo(qPair.getKey()) == 0) {
                result += pPair.getValue() * qPair.getValue();
                //System.out.println("Result for p,q = " + p + " is - " + result);
                p++;
                q++;
            } else if (pPair.getKey().compareTo(qPair.getKey()) > 0) {
                q++;
            } else {
                p++;
            }
        }
        
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);