package ism.com.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ism.com.entities.Classe;
import ism.com.entities.Niveau;

public class ClasseRepositoryBD extends BaseRepositoryBD<Classe> {

    @Override
    public void add(Classe classe) {
        try {
            String sql = "INSERT INTO classes (name, niveau_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, classe.getName());
            stmt.setLong(2, classe.getNiveau().getId());  // Assurez-vous que 'niveau' est un objet de type Niveau
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Classe classe) {
        try {
            String sql = "UPDATE classes SET name = ?, niveau_id = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, classe.getName());
            stmt.setLong(2, classe.getNiveau().getId());  // Assurez-vous que 'niveau' est un objet de type Niveau
            stmt.setLong(3, classe.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Classe classe) {
        try {
            String sql = "DELETE FROM classes WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, classe.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Classe findById(Long id) {
        try {
            String sql = "SELECT * FROM classes WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getLong("id"));
                classe.setName(rs.getString("name"));

                // Récupérer le niveau associé à cette classe
                Long niveauId = rs.getLong("niveau_id");
                Niveau niveau = new Niveau();  // Ici vous pouvez récupérer le niveau depuis un autre repository si nécessaire
                niveau.setId(niveauId);  // Vous devriez probablement récupérer le niveau réel via un repository Niveau

                classe.setNiveau(niveau);  // Assigner l'objet Niveau à la classe
                return classe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Classe> findAll() {
        List<Classe> classes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM classes";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getLong("id"));
                classe.setName(rs.getString("name"));

                // Récupérer le niveau associé à cette classe
                Long niveauId = rs.getLong("niveau_id");
                Niveau niveau = new Niveau();
                niveau.setId(niveauId);

                classe.setNiveau(niveau);  // Assigner l'objet Niveau à la classe
                classes.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
