package Controller;

import Domain.Values.RefValue;
import Domain.Values.Value;
import Exceptions.CustomException;
import Exceptions.EmptyStackException;
import Domain.MyADTs.MyIStack;
import Domain.PrgState;
import Domain.Statements.IStmt;
import Repository.IRepo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class Controller {
    IRepo repo;
    ExecutorService executor;

    public Controller(IRepo rep) {
        repo = rep;
    }

    Map<Integer, Value> garbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg (List<PrgState> prgList) throws CustomException, InterruptedException {
        // print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (CustomException e) {
                System.out.print(e.getMessage());
            }
        });
        repo.logGenericPrgState();

        // prepare the list of callables
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep)).toList();

        // start the execution of the callables
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.print(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull).toList();

        // add the new programs
        prgList.addAll(newPrgList);

        // print again
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (CustomException e) {
                System.out.print(e.getMessage());
            }
        });
        repo.logGenericPrgState();

        // save the current programs in the repo
        repo.setPrgList(prgList);
    }

    public void conservativeGarbageCollector(List<PrgState> prgList) {
        prgList.forEach(prg -> {
            prg.getHeap().setContent(garbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent()),
                    getAddrFromHeap(prg.getHeap().getContent().values()),
                    prg.getHeap().getContent()
            ));
        });
    }

    public void allStep() throws CustomException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        // remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        // execute
        while (prgList.size() > 0) {
            conservativeGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(prgList);
        }
        executor.shutdownNow();

        // update the repo
        repo.setPrgList(prgList);
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public IRepo getRepo() {
        return repo;
    }
}
