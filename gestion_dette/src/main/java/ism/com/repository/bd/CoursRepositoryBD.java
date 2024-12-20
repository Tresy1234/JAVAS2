package ism.com.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ism.com.entities.Cours;
import ism.com.entities.Classe;

public class CoursRepositoryBD extends BaseRepositoryBD<Cours> {

    @Override
    public void add(Cours cours) {
        try {
            String sql = "INSERT INTO cours (module) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cours.getModule());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cours cours) {
        try {
            String sql = "UPDATE cours SET module = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cours.getModule());
            stmt.setLong(2, cours.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cours cours) {
        try {
            String sql = "DELETE FROM cours WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, cours.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cours findById(Long id) {
        try {
            String sql = "SELECT * FROM cours WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cours cours = new Cours();
                cours.setId(rs.getLong("id"));
                cours.setModule(rs.getString("module"));
                
                // Associer les classes à ce cours
                List<Classe> classes = getClassesForCours(cours.getId());
                cours.setClasses(classes);

                return cours;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cours";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cours cours = new Cours();
                cours.setId(rs.getLong("id"));
                cours.setModule(rs.getString("module"));

                // Associer les classes à ce cours
                List<Classe> classes = getClassesForCours(cours.getId());
                cours.setClasses(classes);

                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursList;
    }

    // Méthode pour récupérer les classes associées à un cours
    private List<Classe> getClassesForCours(Long coursId) {
        List<Classe> classes = new ArrayList<>();
        try {
            String sql = "SELECT c.* FROM classes c JOIN cours_classes cc ON c.id = cc.classe_id WHERE cc.cours_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, coursId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getLong("id"));
                classe.setName(rs.getString("name"));
                classes.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
