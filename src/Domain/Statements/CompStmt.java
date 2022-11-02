package Domain.Statements;

import Domain.Exceptions.CustomException;
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
        return state;
    }
}
