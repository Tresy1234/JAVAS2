package ism.com.repository.list;

import ism.com.entities.Classe;
import java.util.List;

public class ClasseRepositoryList extends BaseRepositoryList<Classe> {

    @Override
    public void add(Classe classe) {
        storage.add(classe);
    }

    @Override
    public void update(Classe classe) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId().equals(classe.getId())) {
                storage.set(i, classe);
                return;
            }
        }
    }

    @Override
    public void delete(Classe classe) {
        storage.removeIf(c -> c.getId().equals(classe.getId()));
    }

    @Override
    public Classe findById(Long id) {
        return storage.stream()
                .filter(classe -> classe.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Classe> findAll() {
        return storage;
    }
}
