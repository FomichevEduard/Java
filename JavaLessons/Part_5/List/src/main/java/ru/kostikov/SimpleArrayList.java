package ru.kostikov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Алексей on 12.10.2016.
 */
public class SimpleArrayList<E> implements SimpleList<E> {
    /** Array of items  */
    protected Object[] array;

    /** Current index of storage */
    protected int index;

    private final int DEFAULT_SIZE = 10;

    public SimpleArrayList() {
        this.array = new Object[DEFAULT_SIZE];
    }

    public SimpleArrayList(int size) {
        this.array = new Object[size];
    }

    /**
     * Checks range
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >=  this.array.length){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+index );
        }
    }


    /**
     * Add new item at end of array
     *
     * @param e - new item
     */
    @Override
    public void add(E e) {

        if (this.index >= this.array.length){
            this.array = Arrays.copyOf(this.array, this.array.length*2);
        }
        this.array[this.index++] = e;
    }

    /**
     * Add new item by index
     *
     * @param e - new item
     */
    @Override
    public void add(int index, E e) {

        if (index >= this.array.length){
            this.array = Arrays.copyOf(this.array, this.array.length*2);
        }

        int numMoved = this.index - index;
        if (numMoved > 0)
            System.arraycopy(this.array, index, this.array, index+1,
                    numMoved);

        this.array[index] = e;
        this.index++;
    }

    /**
     * Gets element by index
     *
     * @param index index of element
     * @return element value
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) this.array[index];
    }

    /**
     * Deletes element from array
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void delete(int index) {
        rangeCheck(index);

        int numMoved = this.index - index - 1;
        if (numMoved > 0)
            System.arraycopy(this.array, index+1, this.array, index,
                    numMoved);
        this.array[--this.index] = null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> resultIter = new Iterator<E>() {
            private int iterIndex = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;

                if (iterIndex < array.length && array[iterIndex] != null){
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (!this.hasNext()){
                    throw new NoSuchElementException();
                }
                return (E) array[iterIndex++];
            }
        };

        return resultIter;
    }
}
