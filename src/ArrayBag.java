public final class ArrayBag<T> implements BagInterface<T> {

    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 52;
    private boolean initialised = false;
    private static final int MAX_CAPACITY = 52;

    private boolean isArrayFull() {
        return (bag.length == numberOfEntries);
    }

    private void checkInitialisation() {
        if (!initialised)
            throw new SecurityException("ArrayBag object is not initialised properly!");
    }

    private T removeElementAt(int index) {
        T result = null;
        if (!isEmpty() && index >= 0 && index < numberOfEntries) {
            result = bag[index];
            bag[index] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBag(int capacity) {
        if (capacity <= MAX_CAPACITY) {
            T[] tempBag = (T[]) new Object[capacity];
            bag = tempBag;
            numberOfEntries = 0;
            initialised = true;
        } else throw new IllegalStateException("Attempt to create a bag where the capacity exceeds the maximum");
    }

    public int getCurrentSize() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }

    public boolean addNewEntry(Card newEntry) {
        checkInitialisation();
        if (isArrayFull()) return false;
        else {
            bag[numberOfEntries++] = (T) newEntry;
            return true;
        }
    }

    public T remove() {
        checkInitialisation();
        T result = null;
        return removeElementAt(numberOfEntries - 1);
    }

    public boolean remove(T anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfEntries)
            if (bag[index].equals(anEntry)) found = true;
            else index++;
        if (found) removeElementAt(index);
        return found;
    }

    public void clear() {
        while (!isEmpty()) remove();
    }

    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) count++;
        }
        return count;
    }

    public boolean contains(T anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfEntries)
            if (bag[index].equals(anEntry)) found = true;
        return found;
    }

    public T[] toArray() {
        T[] resultArray = (T[]) new Object[numberOfEntries];
        System.arraycopy(bag, 0, resultArray, 0, numberOfEntries);
        return resultArray;
    }

    @Override
    public T get(int index) {
        return bag[index];
    }

    @Override
    public T set(int index, Object element) {
        return null;
    }

    @Override
    public int recurssion(T anEntry) {
        return 0;
    }

    public String toString() {
        String strResult = "Bag[";
        for (int i = 0; i < numberOfEntries; i++) {
            strResult += bag[i] + " ";
        }
        strResult += "]";
        return strResult;
    }
}