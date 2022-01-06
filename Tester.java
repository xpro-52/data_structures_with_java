import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import binary_search_tree.BinarySearchTree;
import dynamic_array.DynamicArray;
import linked_list.LinkedList;
import priority_queue.PriorityQueue;
import queue.Queue;
import stack.Stack;
import hash.Hash;

/**
 * Simple test
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
        assertEquals(5, hash.size());
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

    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
        for (int i = 10; i >= 0; i--) {
            assertEquals(i, (int) stack.pop());
        }
    }

    @Test
    public void testQueue() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 11; i++) {
            queue.enq(i);
        }
        for (int i = 0; i < 11; i++) {
            assertEquals(i, (int) queue.deq());
        }
    }

    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int i = 10; i >= 0; i--) {
            pq.enq(i);
        }

        System.out.println(pq);
        assertEquals(11, pq.size());

        for (int i = 0; i < 11; i++) {
            assertEquals(i, (int) pq.deq());
        }
    }

    @Test
    public void testBST() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>((a, b) -> Integer.compare(a, b));
        Integer[] input = {5, 1, 3, 9, 7, 2};
        for (Integer i : input) {
            bst.put(i, i);
        }
        assertEquals("[ {1:1} {2:2} {3:3} {5:5} {7:7} {9:9} ]", bst.toString());
        assertEquals(input.length, bst.size());
        for (Integer i : input) {
            assertEquals(i, bst.get(i));
        }

        try {
            bst.remove(4);
        } catch (Exception e) {
            assertEquals(new NoSuchElementException().getClass(), e.getClass());
        }

        for (Integer i : input) {
            bst.remove(i);
        }
        assertEquals(0, bst.size());
    }
}