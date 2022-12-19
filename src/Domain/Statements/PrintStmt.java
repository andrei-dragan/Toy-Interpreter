package Domain.Statements;

import Domain.MyADTs.MyIHeap;
import Domain.Types.Type;
import Exceptions.CustomException;
import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIList;
import Domain.PrgState;
import Domain.Values.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value v = exp.eval(symTbl, heap);
        out.pushBack(v);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}
