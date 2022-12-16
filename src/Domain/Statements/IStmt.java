package Domain.Statements;

import Exceptions.CustomException;
import Domain.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws CustomException;
    IStmt deepCopy();
}
