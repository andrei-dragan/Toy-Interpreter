package Domain;

public interface IStmt {
    // execution method for a statement.
    public PrgState execute(PrgState state) throws MyException;
}
