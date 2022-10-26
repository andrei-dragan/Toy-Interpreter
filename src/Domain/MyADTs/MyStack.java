package Domain.MyADTs;

import Domain.Exceptions.EmptyStackException;
import Domain.Statements.IStmt;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;
    MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public T pop() throws EmptyStackException {
        if (stack.isEmpty()) throw new EmptyStackException("The execution stack is empty!\n");
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public Boolean isEmpty() {
        return stack.isEmpty();
    }
}
