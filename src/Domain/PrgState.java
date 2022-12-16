package Domain;

import Domain.MyADTs.*;
import Domain.Values.StringValue;
import Exceptions.CustomException;
import Domain.Statements.IStmt;
import Domain.Values.Value;
import Exceptions.EmptyStackException;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyITable<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer, Value> heap;
    IStmt originalProgram;

    int id;
    static int globalID = 0;

    synchronized static int getNextId() {
        globalID++;
        return globalID;
    }


    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, MyITable<StringValue, BufferedReader> fileTbl, MyIHeap<Integer, Value> hp, IStmt prg) throws CustomException {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = fileTbl;
        heap = hp;
        originalProgram = prg.deepCopy();
        exeStack.push(prg);

        id = getNextId();
    }

    public String genericStateToString () {
        return out.toString() + fileTable.toString() + heap.toString();
    }

    @Override
    public String toString() {
        return "Id = " + id + '\n' + exeStack.toString() + symTable.toString() + '\n';
    }

    public MyIStack<IStmt> getStk() {return exeStack;}
    public MyIDictionary<String, Value> getSymTable() {return symTable;}
    public MyIList<Value> getOut() {return out;}
    public MyITable<StringValue, BufferedReader> getFileTable() {return fileTable;}
    public MyIHeap<Integer, Value> getHeap() {return heap;}

    public void setStk(MyIStack<IStmt> newStack) {exeStack = newStack;}
    public void setSymTable(MyIDictionary<String, Value> newSymTable) {symTable = newSymTable;}
    public void setOut(MyIList<Value> newOut) {out = newOut;}
    public void setFileTable(MyITable<StringValue, BufferedReader> newFileTable) {fileTable = newFileTable;}
    public void setHeap(MyIHeap<Integer, Value> newHeap) {heap = newHeap;}

    public Boolean isNotCompleted() { return (!exeStack.isEmpty()); }

    public PrgState oneStep() throws CustomException {
        if (exeStack.isEmpty()) throw new EmptyStackException("stack is empty.\n");
        IStmt stmt = exeStack.pop();
        return stmt.execute(this);
    }
}
