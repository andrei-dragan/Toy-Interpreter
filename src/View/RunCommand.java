package View;

import Controller.Controller;
import Exceptions.CustomException;
import Repository.IRepo;

public class RunCommand extends Command {
    private Controller ctr;
    public RunCommand(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() throws CustomException, InterruptedException {
        ctr.allStep();
    }

    public void updateLogFilePath(String logFilePath) {
        IRepo repo = ctr.getRepo();
        repo.updateLogFilePath(logFilePath);
    }
}
