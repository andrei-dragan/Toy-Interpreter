package Domain.Statements;

import Domain.MyADTs.MyIHeap;
import Exceptions.AssignmentException;
import Exceptions.CustomException;
import Exceptions.KeyNotInDictException;
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
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl, heap);
            Type typeId = symTbl.lookup(id).getType();
            if ((val.getType()).equals(typeId)) {
                symTbl.update(id, val);
            }
            else throw new AssignmentException("declared type of variable " + id + " and type of the assigned expression do not match.");
        }
        else {
            throw new KeyNotInDictException("the used variable " + id + " was not declared before");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }
}
