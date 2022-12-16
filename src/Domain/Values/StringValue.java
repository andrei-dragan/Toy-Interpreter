package Domain.Values;

import Domain.Types.IntType;
import Domain.Types.StringType;
import Domain.Types.Type;

public class StringValue implements Value {
    String val;
    public StringValue(String v) {val = v;}

    @Override
    public boolean equals(Object another) { return another instanceof StringValue; }

    public String getVal() {return val;}

    @Override
    public String toString() {
        return val;
    }

    @Override
    public Type getType() {return new StringType();}

    @Override
    public Value deepCopy() {return new StringValue(val);}
}
