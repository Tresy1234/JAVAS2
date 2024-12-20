package ism.com.services;

import java.util.List;
import ism.com.entities.Classe;
import ism.com.repository.IRepository;

public class ClasseService {
    private final IRepository<Classe> classeRepository;

    public ClasseService(IRepository<Classe> classeRepository) {
        this.classeRepository = classeRepository;
    }

    public void createClasse(Classe classe) {
        classeRepository.add(classe);
    }

    public void updateClasse(Classe classe) {
        classeRepository.update(classe);
    }

    public void deleteClasse(Classe classe) {
        classeRepository.delete(classe);
    }

    public Classe findClasseById(Long id) {
        return classeRepository.findById(id);
    }

    public List<Classe> findAllClasses() {
        return classeRepository.findAll();
    }
}
