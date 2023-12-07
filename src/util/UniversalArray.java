package util;

public interface UniversalArray<T> {
    void add(T element);

    void addAll(Object[] elements);

    int size();

    T get(int index);

    Object[] toArray();
}
