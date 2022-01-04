package dynamic_array;

import java.util.Arrays;

public class DynamicArray<E> {
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int TIMES = 2;
    private int size;

    public DynamicArray() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public DynamicArray(int cap) {
        elements = new Object[cap];
        size = 0;
    }

    private int cap() { return elements.length; }

    public void add(E e) {
        if (size >= cap())
            elements = Arrays.copyOf(elements, cap() * TIMES);
        elements[size++] = e;
    }

    public E remove(int index) {
        @SuppressWarnings("unchecked") E removed_elem = (E) elements[index];
        final int last_i = size - 1;
        if (last_i > index)
            System.arraycopy(elements, index + 1, elements, index, last_i - index);
        elements[size = last_i] = null;
        return removed_elem;
    }

    @Override
    public String toString() {        
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(" ");
        for (int i = 0; i < size; i++)
            sb.append(elements[i]).append(" ");
        sb.append("]");
        return sb.toString();
    }
}
