package Domain.Types;

import Domain.Values.IntValue;
import Domain.Values.Value;

public class IntType implements Type {
    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }

    @Override
    public Value getDefault() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }
}
