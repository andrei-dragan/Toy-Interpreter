package Domain.Statements;

import Domain.Exceptions.CustomException;
import Domain.Exceptions.KeyInDictException;
import Domain.MyADTs.MyIDictionary;
import Domain.PrgState;
import Domain.Types.Type;
import Domain.Values.Value;

public class VarDeclStmt implements IStmt {
    String name;
    Type typ;

    public VarDeclStmt(String declName, Type declTyp) {
        name = declName;
        typ = declTyp;
    }

    @Override
    public String toString() {
        return "(" + typ.toString() + " " + name + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (symTbl.isDefined(name)) throw new KeyInDictException("variable already declared.\n");
        symTbl.add(name, typ.getDefault());
        return state;
    }
}