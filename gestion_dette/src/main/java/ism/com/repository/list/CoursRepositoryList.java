package ism.com.repository.list;

import ism.com.entities.Cours;
import java.util.List;

public class CoursRepositoryList extends BaseRepositoryList<Cours> {

    @Override
    public void add(Cours cours) {
        storage.add(cours);
    }

    @Override
    public void update(Cours cours) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId().equals(cours.getId())) {
                storage.set(i, cours);
                return;
            }
        }
    }

    @Override
    public void delete(Cours cours) {
        storage.removeIf(c -> c.getId().equals(cours.getId()));
    }

    @Override
    public Cours findById(Long id) {
        return storage.stream()
                .filter(cours -> cours.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cours> findAll() {
        return storage;  // Retourne la liste des cours
    }
}
