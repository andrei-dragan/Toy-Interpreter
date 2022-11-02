package Domain.Expressions;

import Domain.Exceptions.CustomException;
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
    public Value eval(MyIDictionary<String, Value> tbl) throws CustomException {
        return e;
    }
}
