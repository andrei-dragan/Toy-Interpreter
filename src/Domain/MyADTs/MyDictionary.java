package Domain.MyADTs;

import Domain.Exceptions.KeyInDictException;
import Domain.Exceptions.KeyNotInDictException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MyDictionary<T1, T2> implements MyIDictionary<T1, T2> {
    HashMap<T1, T2> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<T1, T2>();
    }

    @Override
    public Boolean isDefined(T1 key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void add(T1 key, T2 value) throws KeyInDictException {
        if (isDefined(key)) throw new KeyInDictException("variable already defined\n");
        dictionary.put(key, value);
    }

    @Override
    public T2 lookup(T1 key) throws KeyNotInDictException {
        if (!isDefined(key)) throw new KeyNotInDictException("variable was not declared\n");
        return dictionary.get(key);
    }

    @Override
    public void update(T1 key, T2 newValue) throws KeyNotInDictException {
        if (!isDefined(key)) throw new KeyNotInDictException("variable was not declared\n");
        T2 oldValue = dictionary.get(key);
        dictionary.replace(key, oldValue, newValue);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("SymTable:\n");

        boolean append = false;
        answer.append('{');
        Set<Map.Entry<T1, T2>> entrySet = dictionary.entrySet();
        for (Map.Entry<T1, T2> t1T2Entry : entrySet) {
            append = true;
            answer.append("[").append(t1T2Entry.getKey()).append(", ").append(((Map.Entry<T1, T2>) t1T2Entry).getValue()).append("], ");
        }

        if (append) {
            answer.deleteCharAt(answer.length() - 1);
            answer.deleteCharAt(answer.length() - 1);
        }
        answer.append("}\n");

        answer.append("=======================================\n");
        return answer.toString();
    }
}

