package Domain.Types;

import Domain.Values.IntValue;
import Domain.Values.RefValue;
import Domain.Values.Value;

public class RefType implements Type {
    Type inner;

    public RefType(Type innerType) {
        inner = innerType;
    }
    public RefType() {inner = null;}

    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object another) {
       if (another instanceof RefType) {
           return (((RefType) another).getInner() == null || inner == null || (inner.equals(((RefType) another).getInner())));
       }
       return false;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public Type deepCopy() {return new RefType(inner.deepCopy());}
}
