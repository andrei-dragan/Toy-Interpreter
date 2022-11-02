package Domain.MyADTs;

import Domain.Exceptions.KeyInDictException;
import Domain.Exceptions.KeyNotInDictException;

public interface MyIDictionary<T1, T2> {
    public Boolean isDefined(T1 key);
    public void add(T1 key, T2 value) throws KeyInDictException;
    public T2 lookup(T1 key) throws KeyNotInDictException;
    public void update(T1 key, T2 newValue) throws KeyNotInDictException;
}
