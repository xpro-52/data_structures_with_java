import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dynamic_array.DynamicArray;
import linked_list.LinkedList;
import priority_queue.PriorityQueue;
import stack.Stack;
import queue.Queue;
import hash.Hash;

/**
 * Tester
 */
public class Tester{
    @Test
    public void testDynamicArray() {
        DynamicArray<Integer> da = new DynamicArray<>();
        for (int i = 0; i < 11; i++) {
            da.add(i);
        }
        System.out.println(da);
        assertEquals(da.__cap(), 20);
        for (int i = 0; i < 11; i++) {
            da.remove(0);
        }
        assertEquals(da.size(), 0);
    }

    @Test
    public void testHash() {
        String[] test_array = {"a", "a", "b", "f", "z", "c"};
        Hash<String> hash = new Hash<>(test_array);
        assertEquals(hash.size(), 5);
        System.out.println(hash);
        DynamicArray<String> hash_array = hash.toDynamicArray();
        String prev = hash_array.get(0);
        for (int i = 1; i < hash.size(); i++) {
            assertNotEquals(prev, hash_array.get(i));
        }

        for (String t : test_array) {
            assertTrue(hash.contains(t));
        }
    }

    @Test
    public void testLinkedList() {
        LinkedList<Integer> ll  = new LinkedList<>();
        for (int i = 0; i < 11; i++) {
            ll.add(i);
        }
        assertEquals(11, ll.size());
        System.out.println(ll);

        ll.addFirst(12);
        assertEquals(12, (int) ll.remove(0));
        assertEquals(0, (int) ll.removeFirst());
        assertEquals(10, (int) ll.removeLast());
        assertEquals("[ 1 2 3 4 5 6 7 8 9 ]", ll.toString());
    }
}