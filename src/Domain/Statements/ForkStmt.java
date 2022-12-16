package Domain.Statements;

import Domain.MyADTs.*;
import Domain.Values.StringValue;
import Exceptions.CustomException;
import Exceptions.TypeException;
import Domain.Expressions.Exp;
import Domain.PrgState;
import Domain.Types.BoolType;
import Domain.Values.BoolValue;
import Domain.Values.Value;

import java.io.BufferedReader;
import java.util.Map;
import java.util.Set;

public class ForkStmt implements IStmt {
    IStmt stmt;

    public ForkStmt(IStmt s) {
        stmt = s;
    }

    @Override
    public String toString() {
        return "(fork(" + stmt.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        MyITable<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIList<Value> out = state.getOut();

        // create the new program state
        MyIStack<IStmt> newStk = new MyStack<>(100);

        MyIDictionary<String, Value> newSymTbl = new MyDictionary<>();
        Set<Map.Entry<String, Value>> entrySet = symTbl.getEntrySet();
        for (Map.Entry<String, Value> t1T2Entry : entrySet) {
            newSymTbl.add(t1T2Entry.getKey(), t1T2Entry.getValue().deepCopy());
        }

        return new PrgState(newStk, newSymTbl, out, fileTable, heap, stmt);
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }
}
