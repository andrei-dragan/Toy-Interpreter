package Domain;

import Domain.Exceptions.CustomException;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIList;
import Domain.MyADTs.MyIStack;
import Domain.Statements.IStmt;
import Domain.Values.Value;

import java.util.Stack;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg) throws CustomException {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        exeStack.push(prg);
    }

    @Override
    public String toString() {
        return exeStack.toString() + symTable.toString() + out.toString() +  "\n\n";
    }

    public MyIStack<IStmt> getStk() {return exeStack;}
    public MyIDictionary<String, Value> getSymTable() {return symTable;}
    public MyIList<Value> getOut() {return out;}

    public void setStk(MyIStack<IStmt> newStack) {exeStack = newStack;}
    public void setSymTable(MyIDictionary<String, Value> newSymTable) {symTable = newSymTable;}
    public void setOut(MyIList<Value> newOut) {out = newOut;}
}
