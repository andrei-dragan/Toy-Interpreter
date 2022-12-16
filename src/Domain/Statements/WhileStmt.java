package Domain.Statements;

import Domain.MyADTs.MyIHeap;
import Exceptions.CustomException;
import Exceptions.TypeException;
import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIStack;
import Domain.PrgState;
import Domain.Types.BoolType;
import Domain.Values.BoolValue;
import Domain.Values.Value;

public class WhileStmt implements IStmt {
    Exp exp;
    IStmt stmt;

    public WhileStmt(Exp e, IStmt stm) {
        exp = e;
        stmt = stm;
    }

    @Override
    public String toString() {
        return "(while (" + exp.toString() + ")" + stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value cond = exp.eval(symTbl, heap);
        if (!cond.getType().equals(new BoolType())) {
            throw new TypeException("conditional expr is not a boolean");
        }
        else {
            BoolValue v = (BoolValue) cond;
            if (v.getVal()) {
                stk.push(this);
                stk.push(stmt);
            }
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(exp.deepCopy(), stmt.deepCopy());
    }
}
