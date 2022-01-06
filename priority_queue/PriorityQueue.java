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

    public int size() {
        return size;
    }

    public void enq(E e) {
        if (size >= cap()) {
            elements = Arrays.copyOf(elements, cap() * TIMES);
        }
        
        elements[size] = e;
        siftUp(size, comparator, elements);
        size++;
    }

    private static <T> void siftUp(int k, Comparator<? super T> comparator, Object[] elements) {
        while (k > 0) {
            int parent = (k - 1) / 2;
            if (comparator.compare((T) elements[k], (T) elements[parent]) < 0) {
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
                siftDown(size, comparator, elements);
            }
        }
        return fe;
    }

    private static <T> void siftDown(int size, Comparator<? super T> comparator, Object[] elements) {
        int k = 0;
        int k_lim = size / 2;
        while (k < k_lim) {
            int child = 2 * k + 1;
            int right = child + 1;
            if (right < size && comparator.compare((T) elements[child], (T) elements[right]) >= 0) {
                child = right;
            }
            if (comparator.compare((T) elements[k], (T) elements[child]) > 0) {
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
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
