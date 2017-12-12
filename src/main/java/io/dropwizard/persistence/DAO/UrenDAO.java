package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Personeel;
import io.dropwizard.models.Uren;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UrenDAO {
    ConnectionPool pool;

    public UrenDAO(){
        this.pool = new ConnectionPool("org.mariadb.jdbc.Driver","jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");
    }

    /**
     * Produceert een ResultSet wanneer wordt aangeroepen. Vereist de begin en einddatum als argument.
     * @param begindatum
     * @param einddatum
     * @return
     */
    public List<Uren> getUrenByKlantProjectOnderwerp(String begindatum, String einddatum, String klant, String project, String onderwerp) {
        ResultSet queryResultaten;
        queryResultaten = null;
        try {
            Connection con = pool.checkout();
            PreparedStatement statement = con.prepareStatement("SELECT geregistreerdetijd.*, personeel.voornaam, personeel.tussenvoegsel, personeel.achternaam FROM geregistreerdetijd JOIN personeel ON geregistreerdetijd.persoonID = personeel.persoonID\n WHERE begindatum >=? AND einddatum<=?"+ "AND (klant_naam = ? OR klant_naam LIKE ?)"+ "AND (project_naam = ? OR project_naam LIKE ?)"+ "AND (onderwerp_naam = ? OR onderwerp_naam LIKE ?)" );
            statement.setString(1, begindatum);
            statement.setString(2, einddatum);

            statement.setString(3, klant);
            statement.setString(4, klant+"%");
            statement.setString(5, project);
            statement.setString(6, project+"%");
            statement.setString(7, onderwerp);
            statement.setString(8, onderwerp+ "%");
            queryResultaten = statement.executeQuery();
            pool.checkIn(con);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toModel(queryResultaten);
    }
    private List<Uren> toModel(ResultSet results){
        List<Uren> toModel = new ArrayList<>();
        String personeelsNaam;
        try {
            while(results.next()){
                if (results.getString("tussenvoegsel") != null) {
                    personeelsNaam = results.getString("voornaam") + " " + results.getString("tussenvoegsel") + " " + results.getString("achternaam");
                } else {
                    personeelsNaam = results.getString("voornaam") + " " + results.getString("achternaam");
                }
                toModel.add(new Uren(
                        results.getInt("uurID"),
                        results.getString("begindatum"),
                        results.getString("einddatum"),
                        results.getString("begintijd"),
                        results.getString("eindtijd"),
                        results.getString("commentaar"),
                        results.getBoolean("goedgekeurd"),
                        results.getInt("persoonID"),
                        results.getString("klant_naam"),
                        results.getString("project_naam"),
                        results.getString("onderwerp_naam"),
                        personeelsNaam
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toModel;
    }
}

