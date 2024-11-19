public class EsdrelonHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    public EsdrelonHeap(int capacity) {
        maxSize = capacity;
        heap = new int[maxSize];
        size = 0;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private void swap(int pos1, int pos2) {
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    public void insert(int element) {
        if (size >= maxSize) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = element;
        int current = size;

        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public int extractMax() {
        if (size == 0) return Integer.MIN_VALUE;
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(0);
        return max;
    }

    private void maxHeapify(int pos) {
        int largest = pos;
        int left = leftChild(pos);
        int right = rightChild(pos);

        if (left < size && heap[left] > heap[largest]) largest = left;
        if (right < size && heap[right] > heap[largest]) largest = right;

        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    public static void main(String[] args) {
        EsdrelonHeap heap = new EsdrelonHeap(10);
        heap.insert(20);
        heap.insert(15);
        heap.insert(500);
        System.out.println("Max: " + heap.extractMax());
    }
}
