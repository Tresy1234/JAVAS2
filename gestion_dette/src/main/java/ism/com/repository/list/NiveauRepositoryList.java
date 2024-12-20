package ism.com.repository.list;

import ism.com.entities.Niveau;
import java.util.List;

public class NiveauRepositoryList extends BaseRepositoryList<Niveau> {

    @Override
    public void add(Niveau niveau) {
        storage.add(niveau);
    }

    @Override
    public void update(Niveau niveau) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId().equals(niveau.getId())) {
                storage.set(i, niveau);
                return;
            }
        }
    }

    @Override
    public void delete(Niveau niveau) {
        storage.removeIf(n -> n.getId().equals(niveau.getId()));
    }

    @Override
    public Niveau findById(Long id) {
        return storage.stream()
                .filter(niveau -> niveau.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Niveau> findAll() {
        return storage;
    }
}
