package Domain.Values;

import Domain.Types.IntType;
import Domain.Types.Type;

public class IntValue implements Value {
    int val;
    public IntValue(int v) {val = v;}

    public int getVal() {return val;}
    @Override
    public String toString() {
        // not sure if that is what is expected to be printed
        return Integer.toString(val);
    }

    @Override
    public Type getType() {return new IntType();}

    @Override
    public Value deepCopy() {return new IntValue(val);}
}
