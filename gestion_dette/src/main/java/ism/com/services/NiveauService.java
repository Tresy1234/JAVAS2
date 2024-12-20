
package ism.com.services;

import java.util.List;
import ism.com.entities.Niveau;
import ism.com.repository.IRepository;

public class NiveauService {
    private final IRepository<Niveau> niveauRepository;

    public NiveauService(IRepository<Niveau> niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    public void createNiveau(Niveau niveau) {
        niveauRepository.add(niveau);
    }

    public void updateNiveau(Niveau niveau) {
        niveauRepository.update(niveau);
    }

    public void deleteNiveau(Niveau niveau) {
        niveauRepository.delete(niveau);
    }

    public Niveau findNiveauById(Long id) {
        return niveauRepository.findById(id);
    }

    public List<Niveau> findAllNiveaux() {
        return niveauRepository.findAll();
    }
}
