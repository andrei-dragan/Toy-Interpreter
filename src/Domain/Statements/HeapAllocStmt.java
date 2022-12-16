package Domain.Statements;

import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIHeap;
import Domain.PrgState;
import Domain.Types.BoolType;
import Domain.Types.RefType;
import Domain.Types.StringType;
import Domain.Values.RefValue;
import Domain.Values.Value;
import Exceptions.AssignmentException;
import Exceptions.CustomException;
import Exceptions.KeyNotInDictException;
import Exceptions.TypeException;

public class HeapAllocStmt implements IStmt {
    String id;
    Exp exp;

    public HeapAllocStmt(String varName, Exp e) {
        id = varName;
        exp = e;
    }

    @Override
    public String toString() {
        return "(new(" + id + ", " + exp.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTbl.isDefined(id)) {
            // get the type of id
            Value ref = symTbl.lookup(id);
            if (ref.getType().equals(new RefType())) {
                Value val = exp.eval(symTbl, heap);
                if (((RefType) ref.getType()).getInner().equals(val.getType())) {
                    int addr = heap.add(val);
                    symTbl.update(id, new RefValue(addr, val.getType()));
                }
                else throw new AssignmentException("declared type of variable " + id + " and type of the assigned expression do not match.");
            }
            else throw new TypeException(id + " is not a variable of RefType");
        }
        else {
            throw new KeyNotInDictException("the used variable " + id + " was not declared before");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocStmt(id, exp.deepCopy());
    }
}
