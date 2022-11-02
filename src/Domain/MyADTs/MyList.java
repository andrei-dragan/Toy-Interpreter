package Domain.MyADTs;

import Domain.Exceptions.ListElementException;
import Domain.Exceptions.ListIsEmptyException;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    ArrayList<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    @Override
    public void pushBack(T e) {
        list.add(e);
    }

    @Override
    public Boolean isEmpty() {
        return (list.size() == 0);
    }

    @Override
    public T getBack() throws ListIsEmptyException {
        if (isEmpty()) throw new ListIsEmptyException("the list is empty.\n");
        return list.get(list.size() - 1);
    }

    @Override
    public T get(int index) throws ListElementException {
        if (index < 0 || index >= list.size()) throw new ListElementException("element index out of bounds.\n");
        return list.get(index);
    }

    @Override
    public void push(int index, T e) throws ListElementException {
        if (index < 0 || index > list.size()) throw new ListElementException("element index out of bounds.\n");
        pushBack(e);
        for (int i = list.size() - 1; i > index; i--) {
            T elem = list.get(i - 1);
            list.set(i, elem);
        }
        list.set(index, e);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Out:\n");

        for (T t : list) {
            answer.append(t.toString());
            answer.append("\n");
        }

        answer.append("=======================================\n");
        return answer.toString();
    }
}
