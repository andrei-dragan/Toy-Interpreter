package Domain.Statements;

import Domain.Exceptions.AssignmentException;
import Domain.Exceptions.CustomException;
import Domain.Exceptions.KeyNotInDictException;
import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIStack;
import Domain.PrgState;
import Domain.Types.Type;
import Domain.Values.Value;

public class AssignStmt implements IStmt {

    String id;
    Exp exp;

    public AssignStmt(String assignId, Exp assignExp) {
        id = assignId;
        exp = assignExp;
    }

    @Override
    public String toString() {
        return "(" + id + " = " + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl);
            Type typeId = symTbl.lookup(id).getType();
            if ((val.getType()).equals(typeId)) {
                symTbl.update(id, val);
            }
            else throw new AssignmentException("declared type of variable " + id + " and type of the assigned expression do not match.");
        }
        else {
            throw new KeyNotInDictException("the used variable " + id + " was not declared before");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }
}
