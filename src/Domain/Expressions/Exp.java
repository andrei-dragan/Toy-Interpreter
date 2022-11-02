package Domain.Expressions;

import Domain.Exceptions.CustomException;
import Domain.MyADTs.MyIDictionary;
import Domain.Values.Value;

public interface Exp {
    public Value eval(MyIDictionary<String, Value> tbl) throws CustomException;
    public Exp deepCopy();
}
