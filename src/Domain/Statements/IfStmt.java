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
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value cond = exp.eval(symTbl, heap);
        if (!cond.getType().equals(new BoolType())) {
            throw new TypeException("conditional expression is not a boolean.\n");
        }
        else {
            BoolValue v = (BoolValue) cond;
            if (v.getVal()) stk.push(thenS);
            else stk.push(elseS);
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        Type typExp = exp.typecheck(typeEnv);
        if (typExp.equals(new BoolType())) {

            MyIDictionary<String, Type> typeEnv1 = new MyDictionary<>();
            Set<Map.Entry<String, Type>> entrySet1 = typeEnv.getEntrySet();
            for (Map.Entry<String, Type> t1T2Entry : entrySet1) {
                typeEnv1.add(t1T2Entry.getKey(), t1T2Entry.getValue().deepCopy());
            }

            MyIDictionary<String, Type> typeEnv2 = new MyDictionary<>();
            Set<Map.Entry<String, Type>> entrySet2 = typeEnv.getEntrySet();
            for (Map.Entry<String, Type> t1T2Entry : entrySet2) {
                typeEnv2.add(t1T2Entry.getKey(), t1T2Entry.getValue().deepCopy());
            }

            thenS.typecheck(typeEnv1);
            elseS.typecheck(typeEnv2);

            return typeEnv;
        }
        else {
            throw new TypeException("conditional expression is not a boolean.\n");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
