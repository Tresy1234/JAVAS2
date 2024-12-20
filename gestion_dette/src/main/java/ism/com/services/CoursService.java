package ism.com.services;

import java.util.List;
import ism.com.entities.Cours;
import ism.com.repository.IRepository;

public class CoursService {
    private final IRepository<Cours> coursRepository;

    public CoursService(IRepository<Cours> coursRepository) {
        this.coursRepository = coursRepository;
    }

    public void createCours(Cours cours) {
        coursRepository.add(cours);
    }

    public void updateCours(Cours cours) {
        coursRepository.update(cours);
    }

    public void deleteCours(Cours cours) {
        coursRepository.delete(cours);
    }

    public Cours findCoursById(Long id) {
        return coursRepository.findById(id);
    }

    public List<Cours> findAllCours() {
        return coursRepository.findAll();
    }
}
