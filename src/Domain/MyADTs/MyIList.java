package Domain.MyADTs;

import Domain.Exceptions.ListElementException;
import Domain.Exceptions.ListIsEmptyException;

public interface MyIList<T> {
    public void pushBack(T e);
    public T getBack() throws ListIsEmptyException;
    public T get(int index) throws ListElementException;
    public void push(int index, T e) throws ListElementException;
    public Boolean isEmpty();
}
