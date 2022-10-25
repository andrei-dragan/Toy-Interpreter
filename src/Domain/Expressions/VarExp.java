package Domain.Expressions;

import Domain.Values.Value;

public class VarExp implements Exp {
    String id;

    VarExp(String varId) {
        id = varId;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return tbl.lookup(id);
    }

}
