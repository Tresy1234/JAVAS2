package ism.com.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ism.com.entities.Niveau;

public class NiveauRepositoryBD extends BaseRepositoryBD<Niveau> {

    @Override
    public void add(Niveau niveau) {
        try {
            String sql = "INSERT INTO niveaux (name) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, niveau.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Niveau niveau) {
        try {
            String sql = "UPDATE niveaux SET name = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, niveau.getName());
            stmt.setLong(2, niveau.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Niveau niveau) {
        try {
            String sql = "DELETE FROM niveaux WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, niveau.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Niveau findById(Long id) {
        try {
            String sql = "SELECT * FROM niveaux WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Niveau niveau = new Niveau();
                niveau.setId(rs.getLong("id"));
                niveau.setName(rs.getString("name"));
                return niveau;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Niveau> findAll() {
        List<Niveau> niveaux = new ArrayList<>();
        try {
            String sql = "SELECT * FROM niveaux";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Niveau niveau = new Niveau();
                niveau.setId(rs.getLong("id"));
                niveau.setName(rs.getString("name"));
                niveaux.add(niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return niveaux;
    }
}
