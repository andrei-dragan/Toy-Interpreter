package Domain.Expressions;

import Domain.Values.Value;

public class ValueExp implements Exp {
    Value e;

    public ValueExp(Value elem) {
        e = elem;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return e;
    }
}
