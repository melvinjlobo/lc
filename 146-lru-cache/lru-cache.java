/**
    To build the LRU cache we use a HasMap for O(1) operations and a doubly linked list for managing the Least Recently used feature
    - Why use a doubly linked list and not a singly linked list?
        1. Efficient Removal of Nodes
        In a single linked list, removing a node requires knowing the previous 
        node, but you can't directly find it because there are no backward links.

        To remove a node, you'd have to traverse the list to find the previous  
        node, which takes O(n) time i.e. Removing the last node requires traversing 
        the list to find the second-to-last node (O(n)).

        In a doubly linked list, each node has a prev pointer, so you can directly 
        access the previous node, allowing O(1) removal.
        For example, if we want to remove the least recently used (LRU) node 
        (which is at the tail of the doubly linked list), we can directly use tail.
        prev to access it and remove it efficiently.

        2. Efficient Moving of Nodes

        When a key is accessed (get operation), the corresponding node needs to be 
        moved to the head (most recently used position).

        In a single linked list, moving a node requires updating multiple 
        pointers, including finding the previous node (an O(n) operation).
        In a doubly linked list, you can easily remove the node using its prev and 
        next pointers and insert it at the head in O(1).

        3. Bidirectional Traversal

        A doubly linked list allows you to traverse the list in both directions 
        (from head to tail and tail to head). This is particularly useful in the 
        LRU Cache because:
            The most recently used element is at the head.
            The least recently used element is at the tail.
            This bidirectional traversal helps in efficiently managing the cache.


 */

class Node {
    int key;
    int val;
    Node prev;
    Node next;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {

    int capacity = 0;
    //Cache
    Map<Integer, Node> cache;

    //Doubly Linked List
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        
        Node valNode = cache.get(key);
        
        // Move the Node to head as it is the most recently used
        removeNode(valNode);
        addNode(valNode);

        return valNode.val;
    }
    
    public void put(int key, int value) {
        //If the node exists, update. Since we need to move the node to the head, 
        //we have to remove it anyways. So do it now
        if(cache.containsKey(key)) {
            Node oldNode = cache.get(key);
            removeNode(oldNode);
        }

        //Create a new node with the new value
        Node valNode = new Node(key, value);
        
        //Add the node to the DLL
        addNode(valNode);

        // Add the new node to the map
        this.cache.put(key, valNode);

        //If we are above capacity, take action to remove the LRU Node...
        if(this.cache.size() > capacity) {
            //remove the LRU node
            Node lruNode = tail.prev;
            removeNode(lruNode);
            this.cache.remove(lruNode.key);
        }      
        
    }

    private void addNode(Node entry) {
        //Add Node to head;
        entry.next = this.head.next;
        this.head.next.prev = entry;
        this.head.next = entry;
        entry.prev = head;
    }

    private void removeNode(Node entry) {
        entry.prev.next = entry.next;
        entry.next.prev = entry.prev;
    }


}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */