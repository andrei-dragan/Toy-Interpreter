package Domain.MyADTs;

import Exceptions.EmptyStackException;
import Exceptions.StackOverflowException;

public interface MyIStack<T> {
    T pop() throws EmptyStackException;
    void push(T v) throws StackOverflowException;
    boolean isEmpty();
}
