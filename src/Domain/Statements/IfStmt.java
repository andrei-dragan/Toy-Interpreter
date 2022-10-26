package Domain.Statements;

import Domain.Values.Value;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString() {
        return "(IF (" + exp.toString() + ") THEN (" + thenS.toString() + ") ELSE (" + elseS.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getSk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        stk.pop();

        Value cond = exp.eval(symTbl);
        if (cond.getType() != bool) {
            throw new MyException("conditional expr is not a boolean");
        }
        else {
            if (cond == True) stk.push(thenS);
            else stk.push(elseS);
        }

        return state;
    }
}
