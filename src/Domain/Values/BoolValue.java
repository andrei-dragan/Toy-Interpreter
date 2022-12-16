package Domain.Values;

import Domain.Types.BoolType;
import Domain.Types.Type;

public class BoolValue implements Value {
    boolean val;
    public BoolValue(boolean v) {val = v;}

    @Override
    public boolean equals(Object another) { return another instanceof BoolValue; }

    public boolean getVal() {return val;}

    @Override
    public String toString() {
        return Boolean.toString(val);
    }

    @Override
    public Type getType() {return new BoolType();}

    @Override
    public Value deepCopy() {return new BoolValue(val);}
}
