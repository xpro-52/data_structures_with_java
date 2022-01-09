package hash;

import dynamic_array.DynamicArray;

public class Hash<E> {
    private Object[] elements;
    private static final int TIMES = 2;
    private int capacity;
    private int size;

    public Hash(E[] array) {
        elements = new Object[array.length * TIMES];
        capacity = elements.length;
        for (E a : array) {
            int key = hash(a, capacity);
            while (elements[key] != null && !elements[key].equals(a)) {  
                key = (key + 1) % capacity;
            }
            if (elements[key] == null) {
                elements[key] = a;
                size++;
            }
        }
    }

    private static <T> int hash(T x, int capacity) {
        return x.hashCode() % capacity;
    }

    public boolean contains(Object o) {
        int key = hash(o, capacity);
        while (elements[key] != null) {
            if (elements[key].equals(o)) {
                return true;
            }
            key = (key + 1) % capacity;
        }
        return false;
    }

    public boolean remove(Object o) {
        int key = hash(o, capacity);
        while (elements[key] != null) {
            if (elements[key].equals(o)) {
                elements[key] = null;
                return true;
            }
            key = (key + 1) % capacity;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public DynamicArray<E> toDynamicArray() {
        DynamicArray<E> hash_array = new DynamicArray<>();
        for (Object e : elements) {
            if (e != null) {
                hash_array.add((E) e);
            }
        }
        return hash_array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(" ");
        for (Object e : elements) {
            if (e != null) {
                sb.append(e.toString()).append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
