package Domain.Statements;

import Domain.Expressions.Exp;
import Domain.MyADTs.MyIDictionary;
import Domain.MyADTs.MyIHeap;
import Domain.MyADTs.MyITable;
import Domain.PrgState;
import Domain.Types.IntType;
import Domain.Types.StringType;
import Domain.Types.Type;
import Domain.Values.IntValue;
import Domain.Values.StringValue;
import Domain.Values.Value;
import Exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {

    Exp exp;
    String varName;

    public ReadFile(Exp fexp, String fVarName) {
        exp = fexp;
        varName = fVarName;
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyITable<StringValue, BufferedReader> fileTbl = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        // check varName
        if (!symTbl.isDefined(varName)) throw new KeyNotInDictException("variable was not declared\n");
        Value v = symTbl.lookup(varName);
        if (!v.getType().equals(new IntType())) throw new TypeException("variable is not an integer.\n");

        // evaluate the expression
        Value expValue = exp.eval(symTbl, heap);
        if (!expValue.getType().equals(new StringType())) throw new FileNameException("invalid type for the name of the file.\n");

        // get the BufferedReader
        StringValue fileName = (StringValue) expValue;
        if (!fileTbl.isDefined(fileName)) throw new KeyInTableException("file is not opened.\n");
        try {
            BufferedReader reader = fileTbl.lookup(fileName);
            String line = reader.readLine();
            if (line == null) symTbl.update(varName, new IntType().defaultValue());
            else symTbl.update(varName, new IntValue(Integer.parseInt(line)));
        }
        catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        };

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        Type typeExp = exp.typecheck(typeEnv);
        Type typeVarName = typeEnv.lookup(varName);
        if (typeExp.equals(new StringType())) {
            if (typeVarName.equals(new IntType())) return typeEnv;
            else throw new TypeException("variable is not an integer.\n");
        }
        else {
            throw new FileNameException("invalid type for the name of the file.\n");
        }
    }

    @Override
    public IStmt deepCopy() {return new ReadFile(exp.deepCopy(), varName);}

    @Override
    public String toString() {return "readFile(" + exp.toString() + ", " + varName + ")";}
}
