package Domain.Statements;

import Exceptions.CustomException;
import Domain.PrgState;

public class NopStmt implements IStmt {
    @Override
    public String toString() {
        return "(Nop)";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
