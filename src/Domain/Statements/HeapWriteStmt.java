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
import Exceptions.*;

public class HeapWriteStmt implements IStmt {
    String id;
    Exp exp;

    public HeapWriteStmt(String varName, Exp e) {
        id = varName;
        exp = e;
    }

    @Override
    public String toString() {
        return "(wH(" + id + ", " + exp.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTbl.isDefined(id)) {
            // get the type of id
            Value ref = symTbl.lookup(id);
            if (ref.getType().equals(new RefType())) {
                RefValue refVal = (RefValue) ref;
                int addr = refVal.getAddress();
                if (heap.isDefined(addr)) {
                    Value val = exp.eval(symTbl, heap);
                    if (((RefType) ref.getType()).getInner().equals(val.getType())) {
                        heap.update(addr, val);
                    }
                    else throw new AssignmentException("declared type of variable " + id + " and type of the assigned expression do not match.");
                }
                else throw new AddressNotInHeapException("address of " + id + " could not be found in heap");
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
        return new HeapWriteStmt(id, exp.deepCopy());
    }
}
