package Repository;

import Domain.PrgState;

public class Repo implements IRepo {
    PrgState state;

    public Repo(PrgState repoState) {
        state = repoState;
    }

    @Override
    public PrgState getCrtPrg() {
        return state;
    }
}
