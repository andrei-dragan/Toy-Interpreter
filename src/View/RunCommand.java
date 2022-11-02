package View;

import Controller.Controller;
import Domain.Exceptions.CustomException;

public class RunCommand extends Command {
    private Controller ctr;
    public RunCommand(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() throws CustomException {
        ctr.allStep();
    }
}
