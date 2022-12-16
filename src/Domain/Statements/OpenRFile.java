package Domain.Statements;

import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIHeap;
import Domain.MyADTs.MyITable;
import Domain.PrgState;
import Domain.Types.StringType;
import Domain.Values.StringValue;
import Domain.Values.Value;
import Exceptions.CustomException;
import Exceptions.CustomIOException;
import Exceptions.FileNameException;
import Exceptions.KeyInTableException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OpenRFile implements IStmt {
    Exp exp;

    public OpenRFile(Exp fExp) {
        exp = fExp;
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyITable<StringValue, BufferedReader> fileTbl = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        // evaluate the expression and check its type
        Value expValue = exp.eval(symTbl, heap);
        if (!expValue.getType().equals(new StringType())) throw new FileNameException("invalid type for the name of the file.\n");

        // look for an already opened file
        StringValue fileName = (StringValue) expValue;
        if (fileTbl.isDefined(fileName)) throw new KeyInTableException("file already opened.\n");

        // create the BufferedReader
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName.getVal()));
            fileTbl.add(fileName, reader);
        }
        catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {return new OpenRFile(exp.deepCopy());}

    @Override
    public String toString() {return "openRFile(" + exp.toString() + ")";}
}
