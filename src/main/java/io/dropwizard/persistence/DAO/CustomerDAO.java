package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Customer;
import io.dropwizard.persistence.ConnectionPool;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CustomerDAO {

    private ConnectionPool pool;
    //private ConnectionPool pool = new ConnectionPool();

    public CustomerDAO() {
        this.pool = new ConnectionPool("org.mariadb.jdbc.Driver", "jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");
    }

    public List<Customer> getCustomer() {
        List<Customer> klanten = new ArrayList<Customer>();

        Connection con = this.pool.checkout();
        try {
            PreparedStatement getCustomers = con.prepareStatement("SELECT klant_ID, klant_naam FROM klant");
            ResultSet results = getCustomers.executeQuery();
            while(results.next()) {
                klanten.add(new Customer(results.getInt("klant_ID"), results.getString("klant_naam")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return klanten;
    }

    public void add(String customerName){
        Connection con = pool.checkout();
        try{
            PreparedStatement statement = con.prepareStatement("INSERT INTO klant (klant_naam) VALUES (?)");
            statement.setString(1, customerName);

        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }

    public Customer getCustomerByName(String customerName) {
        Connection con = pool.checkout();
        Customer customer = null;
        try{
            PreparedStatement getCustomer = con.prepareStatement("SELECT klant_ID, klant_naam FROM klant WHERE klant_naam = (?)");
            getCustomer.setString(1, customerName);
            ResultSet results = getCustomer.executeQuery();
            if (results.next()) {
                customer = new Customer(results.getInt("klant_ID"), results.getString("klant_naam"));
            }



        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return customer;
    }
}
