package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Klant;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KlantDAO {

    private ConnectionPool pool;
    //private ConnectionPool pool = new ConnectionPool();

    public KlantDAO() {
        this.pool = new ConnectionPool("org.mariadb.jdbc.Driver", "jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");
    }

    public List<Klant> getCustomer() {
        List<Klant> klanten = new ArrayList<Klant>();

        Connection con = this.pool.checkout();
        try {
            PreparedStatement getCustomers = con.prepareStatement("SELECT klant_ID, klant_naam FROM klant");
            ResultSet results = getCustomers.executeQuery();
            while(results.next()) {
                klanten.add(new Klant(results.getInt("klant_ID"), results.getString("klant_naam")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return klanten;
    }

    public void add(Klant klant){
        try{
            Connection con = pool.checkout();
            PreparedStatement statement = con.prepareStatement("INSERT INTO klant (klant_naam) VALUES (?)");
            statement.setString(1, klant.getKlantNaam());

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
