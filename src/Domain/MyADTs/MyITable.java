package Domain.MyADTs;

import Exceptions.KeyInTableException;
import Exceptions.KeyNotInTableException;

public interface MyITable<T1, T2> {
    boolean isDefined(T1 key);
    void add(T1 key, T2 value) throws KeyInTableException;
    T2 lookup(T1 key) throws KeyNotInTableException;
    void delete(T1 key) throws KeyNotInTableException;
}
