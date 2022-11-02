package Domain.Values;

import Domain.Types.BoolType;
import Domain.Types.Type;

public class BoolValue implements Value {
    Boolean val;
    public BoolValue(Boolean v) {val = v;}

    public Boolean getVal() {return val;}
    @Override
    public String toString() {
        // not sure if that is what is expected to be printed
        return Boolean.toString(val);
    }

    @Override
    public Type getType() {return new BoolType();}

    @Override
    public Value deepCopy() {return new BoolValue(val);}
}
