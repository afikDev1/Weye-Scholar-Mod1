/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smi.daocouche;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import smi.configurationserver.ArchitectureDAO;
import smi.configurationserver.ServerData;
import smi.entitycouche.Eleve;

/**
 *
 * @author Joyce-Kabala
 */
public class EleveDAO implements ArchitectureDAO<Eleve> {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public boolean create(Eleve eleve) {

        String queryOne = " INSERT INTO T_Personne (nom, postnom, prenom, sexe, nationalite, adresse) "
                + " VALUES (?,?,?,?,?,?)";

        try {
            ps = ServerData.getConnectionInstance().prepareStatement(queryOne);
            ps.setString(1, eleve.getNom());
            ps.setString(2, eleve.getPostnom());
            ps.setString(3, eleve.getPrenom());
            ps.setString(4, eleve.getSexe());
            ps.setString(5, eleve.getNationalite());
            ps.setInt(6, eleve.getAdresse().getId());

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0) {

                int idpersonne = (int) findId(eleve);

                String queryTwo = " INSERT INTO T_Eleve (numeroPersonne, matriculeEleve, lieuNaissance, dateNaissance) "
                        + " VALUES (?,?,?,?)";

                ps = ServerData.getConnectionInstance().prepareStatement(queryTwo);
                ps.setInt(1, idpersonne);
                ps.setString(2, eleve.getMatriculeEleve());
                ps.setString(3, eleve.getLieuNaissance());
                ps.setString(4, eleve.getDateNaissance());

                int isOk = ps.executeUpdate();

                if (isOk > 0) {
                    return true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Eleve> listAll() {

        ArrayList<Eleve> lesEleves = new ArrayList<>();

        String query = "select * from t_personne as p inner join t_eleve as el on el.numeroPersonne = p.idPersonne";

        try {
            ps = ServerData.getConnectionInstance().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Eleve el = new Eleve();

                el.setMatriculeEleve(rs.getString("matriculeEleve"));
                el.setNom(rs.getString("nom"));
                el.setPostnom(rs.getString("postnom"));
                el.setPrenom(rs.getString("prenom"));
                el.setSexe(rs.getString("sexe"));
                //el.setAdresse(new AdresseDAO().searchObjectById(rs.getInt("adresse")));

                lesEleves.add(el);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesEleves;
    }

    @Override
    public boolean update(Eleve elev) {

        int idpersonne = (int) findId(elev);

        String query = "update t_personne set nom = ?, postnom = ?, prenom = ?, sexe = ?, nationalite = ?, adresse = ? "
                + "where idPersonne = ? ";

        try {
            ps = ServerData.getConnectionInstance().prepareStatement(query);
            ps.setString(1, elev.getNom());
            ps.setString(2, elev.getPostnom());
            ps.setString(3, elev.getPrenom());
            ps.setString(4, elev.getSexe());
            ps.setString(5, elev.getNationalite());
            ps.setInt(6, elev.getAdresse().getId());
            //
            ps.setInt(7, idpersonne);

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0) {

                String queryTwo = " update t_eleve set lieuNaissance = ?, dateNaissance= ? "
                        + "where numeroPersonne = ? ";

                ps.setString(1, elev.getLieuNaissance());
                ps.setString(2, elev.getDateNaissance());
                //
                ps.setInt(3, idpersonne);

                int isUpdatedTwo = ps.executeUpdate();

                if (isUpdatedTwo > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteById(Eleve el) {

        String query = " delete from t_personne where idPersonne = ?";

        try {
            ps = ServerData.getConnectionInstance().prepareStatement(query);
            ps.setInt(1, (int) findId(el));

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Eleve searchById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Eleve searchByObject(Eleve ele) {

        String query = " select * from t_personne as p inner join t_eleve as el on el.numeropersonne = p.idPersonne "
                + "WHERE matriculeEleve = ?";

        try {
            ps = ServerData.getConnectionInstance().prepareStatement(query);
            ps.setString(1, ele.getMatriculeEleve());

            rs = ps.executeQuery();

            if (rs.next()) {
                Eleve el = new Eleve();

                el.setMatriculeEleve(rs.getString("matriculeeleve"));
                el.setNom(rs.getString("nom"));
                el.setPostnom(rs.getString("postnom"));

                return el;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object findId(Eleve elev) {

        String query = "select idpersonne from t_eleve as el, t_personne as p where "
                + " el.numeropersonne = p.idpersonne and nom = ? and postnom = ? and prenom = ?";

        try {
            ps = ServerData.getConnectionInstance().prepareStatement(query);
            ps.setString(1, elev.getNom());
            ps.setString(2, elev.getPostnom());
            ps.setString(3, elev.getPrenom());

            rs = ps.executeQuery();

            if (rs.next()) {
                int idpersonne = rs.getInt("numeroPersonne");
                return idpersonne;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
