package Domain.Statements;

import Domain.MyADTs.MyIDictionary;
import Domain.Types.RefType;
import Domain.Types.Type;
import Exceptions.AssignmentException;
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
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
