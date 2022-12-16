package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Domain.Types.BoolType;
import Domain.Types.RefType;
import Domain.Values.RefValue;
import Exceptions.AddressNotInHeapException;
import Exceptions.CustomException;
import Domain.MyADTs.MyIDictionary;
import Domain.Values.Value;
import Exceptions.TypeException;

public class HeapReadExp implements Exp {
    Exp exp;

    public HeapReadExp(Exp e) {
        exp = e;
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws CustomException {
        Value val = exp.eval(tbl, hp);
        if (val.getType().equals(new RefType())) {
            RefValue refVal = (RefValue) val;
            int addr = refVal.getAddress();
            if (hp.isDefined(addr)) {
                return hp.lookup(addr);
            }
            else throw new AddressNotInHeapException("address of expression could not be found in heap");
        }
        else throw new TypeException(exp.toString() + " is not evaluated to a RefType");
    }

    @Override
    public Exp deepCopy() {
        return new HeapReadExp(exp.deepCopy());
    }
}
