package Domain.Statements;

import Domain.Types.Type;
import Domain.Values.Value;

public class AssignStmt implements IStmt {

    String id;
    Exp exp;

    AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getSk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        stk.pop();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl);
            Type typeId = symTbl.lookup(id).getType();
            if ((val.getType()).equals(typeId)) {
                symTbl.update(id, val);
            }
            else throw new MyException("declared type of variable " + id + " and type of the assigned expression do not match.");
        }
        else {
            throw new MyException("the used variable " + id + " was not declared before");
        }
        return state;
    }
}
