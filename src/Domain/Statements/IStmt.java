package Domain.Statements;

import Domain.MyADTs.MyIDictionary;
import Domain.Types.Type;
import Exceptions.CustomException;
import Domain.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws CustomException;

    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException;

    IStmt deepCopy();
}
