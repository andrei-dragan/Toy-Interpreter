package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Domain.Types.Type;
import Exceptions.CustomException;
import Domain.MyADTs.MyIDictionary;
import Domain.Values.Value;

public class ValueExp implements Exp {
    Value e;

    public ValueExp(Value elem) {
        e = elem;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws CustomException {
        return e;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        return e.getType();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }
}
