package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Domain.Types.Type;
import Exceptions.CustomException;
import Domain.MyADTs.MyIDictionary;
import Domain.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws CustomException;

    Type typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException;

    Exp deepCopy();
}
