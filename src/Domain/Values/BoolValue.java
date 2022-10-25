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
        return "bool " + getVal();
    }

    @Override
    public Type getType() {return new BoolType();}
}
