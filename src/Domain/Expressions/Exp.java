package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Exceptions.CustomException;
import Domain.MyADTs.MyIDictionary;
import Domain.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws CustomException;
    Exp deepCopy();
}
