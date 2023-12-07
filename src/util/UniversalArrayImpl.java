package util;

import java.util.Arrays;

public class UniversalArrayImpl<T> implements UniversalArray<T> {
    private Object[] array;


    public UniversalArrayImpl() {
        this.array = new Object[]{};
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void add(T element) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = element;
    }

    @Override
    public void addAll(Object[] elements) {
        for (var element : elements) {
            add((T) element);
        }
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length, Object[].class);
    }

}
