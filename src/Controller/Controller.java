package Controller;

import Domain.Exceptions.CustomException;
import Domain.Exceptions.EmptyStackException;
import Domain.MyADTs.MyIStack;
import Domain.PrgState;
import Domain.Statements.IStmt;
import Repository.IRepo;

public class Controller {
    IRepo repo;

    public Controller(IRepo rep) {
        repo = rep;
    }

    public PrgState oneStep(PrgState state) throws CustomException {
        MyIStack<IStmt> stk = state.getStk();
        if (stk.isEmpty()) throw new EmptyStackException("stack is empty.\n");
        IStmt stmt = stk.pop();
        return stmt.execute(state);
    }

    public void allStep() throws CustomException {
        PrgState prgState = repo.getCrtPrg();
        System.out.println(prgState.toString());
        while(!prgState.getStk().isEmpty()) {
            oneStep(prgState);
            System.out.println(prgState.toString());
        }
    }
}
