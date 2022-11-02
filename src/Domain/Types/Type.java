package Domain.Types;

import Domain.Values.Value;

public interface Type {
    public Value getDefault();
    public Type deepCopy();
}