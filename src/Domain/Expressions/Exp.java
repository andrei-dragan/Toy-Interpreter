package Domain.Expressions;

import Domain.Values.Value;

public interface Exp {
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException;
}
