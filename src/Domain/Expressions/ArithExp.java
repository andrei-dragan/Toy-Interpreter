package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Domain.Types.Type;
import Exceptions.CustomException;
import Exceptions.CustomIOException;
import Exceptions.DivisionByZeroException;
import Exceptions.TypeException;
import Domain.MyADTs.MyIDictionary;
import Domain.Types.IntType;
import Domain.Values.IntValue;
import Domain.Values.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    char op;

    public ArithExp(char opSign, Exp exp1, Exp exp2) {
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
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if (!v1.getType().equals(new IntType())) throw new TypeException("first operand is not an integer.\n");

        v2 = e2.eval(tbl, hp);
        if (!v2.getType().equals(new IntType())) throw new TypeException("second operand is not an integer.\n");

        IntValue i1 = (IntValue)v1;
        IntValue i2 = (IntValue)v2;
        int n1, n2;
        n1 = i1.getVal();
        n2 = i2.getVal();
        if (op == '+') return new IntValue(n1 + n2);
        else if (op == '-') return new IntValue(n1 - n2);
        else if (op == '*') return new IntValue(n1 * n2);
        else {
            if (n2 == 0) throw new DivisionByZeroException("division by zero.\n");
            return new IntValue(n1 / n2);
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws CustomException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            }
            else {
                throw new TypeException("second operand is not an integer.\n");
            }
        }
        else {
            throw new TypeException("first operand is not an integer.\n");
        }
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(op, e1.deepCopy(), e2.deepCopy());
    }
}
