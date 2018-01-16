package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Personeel;
import io.dropwizard.models.Uren;
import io.dropwizard.persistence.ConnectionPool;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UrenDAO extends DAO{

    public UrenDAO(ConnectionPool pool){
        super(pool);
    }

    /**
     * Produceert een ResultSet wanneer wordt aangeroepen. Vereist de begin en einddatum als argument.
     * @param begindatum
     * @param einddatum
     * @return
     */
    public List<Uren> getUrenByKlantProjectOnderwerp(Integer id, String begindatum, String einddatum, String klant, String project, String onderwerp) {
        ResultSet queryResultaten;
        queryResultaten = null;
        Connection con = pool.checkout();
        try {

            PreparedStatement statement;
            if(id==null){
                statement = con.prepareStatement("SELECT geregistreerdetijd.*, personeel.voornaam, personeel.tussenvoegsel, personeel.achternaam FROM geregistreerdetijd JOIN personeel ON geregistreerdetijd.persoonID = personeel.persoonID\n WHERE begindatum >=? AND einddatum<=?"+ "AND (klant_naam = ? OR klant_naam LIKE ?)"+ "AND (project_naam = ? OR project_naam LIKE ?)"+ "AND (onderwerp_naam = ? OR onderwerp_naam LIKE ?)" + "AND persoonID = ?" );
                statement.setInt(9, id);
            }else {
                statement = con.prepareStatement("SELECT geregistreerdetijd.*, personeel.voornaam, personeel.tussenvoegsel, personeel.achternaam FROM geregistreerdetijd JOIN personeel ON geregistreerdetijd.persoonID = personeel.persoonID\n WHERE begindatum >=? AND einddatum<=?"+ "AND (klant_naam = ? OR klant_naam LIKE ?)"+ "AND (project_naam = ? OR project_naam LIKE ?)"+ "AND (onderwerp_naam = ? OR onderwerp_naam LIKE ?)" );
            }

            statement.setString(1, begindatum);
            statement.setString(2, einddatum);

            statement.setString(3, klant);
            statement.setString(4, klant+"%");
            statement.setString(5, project);
            statement.setString(6, project+"%");
            statement.setString(7, onderwerp);
            statement.setString(8, onderwerp+ "%");
            queryResultaten = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
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
                        results.getInt("persoonID"),
                        results.getString("begindatum"),
                        results.getString("begintijd"),
                        results.getString("eindtijd"),
                        results.getString("klant_naam"),
                        results.getString("project_naam"),
                        results.getString("onderwerp_naam"),
                        results.getInt("klant_ID"),
                        results.getInt("project_ID"),
                        results.getInt("onderwerp_ID"),
                        results.getString("commentaar"),
                        results.getBoolean("goedgekeurd"),
                        personeelsNaam,
                        false
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toModel;
    }

    public List<Uren> getByPersoonId(int id){
        ResultSet resultSet = null;
        Connection con = pool.checkout();
        PreparedStatement statement;

        try {
            statement = con.prepareStatement("SELECT geregistreerdetijd.uurID, geregistreerdetijd.begindatum, geregistreerdetijd.einddatum, geregistreerdetijd.begintijd, geregistreerdetijd.eindtijd, geregistreerdetijd.commentaar, geregistreerdetijd.goedgekeurd, personeel.voornaam, personeel.persoonID, personeel.achternaam, personeel.tussenvoegsel, klant.klant_naam, geregistreerdetijd.klant_ID, project.project_naam, geregistreerdetijd.project_ID, onderwerp.onderwerp_naam, geregistreerdetijd.onderwerp_ID FROM geregistreerdetijd JOIN personeel ON personeel.persoonID = geregistreerdetijd.persoonID JOIN klant ON klant.klant_ID = geregistreerdetijd.klant_ID JOIN project ON project.project_ID = geregistreerdetijd.project_ID JOIN onderwerp ON onderwerp.onderwerp_ID = geregistreerdetijd.onderwerp_ID WHERE geregistreerdetijd.persoonID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            pool.checkIn(con);
        }
        return toModel(resultSet);
    }

    public List<Uren> getAllUren() {
        ResultSet results = null;
        Connection con = pool.checkout();;
        PreparedStatement statement;
        try {

            statement = con.prepareStatement("SELECT geregistreerdetijd.uurID, geregistreerdetijd.begindatum, geregistreerdetijd.einddatum, geregistreerdetijd.begintijd, geregistreerdetijd.eindtijd, geregistreerdetijd.commentaar, geregistreerdetijd.goedgekeurd, personeel.voornaam, personeel.persoonID, personeel.achternaam, personeel.tussenvoegsel, klant.klant_naam, geregistreerdetijd.klant_ID, project.project_naam, geregistreerdetijd.project_ID, onderwerp.onderwerp_naam, geregistreerdetijd.onderwerp_ID FROM geregistreerdetijd JOIN personeel ON personeel.persoonID = geregistreerdetijd.persoonID JOIN klant ON klant.klant_ID = geregistreerdetijd.klant_ID JOIN project ON project.project_ID = geregistreerdetijd.project_ID JOIN onderwerp ON onderwerp.onderwerp_ID = geregistreerdetijd.onderwerp_ID");
            results = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return toModel(results);
    }

    public void setHour(Uren hour) {
        Connection con = pool.checkout();
        try {
            PreparedStatement setHour = null;
            if (hour.getComment() != null) {
                setHour = con.prepareStatement("INSERT INTO geregistreerdetijd (begindatum, begintijd, eindtijd, persoonID, klant_ID, project_ID, onderwerp_ID, commentaar) VALUES (?,?,?,?,?,?,?,?)");
                setHour.setString(8, hour.getComment());

            } else {
                setHour = con.prepareStatement("INSERT INTO geregistreerdetijd (begindatum, begintijd, eindtijd, persoonID, klant_ID, project_ID, onderwerp_ID) VALUES (?,?,?,?,?,?,?)");

            }
                setHour.setString(1, hour.getStartingDate());
                setHour.setString(2, hour.getStartingTime());
                setHour.setString(3, hour.getEndingTime());

                setHour.setInt(4, hour.getEmployeeId());
                setHour.setInt(5, hour.getCustomerId());
                setHour.setInt(6, hour.getProjectId());
                setHour.setInt(7, hour.getSubjectId());

            setHour.executeQuery();

            System.out.println("urengezet");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }

    public void setConfirmed(Uren uur, boolean confirmed){
        Connection con = pool.checkout();
        try{
            PreparedStatement statement = con.prepareStatement("UPDATE geregistreerdetijd set goedgekeurd = ? WHERE uurID = ?");
            statement.setBoolean(1, confirmed);
            statement.setInt(2, uur.getUurId());
            statement.executeUpdate();
        } catch(SQLException e){

            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }

    public void updateHour(Uren hour){
        Connection con = pool.checkout();
        try{
            PreparedStatement statement = con.prepareStatement("UPDATE gereristreerdetijd SET begindatum = ?, begintijd = ?, eindtijd = ?, commentaar = ?, persoonID = ?, klant_ID = ?, project_ID = ?, onderwerp_ID = ? WHERE uurID = ? AND confirmed = false");
            statement.setString(1, hour.getStartingDate());
            statement.setString(2, hour.getStartingTime());
            statement.setString(3, hour.getEndingTime());
            statement.setString(4, hour.getComment());
            statement.setInt(5, hour.getEmployeeId());
            statement.setInt(6, hour.getCustomerId());
            statement.setInt(7, hour.getProjectId());
            statement.setInt(8, hour.getSubjectId());
            statement.setInt(9, hour.getUurId());
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }
}

