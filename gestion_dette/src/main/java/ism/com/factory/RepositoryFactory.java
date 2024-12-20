package ism.com.factory;

import ism.com.entities.Classe;
import ism.com.entities.Cours;
import ism.com.entities.Niveau;
import ism.com.repository.IRepository;
import ism.com.repository.bd.ClasseRepositoryBD;
import ism.com.repository.bd.CoursRepositoryBD;
import ism.com.repository.bd.NiveauRepositoryBD;

import ism.com.repository.list.ClasseRepositoryList;
import ism.com.repository.list.CoursRepositoryList;
import ism.com.repository.list.NiveauRepositoryList;

public class RepositoryFactory {

    public static IRepository<?> createRepository(String type, Class<?> entityClass) {
        if (type.equalsIgnoreCase("bd")) {
            // Création des repositories pour la base de données
            if (entityClass.equals(Niveau.class)) {
                return new NiveauRepositoryBD();
            } else if (entityClass.equals(Classe.class)) {
                return new ClasseRepositoryBD();
            } else if (entityClass.equals(Cours.class)) {
                return new CoursRepositoryBD();
            }
        } else if (type.equalsIgnoreCase("list")) {
            // Création des repositories en mémoire
            if (entityClass.equals(Niveau.class)) {
                return new NiveauRepositoryList();
            } else if (entityClass.equals(Classe.class)) {
                return new ClasseRepositoryList();
            } else if (entityClass.equals(Cours.class)) {
                return new CoursRepositoryList();
            }
        }
        throw new IllegalArgumentException("Type de repository ou entité non pris en charge");
    }
}
