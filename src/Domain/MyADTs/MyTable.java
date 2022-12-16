package Domain.MyADTs;

import Exceptions.KeyInTableException;
import Exceptions.KeyNotInTableException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyTable<T1, T2> implements MyITable<T1, T2> {
    HashMap<T1, T2> table;

    public MyTable() {
        table = new HashMap<>();
    }

    @Override
    public boolean isDefined(T1 key) {
        return table.containsKey(key);
    }

    @Override
    public void add(T1 key, T2 value) throws KeyInTableException {
        if (isDefined(key)) throw new KeyInTableException("file already opened.\n");
        table.put(key, value);
    }

    @Override
    public T2 lookup(T1 key) throws KeyNotInTableException {
        if (!isDefined(key)) throw new KeyNotInTableException("file not opened.\n");
        return table.get(key);
    }

    @Override
    public void delete(T1 key) throws KeyNotInTableException {
        if (!isDefined(key)) throw new KeyNotInTableException("file not opened.\n");
        table.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("FileTable:\n");

        Set<Map.Entry<T1, T2>> entrySet = table.entrySet();
        for (Map.Entry<T1, T2> t1T2Entry : entrySet) {
            answer.append(t1T2Entry.getKey()).append("-->").append((t1T2Entry).getValue()).append('\n');
        }

        answer.append("=======================================\n");
        return answer.toString();
    }
}

