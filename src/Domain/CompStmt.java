package Domain;

import Domain.IStmt;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    CompStmt(IStmt firstStmt, IStmt secondStmt) {
        this.first = firstStmt;
        this.snd = secondStmt;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + snd.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return state;
    }
}
