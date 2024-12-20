package ism.com;

import java.util.List;
import java.util.Scanner;

import ism.com.config.AppConfig;
import ism.com.entities.Classe;
import ism.com.entities.Cours;
import ism.com.entities.Niveau;
import ism.com.factory.RepositoryFactory;
import ism.com.repository.IRepository;
import ism.com.services.ClasseService;
import ism.com.services.CoursService;
import ism.com.services.NiveauService;

public class Main {

    public static void main(String[] args) {
       
        String repositoryType = AppConfig.REPOSITORY_TYPE;

         @SuppressWarnings("unchecked")
        IRepository<Niveau> niveauRepo = (IRepository<Niveau>) RepositoryFactory.createRepository(repositoryType, Niveau.class);
         @SuppressWarnings("unchecked")
        IRepository<Classe> classeRepo = (IRepository<Classe>) RepositoryFactory.createRepository(repositoryType, Classe.class);
         @SuppressWarnings("unchecked")
        IRepository<Cours> coursRepo = (IRepository<Cours>) RepositoryFactory.createRepository(repositoryType, Cours.class);
 
         NiveauService niveauService = new NiveauService(niveauRepo);
         ClasseService classeService = new ClasseService(classeRepo);
         CoursService coursService = new CoursService(coursRepo);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gérer les niveaux");
            System.out.println("2. Gérer les classes");
            System.out.println("3. Gérer les cours");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageNiveaux(niveauService, scanner);
                    break;
                case "2":
                    manageClasses(classeService, scanner);
                    break;
                case "3":
                    manageCours(coursService, scanner);
                    break;
                case "4":
                    running = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private static void manageNiveaux(NiveauService niveauService, Scanner scanner) {
        boolean managing = true;

        while (managing) {
            System.out.println("\n--- Gestion des Niveaux ---");
            System.out.println("1. Créer un niveau");
            System.out.println("2. Afficher tous les niveaux");
            System.out.println("3. Retour au menu principal");
            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Nom du niveau : ");
                    String name = scanner.nextLine();
                    Niveau niveau = new Niveau();
                    niveau.setName(name);
                    niveauService.createNiveau(niveau);
                    System.out.println("Niveau créé avec succès !");
                    break;
                case "2":
                    List<Niveau> niveaux = niveauService.findAllNiveaux();
                    System.out.println("Liste des niveaux :");
                    for (Niveau n : niveaux) {
                        System.out.println(n.getId() + " - " + n.getName());
                    }
                    break;
                case "3":
                    managing = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void manageClasses(ClasseService classeService, Scanner scanner) {
        boolean managing = true;

        while (managing) {
            System.out.println("\n--- Gestion des Classes ---");
            System.out.println("1. Créer une classe");
            System.out.println("2. Afficher toutes les classes");
            System.out.println("3. Retour au menu principal");
            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Nom de la classe : ");
                    String name = scanner.nextLine();
                    System.out.print("ID du niveau associé : ");
                    Long niveauId = Long.parseLong(scanner.nextLine());
                    Niveau niveau = new Niveau();
                    niveau.setId(niveauId);
                    Classe classe = new Classe();
                    classe.setName(name);
                    classe.setNiveau(niveau);
                    classeService.createClasse(classe);
                    System.out.println("Classe créée avec succès !");
                    break;
                case "2":
                    List<Classe> classes = classeService.findAllClasses();
                    System.out.println("Liste des classes :");
                    for (Classe cl : classes) {
                        System.out.println(cl.getId() + " - " + cl.getName() + " - Niveau : " + cl.getNiveau().getName());
                    }
                    break;
                case "3":
                    managing = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void manageCours(CoursService coursService, Scanner scanner) {
        boolean managing = true;

        while (managing) {
            System.out.println("\n--- Gestion des Cours ---");
            System.out.println("1. Créer un cours");
            System.out.println("2. Afficher tous les cours");
            System.out.println("3. Retour au menu principal");
            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Nom du module : ");
                    String module = scanner.nextLine();
                    System.out.print("ID de la classe associée : ");
                    Long classeId = Long.parseLong(scanner.nextLine());
                    Classe classe = new Classe();
                    classe.setId(classeId);
                    Cours cours = new Cours();
                    cours.setModule(module);
                    cours.setClasses(List.of(classe));
                    coursService.createCours(cours);
                    System.out.println("Cours créé avec succès !");
                    break;
                case "2":
                    List<Cours> coursList = coursService.findAllCours();
                    System.out.println("Liste des cours :");
                    for (Cours c : coursList) {
                        System.out.println(c.getId() + " - " + c.getModule());
                    }
                    break;
                case "3":
                    managing = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}
