package Domain.MyADTs;

import Domain.Exceptions.EmptyStackException;

public interface MyIStack<T> {
    public T pop() throws EmptyStackException;
    public void push(T v);
    public Boolean isEmpty();
}
