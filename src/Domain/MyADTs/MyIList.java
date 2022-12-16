package Domain.MyADTs;

import Exceptions.ListElementException;
import Exceptions.ListIsEmptyException;

public interface MyIList<T> {
    void pushBack(T e);
    T getBack() throws ListIsEmptyException;
    T get(int index) throws ListElementException;
    void push(int index, T e) throws ListElementException;
    boolean isEmpty();
}
