package Domain.Statements;

import Domain.MyADTs.MyDictionary;
import Domain.MyADTs.MyIHeap;
import Domain.Types.Type;
import Exceptions.CustomException;
import Exceptions.TypeException;
import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIStack;
import Domain.PrgState;
import Domain.Types.BoolType;
import Domain.Values.BoolValue;
import Domain.Values.Value;

import java.util.Map;
import java.util.Set;

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
            throw new TypeException("conditional expression is not a boolean.\n");
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
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        Type typExp = exp.typecheck(typeEnv);
        if (typExp.equals(new BoolType())) {
            MyIDictionary<String, Type> newTypeEnv = new MyDictionary<>();
            Set<Map.Entry<String, Type>> entrySet = typeEnv.getEntrySet();
            for (Map.Entry<String, Type> t1T2Entry : entrySet) {
                newTypeEnv.add(t1T2Entry.getKey(), t1T2Entry.getValue().deepCopy());
            }
            stmt.typecheck(newTypeEnv);
            return typeEnv;
        }
        else {
            throw new TypeException("conditional expression is not a boolean.\n");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(exp.deepCopy(), stmt.deepCopy());
    }
}
