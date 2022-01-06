package linked_list;

import java.util.NoSuchElementException;

public class LinkedList<E> {
    static class Node<E> {
        Node<E> prev;
        Node<E> next;
        E elem;

        Node(E e) {
            prev = null;
            next = null;
            elem = e;
        }

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

    public int size() {
        return size;
    }

    public void addLast(E e) {
        Node<E> new_node = new Node<>(e);
        Node<E> old_tail = this.tail;
        new_node.prev = old_tail;
        this.tail = new_node;

        if (old_tail == null) {
            this.head = new_node;
        } else {
            old_tail.next = new_node;
        }
        size++;
    }

    public void addFirst(E e) {
        Node<E> new_node = new Node<>(e);
        Node<E> old_head = this.head;
        new_node.next = old_head;
        this.head = new_node;

        if (old_head == null) {
            this.tail = new_node;
        } else {
            old_head.prev = new_node;
        }
    }

    public void add(E e) {
        addLast(e);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public E remove(int index) {
        rangeCheck(index);
        int i = 0;
        Node<E> node = this.head;
        for (; i < index; node = node.next, i++);
        final E elem = node.elem;
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) {
            this.head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            this.tail = prev;
        } else {
            next.prev = prev;
        }
        node = null;
        return elem;
    }

    public E removeLast() {
        if (this.tail == null)
            throw new NoSuchElementException();

        final E elem = this.tail.elem;
        Node<E> prev = this.tail.prev;

        this.tail = prev;
        if (prev == null) {
            this.head = null;
        }
        else {
            prev.next = null;
        }
        size--;
        return elem;
    }

    public E removeFirst() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }

        final E elem = this.head.elem;
        Node<E> next = this.head.next;

        this.head = next;
        if (next == null) {
            this.tail = null;
        } else {
            next.prev = null;
        }
        size--;
        return elem;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(" ");
        for (Node<E> node = this.head; node != null ; node = node.next) {
            sb.append(node.toString()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
