import java.util.Comparator;

import Stack.Stack;
import dynamic_array.DynamicArray;
import linked_list.LinkedList;
import priority_queue.PriorityQueue;
import queue.Queue;
/**
 * Tester
 */
public class Tester {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int i = 4; i >= 0; i--)
            pq.enq(i);
        System.out.println(pq);
        for (int i = 0; i < 3; i++)
            System.out.println(pq.deq());
        System.out.println(pq);
    }
}