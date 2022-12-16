package Domain.MyADTs;

import Domain.Values.RefValue;
import Exceptions.AddressInHeapException;
import Exceptions.AddressNotInHeapException;
import Exceptions.KeyNotInDictException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyHeap<T> implements MyIHeap<Integer, T> {
    HashMap<Integer, T> heap;
    static int emptyAddr = 0;

    public MyHeap() {
        heap = new HashMap<Integer, T>();
    }

    synchronized static int getEmptyAddr() {
        emptyAddr++;
        return emptyAddr;
    }

    @Override
    public boolean isDefined(Integer key) {
        return heap.containsKey(key);
    }

    @Override
    public Integer add(T value) throws AddressInHeapException {
        int newAddr = getEmptyAddr();
        if (isDefined(newAddr)) throw new AddressInHeapException("address already defined inside the heap.\n");
        heap.put(newAddr, value);
        return newAddr;
    }

    @Override
    public T lookup(Integer key) throws AddressNotInHeapException {
        if (!isDefined(key)) throw new AddressNotInHeapException("address could not be found inside the heap.\n");
        return heap.get(key);
    }

    @Override
    public void delete(Integer key) throws AddressNotInHeapException {
        if (!isDefined(key)) throw new AddressNotInHeapException("address could not be found inside the heap.\n");
        heap.remove(key);
    }

    @Override
    public void update(Integer key, T newValue) throws AddressNotInHeapException {
        if (!isDefined(key)) throw new AddressNotInHeapException("address could not be found inside the heap.\n");
        T oldValue = heap.get(key);
        heap.replace(key, oldValue, newValue);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Heap:\n");

        Set<Map.Entry<Integer, T>> entrySet = heap.entrySet();
        for (Map.Entry<Integer, T> t1T2Entry : entrySet) {
            answer.append(t1T2Entry.getKey()).append("-->").append((t1T2Entry).getValue()).append('\n');
        }

        answer.append("=======================================\n");
        return answer.toString();
    }

    @Override
    public void setContent(Map<Integer, T> newMap) {
        heap.clear();
        heap.putAll(newMap);
    }

    @Override
    public Map<Integer, T> getContent() {
        return heap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

