package Domain.Expressions;

import Domain.Types.BoolType;
import Domain.Values.BoolValue;
import Domain.Values.Value;
import java.util.Objects;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op;

    public LogicExp(String opSign, Exp exp1, Exp exp2) {
        e1 = exp1;
        e2 = exp2;
        if (Objects.equals(opSign, "and")) op = 1;
        if (Objects.equals(opSign, "or")) op = 2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1 = e1.eval(tbl);
        if (v1.getType().equals(new BoolType())) {
            Value v2 = e2.eval(tbl);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;

                Boolean n1 = b1.getVal();
                Boolean n2 = b2.getVal();

                if (op == 1) return new BoolValue(n1 && n2);
                if (op == 2) return new BoolValue(n1 || n2);
            }
            else throw new MyException("Operand2 is not a boolean");
        }
        else throw new MyException("Operand1 is not a boolean");

        return new BoolValue(false);
    }
}
