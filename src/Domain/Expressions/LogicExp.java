package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Domain.Types.IntType;
import Domain.Types.Type;
import Exceptions.CustomException;
import Exceptions.TypeException;
import Domain.MyADTs.MyIDictionary;
import Domain.Types.BoolType;
import Domain.Values.BoolValue;
import Domain.Values.Value;
import java.util.Objects;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    String op;

    public LogicExp(String opSign, Exp exp1, Exp exp2) {
        e1 = exp1;
        e2 = exp2;
        op = opSign;
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws CustomException {
        Value v1 = e1.eval(tbl, hp);
        if (!v1.getType().equals(new BoolType())) throw new TypeException("first operand is not a boolean.\n");

        Value v2 = e2.eval(tbl, hp);
        if (!v2.getType().equals(new BoolType()))  throw new TypeException("second operand is not a boolean.\n");

        BoolValue b1 = (BoolValue)v1;
        BoolValue b2 = (BoolValue)v2;

        boolean n1 = b1.getVal();
        boolean n2 = b2.getVal();

        if (Objects.equals(op, "and")) return new BoolValue(n1 && n2);
        else return new BoolValue(n1 || n2);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            }
            else {
                throw new TypeException("second operand is not a boolean.\n");
            }
        }
        else {
            throw new TypeException("first operand is not a boolean.\n");
        }
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(op, e1.deepCopy(), e2.deepCopy());
    }
}
