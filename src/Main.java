import Controller.Controller;
import Domain.Exceptions.CustomException;
import Domain.Expressions.ArithExp;
import Domain.Expressions.ValueExp;
import Domain.Expressions.VarExp;
import Domain.MyADTs.*;
import Domain.PrgState;
import Domain.Statements.*;
import Domain.Types.BoolType;
import Domain.Types.IntType;
import Domain.Values.BoolValue;
import Domain.Values.IntValue;
import Domain.Values.Value;
import Repository.IRepo;
import Repository.Repo;
import View.ExitCommand;
import View.RunCommand;
import View.TextMenu;

class Interpreter {
    TextMenu menu;
    public Interpreter() throws CustomException {
        // first one
        IStmt ex1 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))
                )
        );
        MyIStack<IStmt> stk1 = new MyStack<IStmt>(100);
        MyIDictionary<String, Value> symTbl1 = new MyDictionary<String,Value>();
        MyIList<Value> out1 = new MyList<Value>();
        PrgState prgState1 = new PrgState(stk1, symTbl1, out1, ex1);
        IRepo repo1 = new Repo(prgState1);
        Controller controller1 = new Controller(repo1);

        // second one
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
        MyIStack<IStmt> stk2 = new MyStack<IStmt>(100);
        MyIDictionary<String, Value> symTbl2 = new MyDictionary<String,Value>();
        MyIList<Value> out2 = new MyList<Value>();
        PrgState prgState2 = new PrgState(stk2, symTbl2, out2, ex2);
        IRepo repo2 = new Repo(prgState2);
        Controller controller2 = new Controller(repo2);

        // third one
        IStmt ex3 = new CompStmt(
                new VarDeclStmt("a", new BoolType()),
                new CompStmt(
                        new VarDeclStmt("v", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("a"),
                                                new AssignStmt("v", new ValueExp(new IntValue(2))),
                                                new AssignStmt("v", new ValueExp(new IntValue(3)))
                                        ),
                                        new PrintStmt(new VarExp("v"))
                                )
                        )
                )
        );
        MyIStack<IStmt> stk3 = new MyStack<IStmt>(100);
        MyIDictionary<String, Value> symTbl3 = new MyDictionary<String,Value>();
        MyIList<Value> out3 = new MyList<Value>();
        PrgState prgState3 = new PrgState(stk3, symTbl3, out3, ex3);
        IRepo repo3 = new Repo(prgState3);
        Controller controller3 = new Controller(repo3);

        menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunCommand("1", ex1.toString(), controller1));
        menu.addCommand(new RunCommand("2", ex2.toString(), controller2));
        menu.addCommand(new RunCommand("3", ex3.toString(), controller3));
    }

    public void run() throws CustomException {
        menu.show();
    }
}

public class Main {
    public static void main(String[] args) throws CustomException {
        Interpreter interpreter = new Interpreter();
        interpreter.run();
    }
}