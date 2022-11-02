package Domain.Statements;

import Domain.Exceptions.CustomException;
import Domain.PrgState;

public interface IStmt {
    // execution method for a statement.
    public PrgState execute(PrgState state) throws CustomException;
}
