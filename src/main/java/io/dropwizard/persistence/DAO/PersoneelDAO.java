package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.ConnectionPool;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersoneelDAO {
    Personeel user1;
    //TODO: De connectionpool moet ge inject worden inplaats van meegegeven of geinitialiseerd. Er mag er maar een levend zijn.

    private ConnectionPool pool;
    //private ConnectionPool pool = new ConnectionPool();

    public PersoneelDAO() {
        this.pool = new ConnectionPool("org.mariadb.jdbc.Driver","jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");
        user1 = new Personeel(1, "Pietje", "Potvis", "potvis@live.nl", "wachtwoord", "Admin", "Ja");
    }

    public Personeel getByEmailaddress(String email) {
        Personeel model = new Personeel();
        try {
            Connection con = pool.checkout();
            PreparedStatement getGebruiker = con.prepareStatement("SELECT * FROM personeel WHERE email =?");
            getGebruiker.setString(1, email);

            ResultSet results = getGebruiker.executeQuery();
            pool.checkIn(con);

            if (results.next()) {
                if (results.getString("tussenvoegsel") != null) {
                    model = new Personeel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("tussenvoegsel"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                } else {
                    model = new Personeel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public void setWerkzaam(int werkzaam, int id){
        try{
            Connection con = pool.checkout();
            System.out.println(werkzaam + " " + id);
            PreparedStatement statement = con.prepareStatement("update personeel set werkzaam = ? where persoonID = ?");
            statement.setInt(1, werkzaam);
            statement.setInt(2, id);
            statement.executeUpdate();
            pool.checkIn(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Personeel> getAll(){
        ResultSet results;
        List<Personeel> alHetPersoneel = new ArrayList<>();
        Connection con = pool.checkout();

        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM personeel");
            results = statement.executeQuery();
            pool.checkIn(con);
            while (results.next()){
                Personeel model;
                if (results.getString("tussenvoegsel") != null) {
                    model = new Personeel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("tussenvoegsel"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                } else {
                    model = new Personeel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                }
                alHetPersoneel.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            pool.checkIn(con);
        }
        return alHetPersoneel;
    }
}
