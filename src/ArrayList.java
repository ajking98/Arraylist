/**
 * Your implementation of an ArrayList.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     * <p>
     * You may add statements to this method.
     */
    public ArrayList() {
        overwriteBackingArray((T[]) new Object[INITIAL_CAPACITY]);
    }

    /**
     * @param index The index where you want the new element.
     * @param data Any object of type T.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Override
    public void addAtIndex(int index, T data) throws
            IndexOutOfBoundsException, IllegalArgumentException   {
        checkForIndexOutOfBounds(index, size + 1);

        checkIllegalArguement(data);

        checkIfBackingArrayCapacityNeedsToIncrease();

        shiftingArrayElementToRight(index);

        insertDataAtIndexInBackingArray(index, data);

        incrementingSizeVariableOfBackingArray();
    }

    /**
     * @param data The data to add to the list.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        checkIllegalArguement(data);

        checkIfBackingArrayCapacityNeedsToIncrease();

        shiftingElementsToMakeSpaceAtFront();

        insertDataAtIndexInBackingArray(0, data);

        incrementingSizeVariableOfBackingArray();
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {
        checkIllegalArguement(data);

        checkIfBackingArrayCapacityNeedsToIncrease();

        insertDataAtIndexInBackingArray(size, data);

        incrementingSizeVariableOfBackingArray();
    }

    /**
     * @param index The index of the element
     * @return The object that was formerly at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     */
    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        checkForIndexOutOfBounds(index, size);

        T elementToRemove = getElement(index);

        for (int i = index; i < size - 1; i++) {
            insertDataAtIndexInBackingArray(i, getElement(i + 1));
        }

        insertDataAtIndexInBackingArray(size - 1, null);

        decrementingSizeVariableOfBackingArray();

        return elementToRemove;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        T elementToRemove = getElement(0);

        for (int i = 0; i < size - 1; i++) {
            insertDataAtIndexInBackingArray(i, getElement(i + 1));
        }

        insertDataAtIndexInBackingArray(size - 1, null);

        decrementingSizeVariableOfBackingArray();

        return elementToRemove;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }

        T result = getElement(size - 1);

        insertDataAtIndexInBackingArray(size - 1, null);

        decrementingSizeVariableOfBackingArray();

        return result;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException  {
        checkForIndexOutOfBounds(index, size);

        return getElement(index);
    }

    @Override
    public boolean isEmpty() {
        boolean value;

        int counter = 0;

        for (int i = 0; i < backingArray.length; i++) {
            if (getElement(i) == null) {
                counter++;
            }
        }

        if (counter == backingArray.length) {
            value = true;
        } else {
            value = false;
        }

        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        overwriteBackingArray((T[]) (new Object[INITIAL_CAPACITY]));
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
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

        overwriteBackingArray(newList);
    }

    /**
     *
     * @param newList the newList to set to the old arrayList
     */
    private void overwriteBackingArray(T[] newList) {
        backingArray = newList;
    }

    /**
     *
     * this is meant to check if the backingArray needs to be increase or not
     */
    private void checkIfBackingArrayCapacityNeedsToIncrease() {
        if (isBackingArrayFull()) {
            expandCapacity();
        }
    }

    /**
     *
     * @param index the index of the array to add to the array
     * @param data the element to be added
     */
    private void insertDataAtIndexInBackingArray(int index, T data) {
        backingArray[index] = data;
    }

    /**
     *
     * this increments the size variable
     */
    private void incrementingSizeVariableOfBackingArray() {
        size++;
    }

    /**
     *
     * @return a boolean that lets us know if the arrayList is full
     */
    private boolean isBackingArrayFull() {
        return size >= backingArray.length;
    }

    /**
     *
     * @param data the data with generic type that is used to check for the
     *             exception of illegalArguement
     */
    private void checkIllegalArguement(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * prints the size of the backingArray, this is used for testing purposes
     *
     */
    private void printSizeOfBackingArray() {
        System.out.println(size);
    }

    /**
     *
     * print the elements of the backingArray, this is used for testing purposes
     */
    private void printElementsOfBackingArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(getElement(i) + " ");
        }
        System.out.println();
    }

    /**
     *
     * shifts all the elements to the right to make space for the element
     * that will be added to the front
     */
    private void shiftingElementsToMakeSpaceAtFront() {
        for (int i = size - 1; i >= 0; i--) {
            insertDataAtIndexInBackingArray(i + 1, getElement(i));
        }
    }

    /**
     *
     * @param index the index of the array at the moment where it should be
     *              shifted
     */
    private void shiftingArrayElementToRight(int index) {
        for (int i = size - 1; i >= index; i--) {
            insertDataAtIndexInBackingArray(i + 1, getElement(i));

        }
    }

    /**
     * the method decrements the size variable
     *
     */
    private void decrementingSizeVariableOfBackingArray() {
        size--;
    }

    /**
     *
     * @param index the element at a certain index
     * @return backingArray index (the element at that index of the array)
     */
    private T getElement(int index) {
        return backingArray[index];
    }

    /**
     *
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
}
