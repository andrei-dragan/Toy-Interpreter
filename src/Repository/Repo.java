package Repository;

import Domain.PrgState;
import Exceptions.CustomException;
import Exceptions.CustomIOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repo implements IRepo {
    List<PrgState> prgStates;
    String logFilePath;

    public Repo(PrgState repoState, String repoLogFilePath) {
        prgStates = new ArrayList<>();
        prgStates.add(repoState);

        logFilePath = repoLogFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {return prgStates;}

    @Override
    public void setPrgList(List<PrgState> newPrgStates) {
        prgStates.clear();
        prgStates.addAll(newPrgStates);
    }

    @Override
    public void logPrgStateExec(PrgState state) throws CustomException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            logFile.print(state.toString());
        }
        catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    public void logGenericPrgState() throws  CustomException {
        if (prgStates.size() > 0) {
            try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
                logFile.print(prgStates.get(0).genericStateToString());
                logFile.print("\n\n");
            } catch (IOException e) {
                throw new CustomIOException(e.getMessage());
            }
        }
    }

    @Override
    public void updateLogFilePath(String newLogFilePath) {
        logFilePath = newLogFilePath;
    }
}
