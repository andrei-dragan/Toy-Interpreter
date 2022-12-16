import Controller.Controller;
import Domain.Expressions.*;
import Domain.MyADTs.*;
import Domain.PrgState;
import Domain.Statements.*;
import Domain.Types.BoolType;
import Domain.Types.IntType;
import Domain.Types.RefType;
import Domain.Types.StringType;
import Domain.Values.BoolValue;
import Domain.Values.IntValue;
import Domain.Values.StringValue;
import Domain.Values.Value;
import Exceptions.CustomException;
import Repository.IRepo;
import Repository.Repo;
import View.ExitCommand;
import View.RunCommand;
import View.TextMenu;

import java.io.BufferedReader;
import java.util.ArrayList;

class Interpreter {
    TextMenu menu;
    public Interpreter() throws CustomException {
        ArrayList<IStmt> stmts = new ArrayList<>();

        // ex1
        stmts.add(
            new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                    new AssignStmt("v", new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarExp("v"))
                )
            )
        );

        // ex2
        stmts.add(
            new CompStmt(
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
            )
        );

        // ex3
        stmts.add(
            new CompStmt(
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
            )
        );

        // ex4
        stmts.add(
            new CompStmt(
                new VarDeclStmt("varf", new StringType()),
                new CompStmt(
                    new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                    new CompStmt(
                        new OpenRFile(new VarExp("varf")),
                        new CompStmt(
                            new VarDeclStmt("varc", new IntType()),
                            new CompStmt(
                                new CompStmt(
                                    new CompStmt(
                                        new ReadFile(new VarExp("varf"), "varc"),
                                        new PrintStmt(new VarExp("varc"))
                                    ),
                                    new CompStmt(
                                        new ReadFile(new VarExp("varf"), "varc"),
                                        new PrintStmt(new VarExp("varc"))
                                    )
                                ),
                                new CloseRFile(new VarExp("varf"))
                            )
                        )
                    )
                )
            )
        );

        // ex5
        stmts.add(
            new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                    new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(
                        new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                        new CompStmt(
                            new HeapAllocStmt("a", new VarExp("v")),
                            new CompStmt(
                                new PrintStmt(new VarExp("v")),
                                new PrintStmt(new VarExp("a"))
                            )
                        )
                    )
                )
            )
        );

        // ex6
        stmts.add(
            new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                    new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(
                        new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                        new CompStmt(
                            new HeapAllocStmt("a", new VarExp("v")),
                            new CompStmt(
                                new PrintStmt(new HeapReadExp(new VarExp("v"))),
                                new PrintStmt(new ArithExp('+', new HeapReadExp(new HeapReadExp(new VarExp("a"))), new ValueExp(new IntValue(5))))
                            )
                        )
                    )
                )
            )
        );

        // ex7
        stmts.add(
            new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                    new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(
                        new PrintStmt(new HeapReadExp(new VarExp("v"))),
                        new CompStmt(
                            new HeapWriteStmt("v", new ValueExp(new IntValue(30))),
                            new PrintStmt(new ArithExp('+', new HeapReadExp(new VarExp("v")), new ValueExp(new IntValue(5))))
                        )
                    )
                )
            )
        );

        // ex8
        stmts.add(
            new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                    new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(
                        new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                        new CompStmt(
                            new CompStmt(
                                new HeapAllocStmt("a", new VarExp("v")),
                                new HeapAllocStmt("v", new ValueExp(new IntValue(30)))
                            ),
                            new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a"))))
                        )
                    )
                )
            )
        );

        // ex9
        stmts.add(
            new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                    new AssignStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(
                        new WhileStmt(
                            new RelExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                            new CompStmt(
                                new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))
                            )
                        ),
                        new PrintStmt(new VarExp("v"))
                    )
                )
            )
        );

        // ex10
        stmts.add(
            new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                    new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(10))),
                        new CompStmt(
                            new HeapAllocStmt("a", new ValueExp(new IntValue(22))),
                            new CompStmt(
                                new ForkStmt(
                                    new CompStmt(
                                        new HeapWriteStmt("a", new ValueExp(new IntValue(30))),
                                        new CompStmt(
                                            new AssignStmt("v", new ValueExp(new IntValue(32))),
                                            new CompStmt(
                                                    new PrintStmt(new VarExp("v")),
                                                    new PrintStmt(new HeapReadExp(new VarExp("a")))
                                            )
                                        )
                                    )
                                ),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new PrintStmt(new HeapReadExp(new VarExp("a")))
                                )
                            )
                        )
                    )
                )
            )
        );

        int totalExamples  = 10;

        ArrayList<MyIStack<IStmt>> stacks = new ArrayList<>();
        ArrayList<MyIDictionary<String, Value>> symTbls = new ArrayList<>();
        ArrayList<MyIList<Value>> outs = new ArrayList<>();
        ArrayList<MyITable<StringValue, BufferedReader>> fileTables = new ArrayList<>();
        ArrayList<MyIHeap<Integer, Value>> heaps = new ArrayList<>();
        ArrayList<PrgState> prgStates = new ArrayList<>();
        ArrayList<IRepo> repos = new ArrayList<>();
        ArrayList<Controller> controllers = new ArrayList<>();

        menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        for (int i = 0; i < totalExamples; i++) {
            stacks.add(new MyStack<>(100));
            symTbls.add(new MyDictionary<>());
            outs.add(new MyList<>());
            fileTables.add(new MyTable<>());
            heaps.add(new MyHeap<>());
            prgStates.add(new PrgState(stacks.get(i), symTbls.get(i), outs.get(i), fileTables.get(i), heaps.get(i), stmts.get(i)));
            repos.add(new Repo(prgStates.get(i), "log" + Integer.toString(i + 1) + ".txt"));
            controllers.add(new Controller(repos.get(i)));

            menu.addCommand(new RunCommand(Integer.toString(i + 1), stmts.get(i).toString(), controllers.get(i)));
        }
    }

    public void run() throws CustomException, InterruptedException {
        menu.show();
    }

    public static void main(String[] args) throws CustomException, InterruptedException {
        Interpreter interpreter = new Interpreter();
        interpreter.run();
    }
}