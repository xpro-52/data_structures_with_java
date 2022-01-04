import Stack.Stack;
import dynamic_array.DynamicArray;
import linked_list.LinkedList;
import queue.Queue;
/**
 * Tester
 */
public class Tester {

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < 10; i++)
            q.enq(i);
        System.out.println(q);
        for (int i = 0; i < 10; i++)
             System.out.println(q.deq());
        System.out.println(q);
    }
}