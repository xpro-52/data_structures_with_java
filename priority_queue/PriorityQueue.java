package priority_queue;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class PriorityQueue<E> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int TIMES = 2;
    private final Comparator<? super E> comparator;

    public PriorityQueue(Comparator<? super E> comparator) {
        this.comparator = comparator;
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private int cap() {
        return elements.length;
    }

    public void enq(E e) {
        if (size >= cap())
            elements = Arrays.copyOf(elements, cap() * TIMES);
        
        elements[size] = e;
        siftUp();
        size++;
    }

    private void siftUp() {
        int k = size;
        while (k > 0) {
            int parent = (k - 1) / 2;
            if (comparator.compare((E) elements[k], (E) elements[parent]) < 0) {
                swap(elements, k, parent);
                k = parent;
            } else {
                break;
            }
        }
    } 

    public E deq() {
        final E fe = (E) elements[0];
        if (fe != null) {
            size--;
            final int last = size;
            final E le = (E) elements[last];
            elements[last] = null;
            if (last > 0) {
                elements[0] = le;
                siftDown();
            }
        }
        return fe;
    }

    private void siftDown() {
        int k = 0;
        int half = size / 2;
        while (k < half) {
            int child = 2 * k + 1;
            int right = child + 1;
            if (right < size && comparator.compare((E) elements[child], (E) elements[right]) >= 0)
                child = right;

            if (comparator.compare((E) elements[k], (E) elements[child]) > 0) {
                swap(elements, k, child);
                k = child;
            } else {
                break;
            }
        } 
    }

    private static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
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
