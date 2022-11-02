package Domain.Types;

import Domain.Values.BoolValue;
import Domain.Values.IntValue;
import Domain.Values.Value;

public class BoolType implements Type {
    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    @Override
    public Value getDefault() {
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "bool";
    }
}