package View;

import Controller.Controller;
import Domain.Expressions.ArithExp;
import Domain.Expressions.ValueExp;
import Domain.Expressions.VarExp;
import Domain.MyADTs.*;
import Domain.PrgState;
import Domain.Statements.*;
import Domain.Types.IntType;
import Domain.Values.IntValue;
import Domain.Values.Value;
import Repository.IRepo;
import Repository.Repo;

public class View {

    public void run() {
        IStmt ex1 = new CompStmt(
                        new VarDeclStmt("v", new IntType()),
                        new CompStmt(
                                new AssignStmt("v", new ValueExp(new IntValue(2))),
                                new PrintStmt(new VarExp("v"))
                        )
                    );

        IStmt ex2 = new CompStmt(
                        new VarDeclStmt("a", new IntType()),
                        new CompStmt(
                                new VarDeclStmt("b", new IntType()),
                                new CompStmt(
                                        new AssignStmt("a",
                                                new ArithExp('+',
                                                        new ValueExp(new IntValue(2)),
                                                        new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)))
                                                )
                                        ),
                                        new CompStmt(
                                                new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                                                new PrintStmt(new VarExp("b"))
                                        )
                                )
                        )
                    );

        MyIStack<IStmt> stk = new MyStack<IStmt>(100);
        MyIDictionary<String, Value> symTbl = new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();

        try {
            PrgState prgState = new PrgState(stk, symTbl, out, ex2);
            IRepo repo = new Repo(prgState);
            Controller controller = new Controller(repo);

            controller.allStep();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
