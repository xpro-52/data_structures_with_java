package stack;

import linked_list.LinkedList;

public class Stack<E> extends LinkedList<E>{
    
    public Stack() {
        super();
    }

    public void push(E e) {
        addLast(e);
    }

    public E pop() {
        return removeLast();
    }
}
