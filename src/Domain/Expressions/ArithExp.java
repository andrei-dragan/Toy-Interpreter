package Domain.Expressions;

import Domain.Expressions.Exp;
import Domain.Types.IntType;
import Domain.Values.IntValue;
import Domain.Values.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op;

    ArithExp(char opSign, Exp exp1, Exp exp2) {
        e1 = exp1;
        e2 = exp2;
        if (opSign == '+') op = 1;
        else if (opSign == '-') op = 2;
        else if (opSign == '*') op = 3;
        else op = 4;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);

            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4) {
                    if (n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
                }
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");

        return new IntValue(0);
    }
}
