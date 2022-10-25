package Domain;

import Domain.IStmt;

public class PrintStmt implements IStmt {
    Exp exp;

    PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();

        stk.pop();
        // also you need to add it to the output

        return state;
    }

}
