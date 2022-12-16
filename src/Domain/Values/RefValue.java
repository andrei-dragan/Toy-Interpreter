package Domain.Values;

import Domain.Types.IntType;
import Domain.Types.RefType;
import Domain.Types.Type;

public class RefValue implements Value {
    int address;
    Type locationType;

    public RefValue(int addr, Type lType) {
        address = addr;
        locationType = lType;
    }

    @Override
    public boolean equals(Object another) { return another instanceof RefValue; }

    public int getAddress() { return address; }

    @Override
    public String toString() {
        return "(" + Integer.toString(address) + ", " + locationType.toString() +  ")";
    }

    @Override
    public Type getType() { return new RefType(locationType); }

    @Override
    public Value deepCopy() { return new RefValue(address, locationType.deepCopy()); }
}
