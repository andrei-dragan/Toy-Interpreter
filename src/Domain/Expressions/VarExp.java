package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Domain.Types.Type;
import Exceptions.CustomException;
import Domain.MyADTs.MyIDictionary;
import Domain.Values.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String varId) {
        id = varId;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws CustomException {
        return tbl.lookup(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        return typeEnv.lookup(id);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }
}
