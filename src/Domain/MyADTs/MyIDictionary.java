package Domain.MyADTs;

import Domain.Values.Value;
import Exceptions.KeyInDictException;
import Exceptions.KeyNotInDictException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<T1, T2> {
    boolean isDefined(T1 key);
    void add(T1 key, T2 value) throws KeyInDictException;
    T2 lookup(T1 key) throws KeyNotInDictException;
    void update(T1 key, T2 newValue) throws KeyNotInDictException;
    Collection<T2> getContent();

    Set<Map.Entry<T1, T2>> getEntrySet();
}
