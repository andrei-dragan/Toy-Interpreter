package Domain.Expressions;

import Domain.Exceptions.CustomException;
import Domain.Exceptions.EvalVarException;
import Domain.Exceptions.KeyNotInDictException;
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
    public Value eval(MyIDictionary<String, Value> tbl) throws CustomException {
        return tbl.lookup(id);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }
}
