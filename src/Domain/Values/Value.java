package Domain.Values;

import Domain.Types.Type;

public interface Value {
    public Type getType();
    Value deepCopy();
}
