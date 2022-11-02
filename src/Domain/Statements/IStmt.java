package Domain.Statements;

import Domain.Exceptions.CustomException;
import Domain.PrgState;

public interface IStmt {
    public PrgState execute(PrgState state) throws CustomException;
    public IStmt deepCopy();
}
