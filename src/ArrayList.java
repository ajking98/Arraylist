//Mohamud was here

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
    public void addAtIndex(int index, T data) {
        checkForIndexOutOfBound(index);

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
    public void addToFront(T data) {
        checkIllegalArguement(data);

        checkIfBackingArrayCapacityNeedsToIncrease();

        shiftingElementsToMakeSpaceAtFront();

        insertDataAtIndexInBackingArray(0, data);

        incrementingSizeVariableOfBackingArray();
    }

    @Override
    public void addToBack(T data) {
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
    public T removeAtIndex(int index) {
        checkForIndexOutOfBoundForRemoingAtIndexMethod(index);

        T elementToRemove = getElement(index);

        //Off by 1 Error
        for (int i = index; i < size; i++) {
            insertDataAtIndexInBackingArray(i, getElement(i + 1));
        }

        decrementingSizeVariableOfBackingArray();

        return elementToRemove;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        T elementToRemove = getElement(0);

        for (int i = 0; i < size; i++) {
            insertDataAtIndexInBackingArray(i, getElement(i + 1));
        }

        insertDataAtIndexInBackingArray(size, null);

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
    public T get(int index) {
        checkForIndexOutOfBound(index);

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

    private void overwriteBackingArray(T[] newList) {
        backingArray = newList;
    }

    private void checkIfBackingArrayCapacityNeedsToIncrease() {
        if (isBackingArrayFull()) {
            expandCapacity();
        }
    }

    private void insertDataAtIndexInBackingArray(int index, T data) {
        backingArray[index] = data;
    }

    private void incrementingSizeVariableOfBackingArray() {
        size++;
    }

    private boolean isBackingArrayFull() {
        return size >= backingArray.length;
    }

    private void checkIllegalArguement(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    private void printSizeOfBackingArray() {
        System.out.println(size);
    }

    private void printElementsOfBackingArray() {
        for (int i = 0; i < size; i++) {
            System.out.println(getElement(i));
        }
    }

    private void shiftingElementsToMakeSpaceAtFront() {
        for (int i = size - 1; i >= 0; i--) {
            insertDataAtIndexInBackingArray(i + 1, getElement(i));
        }
    }

    private void shiftingArrayElementToRight(int index) {
        for (int i = size - 1; i > index; i--) {
            insertDataAtIndexInBackingArray(i + 1, getElement(i));
        }
    }

    private void checkForIndexOutOfBoundForRemoingAtIndexMethod(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void decrementingSizeVariableOfBackingArray() {
        size--;
    }

    private T getElement(int index) {
        return backingArray[index];
    }

    private void checkForIndexOutOfBound(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
