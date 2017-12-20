package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Klant;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KlantDAO {

    private ConnectionPool pool;

    public KlantDAO(){
        this.pool = new ConnectionPool("org.mariadb.jdbc.Driver","jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");
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
