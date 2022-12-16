package Domain.Expressions;

import Domain.MyADTs.MyIHeap;
import Domain.Values.BoolValue;
import Exceptions.CustomException;
import Exceptions.TypeException;
import Domain.MyADTs.MyIDictionary;
import Domain.Types.IntType;
import Domain.Values.IntValue;
import Domain.Values.Value;

import java.util.Objects;

public class RelExp implements Exp {
    Exp e1;
    Exp e2;
    String op;

    public RelExp(String opSign, Exp exp1, Exp exp2) {
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
        if (Objects.equals(op, "<")) return new BoolValue(n1 < n2);
        else if (Objects.equals(op, "<=")) return new BoolValue(n1 <= n2);
        else if (Objects.equals(op, "==")) return new BoolValue(n1 == n2);
        else if (Objects.equals(op, "!=")) return new BoolValue(n1 != n2);
        else if (Objects.equals(op, ">")) return new BoolValue(n1 > n2);
        else return new BoolValue(n1 >= n2);
    }

    @Override
    public Exp deepCopy() {
        return new RelExp(op, e1.deepCopy(), e2.deepCopy());
    }
}
