package queue;

import linked_list.LinkedList;

public class Queue<E> extends LinkedList<E> {
    public Queue() {
        super();
    }

    public void enq(E e) {
        addLast(e);
    }

    public E deq() {
        return removeFirst();
    }
}
