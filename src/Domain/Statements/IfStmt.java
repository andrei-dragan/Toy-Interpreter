package Domain.Statements;

import Domain.Exceptions.CustomException;
import Domain.Exceptions.TypeException;
import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIStack;
import Domain.PrgState;
import Domain.Types.BoolType;
import Domain.Values.BoolValue;
import Domain.Values.Value;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp ifExp, IStmt ifThenS, IStmt ifElseS) {
        exp = ifExp;
        thenS = ifThenS;
        elseS = ifElseS;
    }

    @Override
    public String toString() {
        return "(if (" + exp.toString() + ") then (" + thenS.toString() + ") else (" + elseS.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        Value cond = exp.eval(symTbl);
        if (!cond.getType().equals(new BoolType())) {
            throw new TypeException("conditional expr is not a boolean");
        }
        else {
            BoolValue v = (BoolValue) cond;
            if (v.getVal()) stk.push(thenS);
            else stk.push(elseS);
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
