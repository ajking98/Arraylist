/**
 * Your implementation of an ArrayList.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     * You may add statements to this method.
     */
    public ArrayList() {
        createBackingArray((T[]) new Object[INITIAL_CAPACITY]);
    }

    @Override
    public void addAtIndex(int index, T data) throws
            IndexOutOfBoundsException, IllegalArgumentException   {
        checkForIndexOutOfBounds(index, size + 1);

        checkIllegalArgument(data);

        checkIfBackingArrayCapacityNeedsToIncrease();

        shiftingArrayElementToRight(index);

        insertElementAtIndex(index, data);

        incrementingSizeVariable();
    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        checkIllegalArgument(data);

        checkIfBackingArrayCapacityNeedsToIncrease();

        shiftingArrayElementToRight(0);

        insertElementAtIndex(0, data);

        incrementingSizeVariable();
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {
        checkIllegalArgument(data);

        checkIfBackingArrayCapacityNeedsToIncrease();

        insertElementAtIndex(size, data);

        incrementingSizeVariable();
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        checkForIndexOutOfBounds(index, size);

        T elementToRemove = removeElementAtIndex(index);

        decrementingSizeVariable();

        return elementToRemove;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        T elementToRemove = removeElementAtIndex(0);

        decrementingSizeVariable();

        return elementToRemove;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }

        T elementToRename = getElement(size - 1);

        insertElementAtIndex(size - 1, null);

        decrementingSizeVariable();

        return elementToRename;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException  {
        checkForIndexOutOfBounds(index, size);

        return getElement(index);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        createBackingArray((T[]) (new Object[INITIAL_CAPACITY]));
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        return backingArray;
    }

    /**
     * @param newList the newList to set to the old arrayList
     */
    private void createBackingArray(T[] newList) {
        backingArray = newList;
    }

    /**
     * @param index the element at a certain index
     * @return backingArray index (the element at that index of the array)
     */
    private T getElement(int index) {
        return backingArray[index];
    }

    /**
     * @param index the index of the array to add to the array
     * @param data the element to be added
     */
    private void insertElementAtIndex(int index, T data) {
        backingArray[index] = data;
    }

    /**
     * @param index the index where the element will be removed from the
     *              Arraylist
     * @return the element to be removed
     */
    private T removeElementAtIndex(int index) {
        T elementToRemove = getElement(index);

        for (int i = index; i < size - 1; i++) {
            insertElementAtIndex(i, getElement(i + 1));
        }

        insertElementAtIndex(size - 1, null);

        return elementToRemove;
    }

    /**
     * this increments the size variable
     */
    private void incrementingSizeVariable() {
        size++;
    }

    /**
     * the method decrements the size variable
     */
    private void decrementingSizeVariable() {
        size--;
    }

    /**
     * this is meant to check if the backingArray needs to be increase or not
     */
    private void checkIfBackingArrayCapacityNeedsToIncrease() {
        if (isBackingArrayFull()) {
            expandCapacity();
        }
    }

    /**
     * @return a boolean that lets us know if the arrayList is full
     */
    private boolean isBackingArrayFull() {
        return size >= backingArray.length;
    }

    /**
     * Creates a new array to store the contents of the list with twice
     * the capacity of the old one.
     */
    private void expandCapacity() {
        T[] newList = (T[]) (new Object[size * 2]);

        for (int i = 0; i < size; i++) {
            newList[i] = getElement(i);
        }

        createBackingArray(newList);
    }

    /**
     * @param index the index of the array at the moment where it should be
     *              shifted
     */
    private void shiftingArrayElementToRight(int index) {
        for (int i = size - 1; i >= index; i--) {
            insertElementAtIndex(i + 1, getElement(i));
        }
    }

    /**
     * @param index the index to check if it is out of bounds of the array
     * @param size the size of the array at the moment
     * @throws IndexOutOfBoundsException throws a index out of bounds exception
     */
    private void checkForIndexOutOfBounds(int index, int size)
            throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @param data the data with generic type that is used to check for the
     *             exception of illegalArguement
     * @throws IllegalArgumentException illegal arguement exception is thrown
     */
    private void checkIllegalArgument(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }
}
