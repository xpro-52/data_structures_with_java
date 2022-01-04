package linked_list;

import java.util.NoSuchElementException;

/**
 * 連結リスト
 */
public class LinkedList<E> {
    /**
     * 連結リストのノード
     * prev: 前のノード
     * next: 次のノード
     */
    static class Node<E> {
        Node<E> prev;
        Node<E> next;
        E elem;

        Node(E e) {
            prev = null;
            next = null;
            elem = e;
        }

        void setPrev(Node<E> prev) { this.prev = prev; }
        void setNext(Node<E> next) { this.next = next; }
        void setElem(E elem) { this.elem = elem; }

        Node<E> prev() { return prev; }
        Node<E> next() { return next; }
        E       elem() { return elem; }

        @Override
        public String toString() {
            return elem.toString();
        }
    }

    private Node<E> head;  //先頭ノード
    private Node<E> tail;  //末尾ノード
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addLast(E e) {
        Node<E> new_node = new Node<>(e);
        Node<E> old_tail = this.tail;
        new_node.setPrev(old_tail);
        this.tail = new_node;

        if (old_tail == null)  // 要素が一つならhead=tailとする
            this.head = new_node;
        else
            old_tail.setNext(new_node);
        size++;
    }

    public void addFirst(E e) {
        Node<E> new_node = new Node<>(e);
        Node<E> old_head = this.head;
        new_node.setNext(old_head);
        this.head = new_node;

        if (old_head == null)
            this.tail = new_node;
        else
            old_head.setPrev(new_node);
    }

    public void add(E e) {
        addLast(e);
    }

    public E remove(int index) {
        int i = 0;
        Node<E> node = this.head;
        for (; i < index; node = node.next(), i++);
        E elem = node.elem();
        Node<E> prev = node.prev();
        Node<E> next = node.next();

        if (prev == null) {
            this.head = next;
        } else {
            prev.setNext(next);
        }

        if (next == null) {
            this.tail = prev;
        } else {
            next.setPrev(prev);
        }
        node = null;
        return elem;
    }

    public E removeLast() {
        if (this.tail == null)
            throw new NoSuchElementException();

        E elem = this.tail.elem();
        Node<E> prev = this.tail.prev();

        this.tail = prev;
        if (prev == null)
            this.head = null;
        else
            prev.setNext(null);
        
        size--;
        return elem;
    }

    public E removeFirst() {
        if (this.head == null)
            throw new NoSuchElementException();

        E elem = this.head.elem();
        Node<E> next = this.head.next();

        this.head = next;
        if (next == null)
            this.tail = null;
        else
            next.setPrev(null);

        size--;
        return elem;
    }

    @Override
    public String toString() {
        if (size == 0)
            return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<E> node = this.head; ; node = node.next()) {
            sb.append(node.toString());
            if (node.next() == null)
                return sb.append("]").toString();
            sb.append(", ");
        }
    }
}
