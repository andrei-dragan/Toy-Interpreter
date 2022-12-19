package Domain.Statements;

import Domain.MyADTs.MyIDictionary;
import Domain.Types.Type;
import Exceptions.CustomException;
import Domain.MyADTs.MyIStack;
import Domain.PrgState;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt firstStmt, IStmt secondStmt) {
        first = firstStmt;
        snd = secondStmt;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + "; " + snd.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        return snd.typecheck(first.typecheck(typeEnv));
    }


    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), snd.deepCopy());
    }
}
