package Domain.MyADTs;

import Domain.Exceptions.EmptyStackException;
import Domain.Exceptions.StackOverflowException;

public interface MyIStack<T> {
    public T pop() throws EmptyStackException;
    public void push(T v) throws StackOverflowException;
    public Boolean isEmpty();
}
