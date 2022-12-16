package Domain.MyADTs;

import Exceptions.EmptyStackException;
import Exceptions.StackOverflowException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;
    int cap;
    public MyStack(int capacity) {
        stack = new Stack<T>();
        this.cap = capacity;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (stack.isEmpty()) throw new EmptyStackException("the execution stack is empty!\n");
        return stack.pop();
    }

    @Override
    public void push(T v) throws StackOverflowException {
        if (stack.size() == cap) {
            throw new StackOverflowException("stack overflow\n");
        }
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("ExeStack:\n");

        Stack<T> tempStack = new Stack<T>();
        while (!stack.isEmpty()) {
            T v = stack.pop();
            answer.append(v.toString());
            answer.append("\n");
            tempStack.push(v);
        };
        while (!tempStack.empty()) {
            T v = tempStack.pop();
            stack.push(v);
        };

        answer.append("=======================================\n");
        return answer.toString();
    }
}
