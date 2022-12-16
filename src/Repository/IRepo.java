package Repository;

import Domain.PrgState;
import Exceptions.CustomException;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newPrgStates);
    void logPrgStateExec(PrgState state) throws CustomException;
    void logGenericPrgState() throws  CustomException;
    void updateLogFilePath(String newLogFilePath);
}
