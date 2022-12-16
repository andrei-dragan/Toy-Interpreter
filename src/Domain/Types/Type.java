package Domain.Types;

import Domain.Values.Value;

public interface Type {
    Value defaultValue();
    Type deepCopy();
}