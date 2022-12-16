package Domain.MyADTs;

import Exceptions.AddressInHeapException;
import Exceptions.AddressNotInHeapException;
import Exceptions.KeyNotInDictException;

import java.util.Map;

public interface MyIHeap<T1, T2> {
    boolean isDefined(T1 key);
    T1 add(T2 value) throws AddressInHeapException;
    T2 lookup(T1 key) throws AddressNotInHeapException;
    void delete(T1 key) throws AddressNotInHeapException;
    void update(T1 key, T2 newValue) throws AddressNotInHeapException;
    void setContent(Map<T1, T2> newMap);
    Map<T1, T2> getContent();
}
